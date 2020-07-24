package com.aubga.alog.BacktrackingAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BackTrace {
    List<List<Integer>> res = new ArrayList<>();

    List<List<Integer>> permute(int[] nums) {
        //记录  路径
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums,track);
        return res;
    }

    private void backtrack(int[] nums,LinkedList<Integer> track) {
        if(track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for(int i=0;i<nums.length;i++) {
            //排除不合法
            if(track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);

            backtrack(nums,track);

            Integer last = track.removeLast();
            System.out.println("last:"+last);
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};

        BackTrace bt = new BackTrace();
        List<List<Integer>> list = bt.permute(nums);

        for(int i=0;i<list.size();i++) {
            System.out.println(list.get(i).toString());
        }
    }
}
