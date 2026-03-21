package test;


import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            // 当前已经种花
            if (flowerbed[i] == 1) {
                continue;
            }
            // 当前未种花，判断前后是否种过花（第一个好最后一个，直接种花）
            if ((i == 0 || flowerbed[i - 1] == 0) && (i + 1 == flowerbed.length || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                n--;
            }
        }
        return n <= 0;
    }
}