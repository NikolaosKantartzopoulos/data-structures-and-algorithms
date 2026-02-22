package com.nik.dsa.neetcode250.arrays;

/**
 * <h2>LeetCode 27: Remove Element</h2>
 * <p>Given an integer array nums and an integer val, remove all occurrences of val
 * in-place. The order of elements can be changed.</p>
 * @see <a href="https://leetcode.com/problems/remove-element/">Problem Link</a>
 */
public class RemoveElement {

    /**
     * <h3>Two Pointers - Opposite Ends Approach</h3>
     * <p>When a target value is found at the left pointer, it is replaced with the
     * last element of the current valid range. This minimizes array writes when
     * the target value is rare.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n) - Each element is inspected at most once.</li>
     * <li><b>Space Complexity:</b> O(1) - The modification is performed in-place.</li>
     * </ul>
     */
    public int removeElement(int[] nums, int val) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            if (nums[l] == val) {
                // Move the last element to the current index
                // Note: l is not incremented so we can check the new value at nums[l]
                nums[l] = nums[--r];
            } else {
                l++;
            }
        }
        return r;
    }
}
