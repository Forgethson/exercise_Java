package src.main.java.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue {
    private Object[] array; //数组
    private int head; //头
    private int tail; //尾
    private volatile int size; //元素个数
    private ReentrantLock lock = new ReentrantLock(); //锁
    private Condition notEmpty = lock.newCondition(); //非空
    private Condition notFull = lock.newCondition();  //非满

    public ArrayBlockingQueue(int capacity) {
        this.array = new Object[capacity];
    }

    //写入元素
    public void put(Object o) throws InterruptedException {
        try{
            lock.lock();
            //当队列满时，阻塞
            while (size == array.length) {
                notFull.wait();
            }
            array[tail++] = o;
            if (tail == array.length) {
                tail = 0;
            }
            size ++;
            //唤醒线程
            notEmpty.notifyAll();
        } finally {
            lock.unlock();
        }
    }

    //取出元素
    public Object get() throws InterruptedException {
        lock.lock();
        try {
            //当队列为空，阻塞
            while (size == 0) {
                notEmpty.wait();
            }
            Object o = array[head++];
            if (head == array.length) {
                head = 0;
            }
            size --;
            //唤醒线程
            notFull.notifyAll();
            return o;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
        arrayBlockingQueue.put(1);

    }

}