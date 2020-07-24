package com.aubga.alog.sort;

public class Reverse {

    public int reverse(int x) {
        long rs = 0;
        while(x != 0){
           // System.out.println(x);
            rs = rs*10+x%10;
            System.out.println(rs);
            x /= 10;  //  a/=30;等于a=a/30;
        }
       // System.out.println(rs);
        return (rs<Integer.MIN_VALUE || rs>Integer.MAX_VALUE) ? 0:(int)rs;
    }

    public static void main(String[] args) {
        Reverse r = new Reverse();
        int result = r.reverse(1534236469);
       // System.out.println(2147483647-1056389759);
        System.out.println(result);
    }
}
