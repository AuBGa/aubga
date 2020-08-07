package com.aubga.alog.Tree;


import java.util.LinkedList;
import java.util.List;

public class UniqueBinarySearchTreesii {
    public List<TreeNode> generateTrees(int n) {
        if(n==0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1,n);
    }

    public List<TreeNode> generateTrees(int start,int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if(start > end) {
            allTrees.add(null);
            return allTrees;
        }
        for(int i=start;i<=end;i++) {
            List<TreeNode> leftTrees = generateTrees(start,i-1);
            List<TreeNode> rightTrees = generateTrees(i+1,end);

            for(TreeNode left:leftTrees) {
                for(TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

    public static void main(String[] args) {
        UniqueBinarySearchTreesii ubs = new UniqueBinarySearchTreesii();
        List<TreeNode> list = ubs.generateTrees(3);
        for(TreeNode tree : list) {
            System.out.println(tree.toString());
        }

        List<TreeNode> testList = new LinkedList<>();
        testList.add(null);
        testList.add(null);
        System.out.println(testList.size());
    }
}
