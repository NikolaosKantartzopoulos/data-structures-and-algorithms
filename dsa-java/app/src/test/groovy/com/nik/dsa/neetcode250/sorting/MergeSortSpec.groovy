package com.nik.dsa.neetcode250.sorting

import spock.lang.Specification
import spock.lang.Subject

class MergeSortSpec extends Specification {

    @Subject
    MergeSort sorter = new MergeSort()

    def "should sort arrays of varying lengths and contents in ascending order"() {
        expect: "The returned array is sorted correctly"
            sorter.sort(input as int[]) == expected as int[]

        where:
            input              | expected
            [5, 2, 3, 1]       | [1, 2, 3, 5]            // Standard unsorted
            [5, 1, 1, 2, 0, 0] | [0, 0, 1, 1, 2, 5]      // Duplicates and zeros
            [10, -1, 0, 8, -5] | [-5, -1, 0, 8, 10]      // Negative numbers
            [1]                | [1]                     // Single element
            []                 | []                      // Empty array
            [5, 4, 3, 2, 1]    | [1, 2, 3, 4, 5]         // Reverse sorted
            [1, 2, 3, 4, 5]    | [1, 2, 3, 4, 5]         // Already sorted
    }

    def "should handle large values within constraints"() {
        given: "An array with the maximum and minimum allowed values"
            int[] nums = [50000, -50000, 0, 100, -100]

        when:
            int[] result = sorter.sort(nums)

        then:
            result == [-50000, -100, 0, 100, 50000] as int[]
    }
}
