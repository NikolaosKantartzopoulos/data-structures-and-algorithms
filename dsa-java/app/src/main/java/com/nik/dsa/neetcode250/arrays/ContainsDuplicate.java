package com.nik.dsa.neetcode250.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * <h2>LeetCode 217: Contains Duplicate</h2>
 * <p>Given an integer array nums, return true if any value appears at least twice
 * in the array, and return false if every element is distinct.</p>
 * @see <a href="https://leetcode.com/problems/contains-duplicate/">Problem Link</a>
 */
public class ContainsDuplicate {

    /**
     * <h3>Hash Set Approach</h3>
     * <p>Uses a HashSet to store visited elements. If an element is already present in the set,
     * a duplicate has been found.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n) - We iterate through the array once; set lookups are O(1) average.</li>
     * <li><b>Space Complexity:</b> O(n) - In the worst case (no duplicates), all elements are stored in the set.</li>
     * </ul>
     */
    public boolean hasDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}
