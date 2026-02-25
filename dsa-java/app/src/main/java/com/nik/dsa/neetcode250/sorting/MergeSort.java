package com.nik.dsa.neetcode250.sorting;

/*
Given an array of integers nums, sort the array in ascending order and return it.

You must solve the problem without using any built-in
functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
 */
public class MergeSort {
    public int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void mergeSort(int[] nums, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = l + (r - l) / 2;

        //Divide
        mergeSort(nums, l, m);
        mergeSort(nums, m + 1, r);

        // Conquer
        merge(nums, l, m, r);
    }

    public void merge(int[] nums, int l, int m, int r) {
        int[] tempArray = new int[r - l + 1];

        int pl = l;
        int pr = m + 1;
        int writePointer = 0;

        while (pl <= m && pr <= r) {
            if (nums[pl] <= nums[pr]) {
                tempArray[writePointer] = nums[pl];
                pl++;
            } else {
                tempArray[writePointer] = nums[pr];
                pr++;
            }
            writePointer++;
        }

        while (pl <= m) {
            tempArray[writePointer++] = nums[pl++];
        }

        while (pr <= r) {
            tempArray[writePointer++] = nums[pr++];
        }

        for (int i = 0; i < tempArray.length; i++) {
            nums[l + i] = tempArray[i];
        }
    }

}
