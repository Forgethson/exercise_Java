package src.main.java.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue2 {

    private Node head;
    private Node tail;
    private int size;
    private int capacity;

    private ReentrantLock lock = new ReentrantLock(); //锁
    private Condition notEmpty = lock.newCondition(); //非空
    private Condition notFull = lock.newCondition();  //非满

    class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node() {
        }
    }

    public BlockQueue2(int capacity) {
        head = new Node();
        tail = new Node();
        head.next = tail;
        size = 0;
        this.capacity = capacity;
    }

    public void produce(int val) throws InterruptedException {
        lock.lock();
        try {
            while (size == capacity) {
                // 等待notFull
                notFull.await();
                System.out.println("唤醒生产者，此时size=" + size);
            }
            tail.next = new Node(val);
            tail = tail.next;
            // 先释放资源，再唤醒
            size++;
            // 通知，已经notEmpty
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (size == 0) {
                // 等待notEmpty
                notEmpty.await();
                System.out.println("唤醒消费者，此时size=" + size);
            }
            Node tmp = head.next;

            head.next = head.next.next;
            tmp.next = null;
            // 先释放资源，再唤醒
            size--;
            // 通知，已经notFull
            notFull.signal();
            return tmp.val;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockQueue2 blockQueue2 = new BlockQueue2(100);

        int round = 1000;
        for (int i = 0; i < round; i++) {
            int ii = i;
            new Thread(() -> {
                try {
                    blockQueue2.produce(ii);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(1000);
        for (int i = 0; i < round; i++) {
            new Thread(() -> {
                try {
                    int consume = blockQueue2.consume();
                    System.out.println("consume: " + consume);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

//        producerThread.start();
//        consumerThread.start();


    }
}