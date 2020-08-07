package com.aubga.alog.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderBottom {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new LinkedList<>();

        if(root == null)
            return ans;

        queue.offer(root);
        while(!queue.isEmpty()) {
            int levelNum = queue.size();

            List<Integer> subList = new LinkedList<>();
            for(int i=0;i<levelNum;i++) {
                TreeNode curNode = queue.poll();
                if(curNode != null) {
                    subList.add(curNode.val);
                    queue.offer(curNode.left);
                    queue.offer(curNode.right);
                }
            }
            if(!subList.isEmpty()) {
                ans.add(0,subList);
            }
         }
         return  ans;
    }
}
