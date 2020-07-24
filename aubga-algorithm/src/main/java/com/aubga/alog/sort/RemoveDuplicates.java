package com.aubga.alog.sort;

public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {

        if(null == nums) return 0;
        if(nums.length == 1) return 1;

        int i=0,j=1;
        while(j<nums.length) {
            if(nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
            j++;
        }
        return i+1;
    }

    public static void main(String[] args) {
        RemoveDuplicates rd = new RemoveDuplicates();
        int result =  rd.removeDuplicates(new int[]{1,1,2,3,4,4,4,5,5,6});
        System.out.println(result);
    }
}
