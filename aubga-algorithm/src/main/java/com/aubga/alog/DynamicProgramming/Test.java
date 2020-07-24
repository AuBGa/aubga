package com.aubga.alog.DynamicProgramming;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] dp = new int[10];
        Arrays.fill(dp,1);
        for(int i=0;i<dp.length;i++) {
            System.out.println(dp[i]);
        }
    }
}
