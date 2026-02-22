/**
 * <h2>Contains Duplicate</h2>
 * @see {@link https://neetcode.io/solutions/contains-duplicate}
 */

/**
 * <h3>Approach 1: Sorting</h3>
 * <p>Sorts the array and checks if adjacent elements are identical.</p>
 * <ul>
 * <li><b>Time Complexity:</b> O(n log n)</li>
 * <li><b>Space Complexity:</b> O(1) or O(n) depending on JS engine sort implementation</li>
 * </ul>
 */
function hasDuplicateSort(nums) {
    // JS sort is lexicographical by default; (a, b) => a - b is required for numbers
    nums.sort((a, b) => a - b);
    for (let i = 1; i < nums.length; i++) {
        if (nums[i] === nums[i - 1]) {
            return true;
        }
    }
    return false;
}

/**
 * <h3>Approach 2: Hash Set</h3>
 * <p>Uses a Set to track seen numbers for O(1) average lookup time.</p>
 * <ul>
 * <li><b>Time Complexity:</b> O(n)</li>
 * <li><b>Space Complexity:</b> O(n)</li>
 * </ul>
 */
function hasDuplicateSet(nums) {
    const seen = new Set();
    for (const num of nums) {
        if (seen.has(num)) {
            return true;
        }
        seen.add(num);
    }
    return false;
}

module.exports = {
    hasDuplicateSort,
    hasDuplicateSet
};
