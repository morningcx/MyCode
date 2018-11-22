// my code 100% 动态规划，先求前n-1个数的最大值，然后两个数构成n的乘积的最大值
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n ; ++i) {
            dp[i] = getProductMax(dp, i - 1);
        }
        return dp[n];
    }
    
    private int getProductMax(int[] dp, int end) {
        int max = Integer.MIN_VALUE;
        int start = 1;
        // 有四种情况
        while (start <= end) {
            max = Math.max(max, start * end);
            max = Math.max(max, start * dp[end]);
            max = Math.max(max, dp[start] * end);
            max = Math.max(max, dp[start++] * dp[end--]);
        }
        return max;
    }
}

// discuss简洁的写法
public int integerBreak(int n) {
    int[] dp = new int[n + 1];
   dp[1] = 1;
   for(int i = 2; i <= n; i ++) {
       for(int j = 1; 2*j <= i; j ++) {
           dp[i] = Math.max(dp[i], (Math.max(j,dp[j])) * (Math.max(i - j, dp[i - j])));
       }
   }
   return dp[n];
}
}

// 分成3的写法，分成2相乘没有分成3相乘来的大，小于等于4的除外
public class Solution {
    public int integerBreak(int n) {
        if(n==2) return 1;
        if(n==3) return 2;
        int product = 1;
        // n为4时需要分成2 * 2 而不是1 * 3
        while(n>4){
            product*=3;
            n-=3;
        }
        product*=n;
        
        return product;
    }
}
