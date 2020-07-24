package com.aubga.alog.sort;

class BSearch {
    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private int search(int[] nums, int low, int high, int target) {
        if (low > high)
            return -1;
        int mid = (low + high) / 2;
        if (nums[mid] == target)
            return mid;
        if (nums[mid] < nums[high]) {
            if (nums[mid] < target && target <= nums[high])
                return search(nums, mid + 1, high, target);
            else
                return search(nums, low, mid - 1, target);
        } else {
            if (nums[low] <= target && target < nums[mid])
                return search(nums, low, mid - 1, target);
            else
                return search(nums, mid + 1, high, target);
        }
    }

    public static void main(String[] args) {
        BSearch bs = new BSearch();

        int result = bs.search(new int[]{4,5,6,7,0,1,2},4);

        System.out.println(result);
    }
}
