package src.main.java.test;

public class BlockQueue1 {

    private Node head;
    private Node tail;
    private int size;
    private int capacity;

    class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node() {
        }
    }

    public BlockQueue1(int capacity) {
        head = new Node();
        tail = new Node();
        head.next = tail;
        size = 0;
        this.capacity = capacity;
    }

    public synchronized void produce(int val) throws InterruptedException {
        // while？
        if (size == capacity) {
            this.wait();
            System.out.println("唤醒生产者，此时size=" + size);
        }
        tail.next = new Node(val);
        tail = tail.next;
        // 先释放资源，再唤醒？
        size++;
        this.notify();
    }

    public synchronized int consume() throws InterruptedException {
        // while？
        if (size == 0) {
            this.wait();
            System.out.println("唤醒消费者，此时size=" + size);
        }
        Node tmp = head.next;
        head.next = head.next.next;
        tmp.next = null;
        // 先释放资源，再唤醒？
        size--;
        this.notify();
        return tmp.val;
    }

    public static void main(String[] args) throws InterruptedException {
        // 消费和生产方法都有synchronized修饰，则无论是生产者还是消费者，都只能有一个线程获得锁；当不满足条件时，线程阻塞，主动放弃锁
        // 阻塞的位置：因没有获取到锁而被动阻塞；因队列条件不满足而调用this.wait()主动阻塞
        // 被动阻塞不需要通过notify来唤醒；主动阻塞需要通过notify来唤醒。
        // 不用while的原因：只有生产/消费成功了才会唤醒别的线程。
        // 不会存在下面的两种情况：阻塞队列满了，但是唤醒了一个生产者线程；阻塞队列空了，但是唤醒了一个消费者线程。
        // 因为如果阻塞队列满了，能执行唤醒操作的一定是消费者线程，则消费之后此时阻塞队列一定不是满的了；情况2同理。
        // 先释放资源，再唤醒的原因：这里消费者和生产者使用的同一个synchronized锁对象，只有等释放了锁之后（代码块结束/抛出异常等），被唤醒了的线程才继续
        BlockQueue1 blockQueue1 = new BlockQueue1(100);

        for (int i = 0; i < 300; i++) {
            int ii = i;
            new Thread(() -> {
                try {
                    blockQueue1.produce(ii);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(1000);
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                try {
                    int consume = blockQueue1.consume();
                    System.out.println("consume: " + consume);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

//        producerThread.start();
//        Thread.sleep(1000);
//        consumerThread.start();

    }
}