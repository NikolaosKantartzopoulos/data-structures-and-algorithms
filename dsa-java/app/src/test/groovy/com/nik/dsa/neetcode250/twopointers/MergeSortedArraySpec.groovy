package com.nik.dsa.neetcode250.twopointers

import spock.lang.Specification

class MergeSortedArraySpec extends Specification {
    def "should merge two sorted arrays in place"() {
        given:
            int[] nums1 = input1 as int[]
            int[] nums2 = input2 as int[]

        when:
            new MergeSortedArray().mergeThreePointers(nums1, m, nums2, n)

        then:
            nums1 == expected as int[]

        where:
            input1                | m | input2    | n | expected
            [1, 2, 3, 0, 0, 0]    | 3 | [2, 5, 6] | 3 | [1, 2, 2, 3, 5, 6]
            [1]                   | 1 | []        | 0 | [1]
            [0]                   | 0 | [1]       | 1 | [1]
            [4, 5, 6, 0, 0, 0]    | 3 | [1, 2, 3] | 3 | [1, 2, 3, 4, 5, 6]
    }
}
