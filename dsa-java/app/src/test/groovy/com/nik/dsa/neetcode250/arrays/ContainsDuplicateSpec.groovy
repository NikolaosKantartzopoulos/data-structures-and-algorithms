package com.nik.dsa.neetcode250.arrays

import spock.lang.Specification

class ContainsDuplicateSpec extends Specification {

    def "should return true if array contains duplicates, false otherwise"() {
        given:
            def solution = new ContainsDuplicate()

        expect:
            solution.hasDuplicate(nums as int[]) == expected

        where:
            nums                  || expected
            [1, 2, 3, 1]          || true
            [1, 2, 3, 4]          || false
            [1, 1, 1, 3, 3, 4, 3] || true
            []                    || false
            [1]                   || false
    }
}
