package src.main.java.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * get方法：先判断key是否存在，如果不存在，返回-1；如果存在，返回val，并将该node移动到头结点
 * put方法：先判断key是否存在
 * 如果存在，修改key的值，并将改node移动到头结点
 * 如果不存在，map添加键值对，并头插法插入新的节点，再判断size是否大于capacity，大于则删除尾结点，并在map中删除key
 */

public class ConcurrentLRUCache {

    int size;
    int capacity;
    private ConcurrentHashMap<Integer, Integer> cache = new ConcurrentHashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ConcurrentLinkedDeque<Integer> queue = new ConcurrentLinkedDeque<>();

    public ConcurrentLRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
    }

    public Integer put(Integer key, Integer value) {
        lock.writeLock().lock();
        try {
            //1. key存在当前缓存中
            if (cache.containsKey(key)) {
                queue.removeLastOccurrence(key);
                queue.offer(key);
                cache.put(key, value);
                return value;
            }
            //超出缓存容量
            if (cache.size() == capacity) {
                Integer oldestKey = queue.poll();
                if (oldestKey != null) {
                    cache.remove(oldestKey);
                }
            }
            queue.offer(key);
            cache.put(key, value);
            return value;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int get(int key) {
        Integer cached = cache.get(key);
        if (cached == null) return -1;
        lock.readLock().lock();
        try {
            if (queue.removeLastOccurrence(key)) {
                queue.offer(key);
            }
        } finally {
            lock.readLock().unlock();
        }
        return cached;
    }

}






