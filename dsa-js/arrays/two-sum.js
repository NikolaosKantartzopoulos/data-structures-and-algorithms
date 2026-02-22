/**
 * <h2>Two Sum - Implementations</h2>
 * @see {@link https://neetcode.io/solutions/two-sum}
 */

/**
 * <h3>Approach 1: Hash Map (Optimal)</h3>
 * <ul>
 * <li><b>Time Complexity:</b> O(n)</li>
 * <li><b>Space Complexity:</b> O(n)</li>
 * </ul>
 */
function twoSumMap(nums, target) {
    const map = new Map();
    for (let i = 0; i < nums.length; i++) {
        const complementary = target - nums[i];
        if (map.has(complementary)) return [map.get(complementary), i];
        map.set(nums[i], i);
    }
    return [];
}

/**
 * <h3>Approach 2: Brute Force</h3>
 * <p>Checks every possible pair using nested loops.</p>
 * <ul>
 * <li><b>Time Complexity:</b> O(n²)</li>
 * <li><b>Space Complexity:</b> O(1)</li>
 * </ul>
 */
function twoSumBruteForce(nums, target) {
    for (let i = 0; i < nums.length; i++) {
        for (let j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] === target) return [i, j];
        }
    }
    return [];
}

module.exports = {
    twoSumMap,
    twoSumBruteForce
};
