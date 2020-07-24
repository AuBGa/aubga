package com.aubga.alog.sort;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentthese {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        //System.out.println(stack);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else {
                System.out.println(stack.toString());
                stack.pop();
                if (stack.isEmpty()) stack.push(i);
                else {
                    System.out.print(stack.peek()+"------:");
                    res = Math.max(res, i - stack.peek());
                    System.out.println(res);
                }
            }
        }
        return res;
    }

    public int longestValidParentheses_2(String s) {

        //
        if(s == null || s.length() == 0) return 0;
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for(int i=0;i<s.length();i++) {

            if(s.charAt(i) == '(') {
                stack.push(i);
            }else {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                }else {
                    res = Math.max(res,i-stack.peek());
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LongestValidParentthese lvp = new LongestValidParentthese();

        int result = lvp.longestValidParentheses_2(")((()))");
        System.out.println(result);
    }
}
