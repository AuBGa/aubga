package com.aubga.alog.DynamicProgramming;

import java.util.Arrays;

/**
 *dp 动态规划
 *时间复杂度:子问题个数(不同状态组合的总数)*函数本身的复杂度
 *下文第一种线性方式时间复杂度:O(K*N*N)
 *下午第二种二分方式时间复杂度:O(K*N*logN)
 *O(K*N)
 */
class SuperEggDrop {
    Integer[][] memo = null;
    public int superEggDrop(int K, int N) {
        memo = new Integer[K+1][N+1];
        return dp(K,N);
    }

    /**
     *当前K个鸡蛋共有N层楼状态时 返回这个状态下确定 F的值的最小移动次数
     */
    private int dp(int k,int n){
        //base case 层数N等于0时不需要扔鸡蛋,当鸡蛋数K为1时，只能每层逐个尝试剩下所有楼层
        if(k==1)return n;
        if(n<=0)return 0;

        //添加一个备忘录 消除之前算过的重叠子问题
        if(memo[k][n]!=null)return memo[k][n];

        int tmp = Integer.MAX_VALUE;

        // //第一种 从第1层楼到第n层楼每层楼开始逐个尝试作为切入点 (会超时)
        // for(int i=1;i<=n+1;i++){
        //     //当选择在第i层楼扔了鸡蛋之后 可能出现鸡蛋碎了和鸡蛋没碎两种情况：
        //     //当鸡蛋碎了 问题状态转嫁为求k-1个鸡蛋 搜索的楼层区间变为1~i-1共i-1层楼时求解
        //     int eggBreak=dp(k-1,i-1);
        //     //当鸡蛋没碎 问题状态转嫁为鸡蛋的个数K不变 搜索楼层区间变为i+1~N共N-i层楼时求解
        //     int eggUnBreak=dp(k,n-i);
        //     //最终以i层为切入点求解的答案 为两种状态的最坏情况 并加上i层时操作1 并更新最小值
        //     tmp = Math.min(tmp,Math.max(eggBreak,eggUnBreak)+1);
        // }

        //第二种 利用二分查找的方式直接找到对应点(AC通过)
        //第一种线性逐个尝试切入点 然后求每个切入点两种状态的较大值 再求这些最大值之中的最小值 其实就是求这两条单调递增和单调递减直线的交点 相当于求上半部V形山谷值 二分查找来快速寻找这个点
        int lo=1,hi =n;
        while(lo<=hi){
            int mid =(lo+hi)/2;
            int eggBreak = dp(k-1,mid-1);
            int eggUnBreak = dp(k,n-mid);
            if(eggBreak>eggUnBreak){
                hi = mid-1;
                tmp = Math.min(tmp,eggBreak+1);
            }else{
                lo = mid+1;
                tmp = Math.min(tmp,eggUnBreak+1);
            }
        }

        //添加到备忘录里
        memo[k][n]=tmp;
        //返回当前k个鸡蛋n层楼时求解的子问题的结果
        return tmp;
    }


    public int eggDrop(int K, int N) {

        // dp[i][j]：一共有 i 层楼梯的情况下，使用 j 个鸡蛋的最少实验的次数
        // 注意：
        // 1、i 表示的是楼层的大小，不是第几层的意思，例如楼层区间 [8, 9, 10] 的大小为 3，这一点是在状态转移的过程中调整的定义
        // 2、j 表示可以使用的鸡蛋的个数，它是约束条件，我个人习惯放在后面的维度，表示消除后效性的意思

        // 0 个楼层和 0 个鸡蛋的情况都需要算上去，虽然没有实际的意义，但是作为递推的起点，被其它状态值所参考
        int[][] dp = new int[N + 1][K + 1];

        // 由于求的是最小值，因此初始化的时候赋值为一个较大的数，9999 或者 i 都可以
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], i);
        }

        // 初始化：填写下标为 0、1 的行和下标为 0、1 的列
        // 第 0 行：楼层为 0 的时候，不管鸡蛋个数多少，都测试不出鸡蛋的 F 值，故全为 0
        for (int j = 0; j <= K; j++) {
            dp[0][j] = 0;
        }

        // 第 1 行：楼层为 1 的时候，0 个鸡蛋的时候，扔 0 次，1 个以及 1 个鸡蛋以上只需要扔 1 次
        dp[1][0] = 0;
        for (int j = 1; j <= K; j++) {
            dp[1][j] = 1;
        }

        // 第 0 列：鸡蛋个数为 0 的时候，不管楼层为多少，也测试不出鸡蛋的 F 值，故全为 0
        // 第 1 列：鸡蛋个数为 1 的时候，这是一种极端情况，要试出 F 值，最少次数就等于楼层高度（想想复杂度的定义）
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
            dp[i][1] = i;
        }

        // 从第 2 行，第 2 列开始填表
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                for (int k = 1; k <= i; k++) {
                    // 碎了，就需要往低层继续扔：层数少 1 ，鸡蛋也少 1
                    // 不碎，就需要往高层继续扔：层数是当前层到最高层的距离差，鸡蛋数量不少
                    // 两种情况都做了一次尝试，所以加 1
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k - 1][j - 1], dp[i - k][j]) + 1);
                }
            }
        }
        return dp[N][K];
    }
}