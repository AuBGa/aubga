package com.aubga.alog.Tree;

public class MaxGain {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return  maxSum;
    }

    private int maxGain(TreeNode root) {
        if(root == null) {
            return 0;
        }

        //递归计算左右子节点的最大贡献值
        //只有在最大贡献值大于0时，才会选择对应子节点
        int leftGain = Math.max(maxGain(root.left),0);
        int rightGain = Math.max(maxGain(root.right),0);

        //节点最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewPath = root.val + leftGain + rightGain;

        //更新答案
        maxSum = Math.max(maxSum,priceNewPath);

        //返回节点的最大贡献值
        return root.val + Math.max(leftGain,rightGain);
    }
}
