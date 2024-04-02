package src.main.java.test;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * get方法：先判断key是否存在，如果不存在，返回-1；如果存在，返回val，并将该node移动到头结点
 * put方法：先判断key是否存在
 * 如果存在，修改key的值，并将改node移动到头结点
 * 如果不存在，map添加键值对，并头插法插入新的节点，再判断size是否大于capacity，大于则删除尾结点，并在map中删除key
 * 并修改为简易版本的线程安全版本
 * 由于这里使用自定义的Node节点作为map的value，修改map的同时，需要修改链表（上锁），这种情况下，使用锁粒度为单个table的ConcurrentHashMap，意义不大
 * ConcurrentHashMap可以实现并发写，但这里由于修改链表的存在，一定不能并发修改，因此用写锁，不能并发写
 */

public class LRUCache {

    Node head;
    Node tail;
    HashMap<Integer, Node> map = new HashMap<>();
    //    ConcurrentHashMap<Integer, Node> map = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    //    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private final ArrayBlockingQueue<Runnable> RunnableQueue = new ArrayBlockingQueue<>(10);
    ExecutorService threadPool = new ThreadPoolExecutor(
            10,
            50,
            2L,
            TimeUnit.SECONDS,
            RunnableQueue,
            Executors.defaultThreadFactory(),
            (r, executor) -> RunnableQueue.offer(r)
    );

    int size;
    int capacity;

    class Node {
        int val;
        int key;
        Node next;
        Node pre;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public Node() {
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    public void put(int key, int val) {
        lock.writeLock().lock();
        try {
            Node node = map.get(key);
            // key存在
            if (node != null) {
                node.val = val;
                moveToHead(node);
                return;
            }
            Node newNode = new Node(key, val);
            map.put(key, newNode);
            addToHead(newNode);
            if (size == capacity) {
                map.remove(tail.pre.key);
                removeRear();
            } else {
                size++;
            }
        } finally {
            lock.writeLock().unlock();
//            System.out.println("写锁释放");
        }
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        lock.readLock().lock();
        lock.readLock().lock();
        try {
            moveToHead(node);
        } finally {
            lock.readLock().unlock();
        }
        // 异步修改双向链表，链表修改完毕后，才释放读锁？（不能这样，ReentrantReadWriteLock不能释放别的线程的锁）
//        threadPool.execute(() -> {
//            try {
//                moveToHead(node);
//            } finally {
//                lock.readLock().unlock();
//                System.out.println("读锁释放");
//            }
//        });
        return node.val;
    }

    // synchronized，可重入
    private synchronized void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.next = null;
        node.pre = null;
    }

    private synchronized void addToHead(Node node) {
        Node tmp = head.next;
        head.next = node;
        node.pre = head;
        node.next = tmp;
        tmp.pre = node;
    }

    private synchronized void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private synchronized void removeRear() {
        removeNode(tail.pre);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(100);

        Thread getThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                int ii = i;
                new Thread(() -> {
                    System.out.println("读到了" + ii + "的值：" + lruCache.get(ii));
                }).start();
            }
        });

        Thread putThread = new Thread(() -> {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    lruCache.put(i, i);
                }
            }).start();
        });

        getThread.start();
        putThread.start();
    }
}






