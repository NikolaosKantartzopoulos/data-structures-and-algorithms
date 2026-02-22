package com.nik.dsa.neetcode250.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>LeetCode 49: Group Anagrams</h2>
 * <p>Given an array of strings, group the anagrams together.
 * You can return the answer in any order.</p>
 * @see <a href="https://leetcode.com/problems/group-anagrams/">Problem Link</a>
 */
public class GroupAnagrams {

    /**
     * <h3>Sorting as Key Approach</h3>
     * <p>Each string is sorted to create a "canonical" version that serves as a unique key for the HashMap.
     * All anagrams will share the same sorted key.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n * k log k) - Where n is the number of strings and k is the max length of a string.
     * We sort n strings of length k.</li>
     * <li><b>Space Complexity:</b> O(n * k) - To store the groups in the HashMap.</li>
     * </ul>
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedString = new String(charArray);
            map.putIfAbsent(sortedString, new ArrayList<>());
            map.get(sortedString).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
