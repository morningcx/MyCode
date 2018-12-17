// my code 0ms 100%
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        return dfs(true, n) + dfs(false, n);
    }
    
    private int dfs(boolean isZero, int depth) {
        if (depth == 1) return isZero ? 1 : 9;
        if (isZero) {
            return dfs(true, depth - 1) + dfs(false, depth - 1);
        }
        int count = depth, num = 9, target = 9;
        // 这里存在冗余的计算
        while (count-- != 1) { // && num > 0
        // 这题其实n大于等于10以后结果就为固定值了，因为超过10必定存在重复的数字，所以while判断中加&& num > 0使时间复杂度变为恒定的O(1)
            target *= num--;  // 而这里恰好num会减成0，相乘之后的结果不影响最终结果
        }
        return target;
    }
}

// discuss dp思想，其实和我的思路一致，都是基于数学方法
 public int countNumbersWithUniqueDigits(int n) {
        if (n == 0)     return 1;
        int res = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n-- > 1 && availableNumber > 0) {
        // 这里把9X9,9X9X8这类的值利用变量缓存，减少冗余计算，这里做的比我好
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
        }
        return res;
    }
