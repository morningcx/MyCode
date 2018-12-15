// my code 0ms
class Solution {
    public boolean isPerfectSquare(int num) {
        int start = 0;
        int end = Math.min(num, 46340);
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int target = mid * mid;
            if (target > num) {
                end = mid - 1;
            } else if (target < num) {
                start = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}

// discuss
class Solution {
    public boolean isPerfectSquare(int num) {
        int l = 0, r = num;
        while (l < r) {
            int m = l + (r - l) / 2 + 1;
            if (num / m >= m) l = m; // 用除法来防止overflow
            else r = m - 1;
        }
        return l * l == num;
    }
}

// 牛顿法
public boolean isPerfectSquare(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) >> 1;
        }
        return x * x == num;
    }
