package com.nik.dsa.neetcode250.arrays

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class LongestCommonPrefixSpec extends Specification {

    @Subject
    LongestCommonPrefix solution = new LongestCommonPrefix()

    @Unroll
    def "longestCommonPrefix: when input is #strs, then result should be '#expected'"() {
        expect: "the method to return the correct common prefix"
            solution.longestCommonPrefix(strs as String[]) == expected

        where:
            strs                              | expected
            ["flower", "flow", "flight"]      | "fl"
            ["dog", "racecar", "car"]         | ""
            ["interspecies", "interstellar", "interstate"] | "inters"
            ["apple", "apple", "apple"]       | "apple"
            ["throne", "throne"]              | "throne"
            ["a"]                             | "a"
            ["", "b"]                         | ""
            ["ab", "a"]                       | "a"
    }

    def "should return the full string if all strings are identical"() {
        given:
            String[] strs = ["leetcode", "leetcode", "leetcode"]

        expect:
            solution.longestCommonPrefix(strs) == "leetcode"
    }
}
