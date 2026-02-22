package com.nik.dsa.neetcode250.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h2>LeetCode 169: Majority Element</h2>
 * <p>Find the element that appears more than floor(n/2) times.</p>
 */
public class MajorityElement {

    /**
     * <h3>Boyer-Moore Voting Algorithm (Optimal)</h3>
     * <p>Uses a candidate and a counter to find the majority element in a single pass.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n)</li>
     * <li><b>Space Complexity:</b> O(1)</li>
     * </ul>
     */
    public int majorityElement(int[] nums) {
        int res = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                res = num;
            }
            count += (num == res) ? 1 : -1;
        }
        return res;
    }

    /**
     * <h3>Hash Map Approach</h3>
     * <p>Counts frequencies of each number and tracks the maximum.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n)</li>
     * <li><b>Space Complexity:</b> O(n)</li>
     * </ul>
     */
    public int majorityElementHashMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;
        int res = 0;

        for (int num : nums) {
            int currentCount = map.getOrDefault(num, 0) + 1;
            map.put(num, currentCount);
            if (currentCount > maxCount) {
                maxCount = currentCount;
                res = num;
            }
        }
        return res;
    }

    /**
     * <h3>Sorting Approach</h3>
     * <p>The majority element will always be at the center index after sorting.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n log n)</li>
     * <li><b>Space Complexity:</b> O(log n)</li>
     * </ul>
     */
    public int majorityElementSorting(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
