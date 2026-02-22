package com.nik.dsa.neetcode250.arrays

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class ValidAnagramSpec extends Specification {

    @Subject
    ValidAnagram solution = new ValidAnagram()

    @Unroll
    def "Implementation #methodName: should return #expected for s='#s' and t='#t'"() {
        expect: "the method to correctly identify if the strings are anagrams"
            solution."$methodName"(s, t) == expected

        where:
            methodName               | s          | t          | expected
            "isAnagramMapSolution"   | "anagram"  | "nagaram"  | true
            "isAnagramMapSolution"   | "rat"      | "car"      | false
            "isAnagramMapSolution"   | "a"        | "ab"       | false
            "isAnagramMapSolution"   | ""         | ""         | true

            "isAnagramArraySolution" | "anagram"  | "nagaram"  | true
            "isAnagramArraySolution" | "rat"      | "car"      | false
            "isAnagramArraySolution" | "a"        | "ab"       | false
            "isAnagramArraySolution" | "racecar"  | "carrace"  | true
    }

    def "should handle characters with different frequencies but same set"() {
        given: "strings with same characters but different counts"
            String s = "aaabb"
            String t = "ababa"

        expect:
            solution.isAnagramMapSolution(s, t)
            solution.isAnagramArraySolution(s, t)
    }
}
