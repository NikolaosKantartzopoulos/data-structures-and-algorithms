import pytest
from .concatination_of_array import concatenation_of_array_double_pass, concatenation_of_array_one_pass

impls = [
    concatenation_of_array_double_pass,
    concatenation_of_array_one_pass
]

@pytest.mark.parametrize("func", impls)
@pytest.mark.parametrize("nums, expected", [
    ([1, 2, 1], [1, 2, 1, 1, 2, 1]),
    ([1, 3, 2, 1], [1, 3, 2, 1, 1, 3, 2, 1]),
    ([1], [1, 1]),
    ([], [])
])
def test_concatenation(func, nums, expected):
    # We pass a copy of nums if we were doing in-place mods, 
    # but here it's fine as we return new lists.
    assert func(nums) == expected
