import pytest
from .two_sum import two_sum_map, two_sum_brute

implementations = [two_sum_map, two_sum_brute]

@pytest.mark.parametrize("func", implementations)
@pytest.mark.parametrize("nums, target, expected", [
    ([2, 7, 11, 15], 9, [0, 1]),
    ([3, 2, 4], 6, [1, 2]),
    ([3, 3], 6, [0, 1]),
    ([1, 5, 9], 10, [0, 2])
])
def test_two_sum(func, nums, target, expected):
    assert func(nums, target) == expected
