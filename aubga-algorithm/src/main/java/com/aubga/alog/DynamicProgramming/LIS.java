package com.aubga.alog.DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

//最长递增子序列（Longest Increasing Subsequence，简写 LIS）
public class LIS {
    public int longestOfLIS(int[] nums) {

        int res = 0;

        int[]  dp = new int[nums.length];
        Arrays.fill(dp,1);

        for(int i=0;i<nums.length;i++) {
            for(int j=0;j<i;j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    //
    public int maxEnvelopes(int[][] envelopes) {
          int n = envelopes.length;
          Arrays.sort(envelopes,new Comparator<int[]>() {
              @Override
              public int compare(int[] o1, int[] o2) {
                  return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
              }
          });
          //对高度数组寻找LIS
        int[] height = new int[n];

        for(int i=0;i<n;i++)
                height[i] = envelopes[i][1];

        return lengthOfLIS(height);
    }

    //利用二分法来进行求LIS
    private int lengthOfLIS(int[] nums) {
       int[] top = new int[nums.length];
       //牌堆数初始化为0
        int piples = 0;
        for (int i=0;i<nums.length;i++) {
            //要处理的扑克
            int poker = nums[i];

            int left = 0,right = piples;
            while(left < right) {
                System.out.println("left:"+left+",right:"+right);
                int mid = (left + right) /2 ;
                if(top[mid] > poker) {
                    right = mid;
                }else if(top[mid] < poker) {
                    left = mid + 1;
                }else {
                    right = mid;
                }

            }
            //没有找到合适的牌堆,新建一堆
            if (left == piples) piples ++;
            top[left] = poker;
        }
        return piples;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,4,3,4,2,3};
        LIS lis = new LIS();
        int res = lis.longestOfLIS(nums);
        System.out.println(res);

        int[][] nums1 = new int[][]{{5,4},{6,4},{6,7},{2,3}};

        int res1 = lis.maxEnvelopes(nums1);
        System.out.println(res1);
    }
}
