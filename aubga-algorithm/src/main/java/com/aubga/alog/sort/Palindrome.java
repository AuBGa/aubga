package com.aubga.alog.sort;

public class Palindrome {

        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }
            if (s.length() == 1) {
                return s;
            }
            int beginIdx = 0, len = 1;
            for (int i = 0; i < s.length() - 1; i++) {
                for (int j = i + 1; j < s.length(); j++) {

                    boolean flag = isPalindrome(s, i, j);
                    System.out.println("i:"+i+",j:"+j+",flag:"+flag+",len:"+len);
                    if (j - i + 1 > len && flag) {
                        len = j - i + 1;
                        beginIdx = i;
                    }
                }
            }
            return s.substring(beginIdx, beginIdx + len);
        }

        private boolean isPalindrome(String s, int i, int j) {

            while (i < j) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
                i++;
                j--;
            }
            return true;
        }


    public String longestPalindromeByDP(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        int beginIdx = 0, len = 1;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i <= j - 1; i++) {
                //System.out.println("i:"+i+",j:"+j+",dp[i + 1][j - 1]:"+dp[i + 1][j - 1]+",i+1:"+(i+1)+",j-1:"+(j-1));
                if (s.charAt(i) == s.charAt((j)) && (j - i + 1 <= 3 || dp[i + 1][j - 1])) {
                  //  System.out.println("dp[i + 1][j - 1]:"+dp[i + 1][j - 1]+",i+1:"+(i+1)+",j-1:"+(j-1));
                    dp[i][j] = true;
                    if (j - i + 1 > len) {
                        beginIdx = i;
                        len = j - i + 1;
                    }
                }
            }
        }
        //打印数组的值
        for (int i=0;i<dp.length;i++) {
            boolean[] b = dp[i];
            for(int j=0;j<b.length;j++) {
                System.out.println("dp["+i+","+j+"]:"+dp[i][j]);
            }
        }
        return s.substring(beginIdx, beginIdx + len);
    }

        public static void main(String[] args) {
            Palindrome pd = new Palindrome();
           String res =  pd.longestPalindromeByDP("ababac");
           System.out.println(res);
        }
}
