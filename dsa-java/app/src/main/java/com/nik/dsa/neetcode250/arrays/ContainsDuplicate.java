package com.nik.dsa.neetcode250.arrays;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
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
