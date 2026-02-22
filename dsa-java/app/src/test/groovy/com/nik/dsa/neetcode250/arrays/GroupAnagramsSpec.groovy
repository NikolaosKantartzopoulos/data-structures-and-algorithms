package com.nik.dsa.neetcode250.arrays

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class GroupAnagramsSpec extends Specification {

    @Subject
    GroupAnagrams solution = new GroupAnagrams()

    @Unroll
    def "groupAnagrams: should group #strs correctly"() {
        when:
            List<List<String>> result = solution.groupAnagrams(strs as String[])

        then: "sort nested lists and the outer list to ensure reliable comparison"
            // We sort the results because LeetCode allows any order of groups and elements
            def sortedResult = result.collect { it.sort() }.sort { it[0] }
            def sortedExpected = expected.collect { it.sort() }.sort { it[0] }

            sortedResult == sortedExpected

        where:
            strs                                     | expected
            ["eat", "tea", "tan", "ate", "nat", "bat"] | [["ate", "eat", "tea"], ["nat", "tan"], ["bat"]]
            [""]                                     | [[""]]
            ["a"]                                    | [["a"]]
            ["abc", "bca", "cab", "xyz", "zyx"]      | [["abc", "bca", "cab"], ["xyz", "zyx"]]
    }

    def "should handle strings with duplicate characters"() {
        given:
            String[] strs = ["bdddddddddd", "bbbbbbbbbbc"]

        when:
            def result = solution.groupAnagrams(strs)

        then:
            result.size() == 2
    }
}
