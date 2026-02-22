package com.nik.dsa.neetcode250.arrays

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class TwoSumSpec extends Specification {

    @Subject
    TwoSum solution = new TwoSum()

    @Unroll
    def "twoSum: when nums is #nums and target is #target, then result should be #expected"() {
        expect: "the returned indices to match the expected pair"
            solution.twoSum(nums as int[], target) == expected as int[]

        where:
            nums         | target | expected
            [2, 7, 11, 15] | 9      | [0, 1]
            [3, 2, 4]      | 6      | [1, 2]
            [3, 3]         | 6      | [0, 1]
            [0, 4, 3, 0]   | 0      | [0, 3]
            [-1, -2, -3]   | -5     | [1, 2]
    }

    def "should return an empty array if no pair is found"() {
        given:
            int[] nums = [1, 2, 3]
            int target = 10

        when:
            int[] result = solution.twoSum(nums, target)

        then:
            result.length == 0
    }
}
