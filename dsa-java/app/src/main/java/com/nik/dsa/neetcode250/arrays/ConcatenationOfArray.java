package com.nik.dsa.neetcode250.arrays;

/**
 * <h2>LeetCode 1929: Concatenation of Array</h2>
 * <p>Given an integer array nums of length n, create an array ans of length 2n
 * where ans is the concatenation of two nums arrays.</p>
 * * @see <a href="https://leetcode.com/problems/concatenation-of-array/">Problem Link</a>
 */
public class ConcatenationOfArray {

    /**
     * <h3>Two Pass Approach</h3>
     * <p>Iterates through the input array twice using nested loops to fill the result array.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n)</li>
     * <li><b>Space Complexity:</b> O(n) - to store the result array</li>
     * </ul>
     */
    public int[] getConcatenationTwoPass(int[] nums) {
        int[] ans = new int[2 * nums.length];
        int idx = 0;
        for (int i = 0; i < 2; i++) {
            for (int num : nums) {
                ans[idx++] = num;
            }
        }
        return ans;
    }

    /**
     * <h3>One Pass Approach</h3>
     * <p>Fills both the i-th and (i + n)-th indices simultaneously in a single iteration.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n)</li>
     * <li><b>Space Complexity:</b> O(n)</li>
     * </ul>
     */
    public int[] getConcatenationOnePass(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; i++) {
            ans[i] = ans[i + n] = nums[i];
        }
        return ans;
    }

    /**
     * <h3>System Copy Approach</h3>
     * <p>Uses Java's native <code>System.arraycopy</code> for highly optimized memory transfer.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n) - highly optimized</li>
     * <li><b>Space Complexity:</b> O(n)</li>
     * </ul>
     */
    public int[] getConcatenationSystemCopy(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        System.arraycopy(nums, 0, ans, 0, n);
        System.arraycopy(nums, 0, ans, n, n);
        return ans;
    }
}
