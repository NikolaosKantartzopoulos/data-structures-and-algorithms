package com.nik.dsa.neetcode250.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Valid Anagram</h2>
 * <p>Given two strings s and t, return true if t is an anagram of s, and false otherwise.</p>
 * @see <a href="https://neetcode.io/solutions/valid-anagram">NeetCode Solution</a>
 */
public class ValidAnagram {

    /**
     * <h3>Hash Map Approach</h3>
     * <p>Uses a single frequency map to increment counts for string 's' and decrement
     * for string 't'. A result of all zeros indicates an anagram.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n) - Single pass through strings of length n.</li>
     * <li><b>Space Complexity:</b> O(k) - Where k is the number of unique characters.</li>
     * </ul>
     */
    public boolean isAnagramMapSolution(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            map.put(sChar, map.getOrDefault(sChar, 0) + 1);
            map.put(tChar, map.getOrDefault(tChar, 0) - 1);
        }

        for (int val : map.values()) {
            if (val != 0) return false;
        }
        return true;
    }

    /**
     * <h3>Fixed Array Optimization</h3>
     * <p>Optimized for lowercase English letters (a-z) using a size-26 integer array.
     * Avoids the overhead of HashMap object creation and autoboxing.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(n)</li>
     * <li><b>Space Complexity:</b> O(1) - The array size is constant (26).</li>
     * </ul>
     */
    public boolean isAnagramArraySolution(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }

        for (int count : counts) {
            if (count != 0) return false;
        }
        return true;
    }
}
