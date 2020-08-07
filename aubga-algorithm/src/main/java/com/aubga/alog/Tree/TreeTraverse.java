package com.aubga.alog.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TreeTraverse {

    //前序遍历
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        preOrderHelper(root,res);
        return res;
    }

    //前序遍历核心知识点
    private void preOrderHelper(TreeNode root, List<Integer> res) {
        if(root != null) {
            res.add(root.val);
            if(root.left != null) {
                preOrderHelper(root.left,res);
            }
            if(root.right != null) {
                preOrderHelper(root.right,res);
            }
        }

        /*if(root == null)
            return;
        res.add(root.val);
        preOrderHelper(root.left,res);
        preOrderHelper(root.right,res);*/
    }

    public List<Integer> stack_preOrderHelper(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                res.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            }

            if(!stack.isEmpty()) {
                curr = stack.pop();
                curr = curr.right;
            }

        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderHelper(root,res);
        return res;
    }

    private void inorderHelper(TreeNode root, List<Integer> res) {
        if(root == null)
            return;

        inorderHelper(root.left,res);
        res.add(root.val);
        inorderHelper(root.right,res);
    }

    public List<Integer> stack_inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()) {
            //push the left tree into stack
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            //when reached left node
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrderHelper(root,res);
        return res;
    }

    private void postOrderHelper(TreeNode root, List<Integer> res) {
        if(root == null)
            return;

        postOrderHelper(root.left,res);
        postOrderHelper(root.right,res);
        res.add(root.val);
    }

    public List<Integer> statck_postOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        TreeNode curr = root;
        int left = 1;  // note left tree
        int right = 2; // note right tree

        while (curr != null || !stack.isEmpty()) {

            while (curr != null) {
                stack.push(curr);
                stack2.push(left);
                curr = curr.left;
            }

            while (!stack.isEmpty() && stack2.peek() == right) {
                // 如果是从左子节点返回父节点，则任务完成，将两个栈的栈顶弹出，记录结果
                stack2.pop();
                res.add(stack.pop().val);
            }

            if (!stack.isEmpty() && stack2.peek() == left) {
                // 如果是从左子树返回，则先将辅助栈弹出栈顶，下一个应该压栈的是右子树
                //
                stack2.pop();
                stack2.push(right);
                curr = stack.peek().right;

            }

        }


        return res;
    }
}
