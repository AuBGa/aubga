package com.aubga.alog.sort;

class Test {
    public static int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];
        for(int i=0;i<last.length;i++) {
            last[i] = -1;// start位置是准的;
        }

        int n = s.length();
        int start = 0;
        int res = 0;
        for(int i=0;i<n;i++) {
            int index = s.charAt(i);
            start = Math.max(start,last[index]+1);
            res = Math.max(res,i -start +1);
            last[index] = i;

            System.out.println("start:"+start+",res:"+res);

        }
        return res;

    }

    public static void main(String[] args) {
       /* String string = "abca";
        int maxLength = Test.lengthOfLongestSubstring(string);

        System.out.print(maxLength);*/

        int[] array = new int[]{-1,3,-5,2,4,6};
        int max = maxSubArray(array);
        System.out.println(max);
    }

    public static int maxSubArray(int[] nums) {
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
}