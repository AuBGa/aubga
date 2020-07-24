package com.aubga.alog.sort;

public class IntegerPalindrome {
    public boolean isPalindrome(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;

            System.out.println("reversed:"+reversed+",x:"+x);
        }
        return x == reversed || x == reversed / 10;

/*
        if(x == 0) return true;
        if(x < 0 || x%10 ==0) return false;

        int reversed = 0;
        while( x > reversed) {
            reversed = reversed*10 + x%10;
            x /= 10;
        }
        return x == reversed || x == reversed /10;*/
    }


    public static void main(String[] args) {
        IntegerPalindrome ip = new IntegerPalindrome();
        boolean res =  ip.isPalindrome(123454321);
        System.out.println(res);
    }
}
