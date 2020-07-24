package com.aubga.alog.sort;

public class ZShow {

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < n; j += (numRows - 1) * 2) {//处理竖行
                sb.append(s.charAt(j));
                if (i > 0 && i < numRows - 1) {
                    int sec = j + 2 * (numRows - i - 1);
                    if (sec < n) {
                        sb.append(s.charAt(sec));//处理斜行
                    }
                }
            }
        }
        return sb.toString();
    }


    public String convert2(String s, int numRows) {
        if(numRows == 1) return s;
        if(s == null || s.length() <= 1) return s;

        int round = 2*numRows - 2;
        StringBuffer buffer = new StringBuffer();
        for(int r=0;r<numRows;r++) {
            for(int i=0;i<s.length();i++) {
                if(i%round == r || i%round == (round-r)) {
                    buffer.append(s.charAt(i));
                }
            }
        }
        return buffer.toString();
    }

/*  0     6      12        18
    1   5 7   11 13    17
    2 4   8 10   14 16
    3     9      15          */




    public static void main(String[] args) {
        ZShow show = new ZShow();
        String res =  show.convert2("LEETCODEISHIRING",5);
        String res1 =  show.convert("LEETCODEISHIRING",5);
        System.out.println(res);
        System.out.println(res1);
    }
}
