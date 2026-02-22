from typing import List


def two_sum_brute(nums: List[int], target: int) -> List[int]:
    """
    Brute Force Approach
    Time: O(n^2) | Space: O(1)
    """
    for i in range(len(nums)):
        for j in range(i + 1, len(nums)):
            if nums[i] + nums[j] == target:
                return [i, j]
    return []

def two_sum_map(nums: List[int], target: int) -> List[int]:
    """
    Hash Map Approach (Optimal)
    Time: O(n) | Space: O(n)
    """
    seen = {}
    for i,n in enumerate(nums):
        complementary = target - n
        if complementary in seen:
            return [seen[complementary], i]
        seen[n] = i
    return []
