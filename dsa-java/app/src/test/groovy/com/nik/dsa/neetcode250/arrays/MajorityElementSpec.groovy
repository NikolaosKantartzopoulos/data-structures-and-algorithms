package com.nik.dsa.neetcode250.arrays

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class MajorityElementSpec extends Specification {

    @Subject
    MajorityElement solution = new MajorityElement()

    @Unroll
    def "Implementation #methodName: should find #expected in #nums"() {
        expect: "the method to correctly identify the majority element"
            solution."$methodName"(nums as int[]) == expected

        where:
            methodName << ["majorityElement", "majorityElementHashMap", "majorityElementSorting"].multiply(6).sort()

            [nums, expected] << [
                    [[3, 2, 3], 3],
                    [[2, 2, 1, 1, 1, 2, 2], 2],
                    [[1], 1],
                    [[1, 1, 1, 2, 2], 1],
                    [[6, 5, 5], 5],
                    [[4, 4, 2, 4, 3, 4, 4], 4],
            ] * 3
    }
}
