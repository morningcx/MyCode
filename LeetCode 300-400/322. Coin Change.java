//my code 398ms 2%  dfs穷举法，删减一些不必要的递归，至少做出来了....
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins);
        int num = maxCoin(coins, amount, 1, coins.length - 1, Integer.MAX_VALUE, amount);
        return num == Integer.MAX_VALUE ? -1 : num;
    }
    
    private int maxCoin(int[] coins, int amount, int index, int start, int min, int total) {
        if (index >= min) return min;
        if (min != Integer.MAX_VALUE && (amount / coins[start]) >= min) {
            return min;
        }
        if (amount % coins[start] == 0) {
            return index + amount / coins[start] - 1;
        }
        for (int i = start; i >= 0; --i) {
            int sub = amount - coins[i];
            if (sub == 0) return index;
            if (sub > 0) {
                min = Math.min(min, maxCoin(coins, sub, index + 1, i, min, total));
            }
        }
        return min;
    }
}

// dp 28ms 48% 缺点：amount过大会造成内存溢出
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 填充数组为amount + 1
        dp[0] = 0; // 0不需要任何数组合
        for (int coin : coins) { // 遍历每个coin，判断从coin到amount的数字中的组合情况
            for (int i = coin; i < dp.length; ++i) {
                //i - coin可以由i - coin + coin组成，所以组成其数字的coin数量只需加1
                dp[i] = Math.min(dp[i], dp[i - coin] + 1); // 将数组中第i - coin数字进行+1和本身最小值的判断
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}

//dfs 12ms 98% 理想中的算法
 public class Solution {
   public int curMin=Integer.MAX_VALUE;
public int coinChange(int[] coins, int amount) {
    if(coins.length==0||amount==0)
      return 0;
     Arrays.sort(coins);
   DFS(coins,coins.length-1,amount,0);
   if(curMin==Integer.MAX_VALUE)
     return -1;
   return curMin;
}
public void DFS(int[]coins,int index,int amount,int count)
{
  if(index==-1)
    return ;
   int number=amount/coins[index]; // 记录可以由coin组成的最大值
   for(int i=number;i>=0;i--) // i = 0很重要
   {
       int remain=amount-coins[index]*i; // 只可能 remain >= 0
       int newcount=count+i; // coin组成的数量
       if(remain>0&&newcount<curMin)
         DFS(coins,index-1,remain,newcount);
        else if(newcount<curMin){ // remain = 0;
            curMin=newcount;
            break; // 原答案没有break个人认为添加break更好
        }
         
        else // remain>=0,newcount>curMin
          break;
   }
}
}
