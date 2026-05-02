package test.leetcode;

import java.util.LinkedList;
import java.util.Queue;

// 933-https://leetcode.cn/problems/number-of-recent-calls/description/?envType=study-plan-v2&envId=leetcode-75
public class RecentCounter {

    private Queue<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<>();
    }

    public int ping(int t) {
        while (!queue.isEmpty() && queue.peek() < t - 3000) {
            queue.poll();
        }
        queue.offer(t);
        return queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */