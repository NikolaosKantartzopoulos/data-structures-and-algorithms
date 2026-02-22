from typing import List

"""
LeetCode 1929: Concatenation of Array
Find all implementations for joining an array with itself.
"""

def concatenation_of_array_double_pass(nums: List[int]) -> List[int]:
    """
    <h3>Double Pass Approach</h3>
    <p>Iterates twice and appends to a dynamic list.</p>
    <ul>
        <li><b>Time Complexity:</b> O(n)</li>
        <li><b>Space Complexity:</b> O(n)</li>
    </ul>
    """
    ans = []
    for _ in range(2):
        for num in nums:
            ans.append(num)
    return ans

def concatenation_of_array_one_pass(nums: List[int]) -> List[int]:
    """
    <h3>One Pass Approach</h3>
    <p>Uses a fixed-size list and assigns values to indices i and i+n simultaneously.</p>
    <ul>
        <li><b>Time Complexity:</b> O(n)</li>
        <li><b>Space Complexity:</b> O(n)</li>
    </ul>
    """
    n = len(nums)
    ans = [0] * (2 * n)
    for i, num in enumerate(nums):
        ans[i] = ans[i + n] = num
    return ans
