package com.aubga.alog.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeNumberSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        //边界条件判断
        if(nums.length < 3 || nums == null) {
            return result;
        }
        //排序,有序对于后续处理效率更高
        Arrays.sort(nums);

        //遍历一个数据,剩下的两个数用双指针法a+b+c=0,a+b=-c
        for(int i=0;i<nums.length-2;i++) {

            //判断第一个字符是否是负值
            if(nums[0] > 0) {
                return result;
            }

            //第一个数组出现重复后,继续
            if(i>0 && nums[i] == nums[i-1]) continue;

            //定义左右指针
            int target = -nums[i];
            int left = i+1;
            int right = nums.length - 1;
            //双指针法

            while(left < right) {
                //满足条件,保存解,指针进位
                if((nums[left] + nums[right]) == target) {
                    ArrayList<Integer> list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);

                    //去重
                    while(left < right && nums[left] == nums[left+1]) {
                        left++;
                    }
                    while(left < right && nums[right] == nums[right-1]) {
                        right--;
                    }
                    //去重后指针修正
                    left++;
                    right--;
                }else if(nums[left]+nums[right]>target) {
                    right--;
                }else {
                    left++;
                }
            }
        }
        return result;

    }

    public static void main(String[] args) {
        ThreeNumberSum tns = new ThreeNumberSum();

        System.out.println(tns.threeSum(new int[]{1,2,-2,-1}));
    }
}
