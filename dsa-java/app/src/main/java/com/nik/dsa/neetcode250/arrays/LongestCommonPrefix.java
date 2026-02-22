package com.nik.dsa.neetcode250.arrays;

/**
 * <h2>LeetCode 14: Longest Common Prefix</h2>
 * <p>Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".</p>
 * @see <a href="https://leetcode.com/problems/longest-common-prefix/">Problem Link</a>
 */
public class LongestCommonPrefix {

    /**
     * <h3>Vertical Scanning Approach</h3>
     * <p>Compares characters at the same index across all strings.
     * Stops and returns the prefix as soon as a mismatch is found or the end of a string is reached.</p>
     * <ul>
     * <li><b>Time Complexity:</b> O(S) - Where S is the sum of all characters in all strings.
     * In the worst case, all strings are identical.</li>
     * <li><b>Space Complexity:</b> O(1) - Constant extra space used beyond the output string.</li>
     * </ul>
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int i = 0; i < strs[0].length(); i++){
            char c = strs[0].charAt(i);
            for (String s : strs) {
                // Check if we've reached the end of current string 's'
                // or if the character at 'i' doesn't match
                if (i == s.length() || s.charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
