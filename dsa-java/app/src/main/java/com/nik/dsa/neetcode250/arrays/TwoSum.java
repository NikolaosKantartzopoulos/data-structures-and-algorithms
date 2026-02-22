package com.nik.dsa.neetcode250.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Two Sum</h2>
 * <p>Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.</p>
 * @see <a href="https://neetcode.io/solutions/two-sum">NeetCode Solution</a>
 */
public class TwoSum {

    /**
     * <h3>One-Pass Hash Map Approach</h3>
     * <p>Iterates through the array once, calculating the complement for each element.
     * If the complement exists in the map, the pair is found. Otherwise, the current
     * value and index are stored for future lookups.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n) - We traverse the list of n elements only once.
     * Each lookup in the table costs only O(1) time.</li>
     * <li><b>Space Complexity:</b> O(n) - The extra space required depends on the
     * number of items stored in the hash table, which stores at most n elements.</li>
     * </ul>
     */
    public int[] twoSum(int[] nums, int target) {
        // Key is the value from the array
        // Value is the index where that value is located
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complementary = target - nums[i];

            if (map.containsKey(complementary)) {
                return new int[] {map.get(complementary), i};
            }

            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
