package com.aubga.alog.sort;

public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length+1];
        for (int i = 1; i <= nums.length; i++) {
            dp[i] += Math.max(0,dp[i-1]) + nums[i-1];
            if(dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubArray msa = new MaxSubArray();
        int result =  msa.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(result);
    }
}
