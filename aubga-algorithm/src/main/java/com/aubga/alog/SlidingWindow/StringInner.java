package com.aubga.alog.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class StringInner {

    public static void main(String[] args) throws Exception {

        String test = "1234";
        System.out.println(test.length());
/*
        for(int i=0;i<test.length();i++) {
            System.out.println(test.charAt(i));
        }*/

        String ori = "ADOBECODEBANC";
        String aim = "ABC";

        System.out.println(minString(ori,aim));

  /*      for(int i=0;i<ori.length();i++) {
            for(int j=i+1;j<ori.length();j++) {
                String temp = ori.substring(i,j+1);//当前字符子串
                 System.out.println(temp);
                if(isAllContain(temp,aim)) {
                  //  System.out.println(temp);
                }
            }
        }*/
    }

 /*   private static boolean isAllContain(String temp, String aim) {
        int count = 0;

        for(int i=0;i<aim.length();i++) {
            for(int j = 0;j<temp.length();j++) {
                if(temp.charAt(j) == aim.charAt(i)) {
                    count++;
                }
            }
        }

        return count == aim.length();
    }*/


    public static String minString(String ori,String aim) {

        int left =0,right = 0;
        Map<Character,Integer> need = new HashMap<>();//需要的字符及次数
        for(int i=0;i<aim.length();i++) {
            Character key = aim.charAt(i);
            need.put(key,need.get(key) != null ? need.get(key) +1 : 1);
        }

        Map<Character,Integer> window = new HashMap<>();//窗口
        for(int i=0;i<aim.length();i++) {
           window.put(aim.charAt(i),0);
        }

        //定义最大长度
        int match = 0;
        int begin = 0;
        int minMatch = Integer.MAX_VALUE;
        while(right < ori.length()) {

            Character rightChar = ori.charAt(right);
            //右侧递进
            if(need.containsKey(rightChar)) {
                window.put(rightChar,window.get(rightChar)+1);
            }
            if(window.get(rightChar) == need.get(rightChar)) {
                match++;
            }
            right++;
            //左侧逼近
            while(match == need.size() && left <= right) {
                if(minMatch < (right-left)) {
                    minMatch = right-left;
                    begin = left;
                }
                Character leftChar = ori.charAt(left);
                if(need.containsKey(leftChar)) {
                    window.put(leftChar,window.get(leftChar)-1);
                    if(window.get(leftChar) < need.get(leftChar)) {
                        match--;
                    }
                }
                left++;
            }


        }
        return minMatch == Integer.MAX_VALUE ? "" : ori.substring(begin,minMatch);
    }
}
