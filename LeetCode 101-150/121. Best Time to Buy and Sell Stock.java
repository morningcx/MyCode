/**
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/

My code://2ms 92% 
//搜索当前遍历到的元素的最小值和其后最大值的差
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit=0;
        if(prices.length==0) return maxProfit;
        int min=prices[0];
        for(int i=0;i<prices.length;i++){
            if(prices[i]<min)//更新最小值
                min=prices[i];
            if(prices[i]-min>maxProfit)//更新利润
                maxProfit=prices[i]-min;
        }
        return maxProfit;
    }
}

Discuss:
（1）//思路一样，在代码上的改进
public int maxProfit(int[] prices) {
    if(prices == null || prices.length < 2) return 0;      
    int maxProfit = 0, minPrice = prices[0];
    
    for(int i = 1; i < prices.length; i++) {
        if(prices[i] > prices[i - 1]) {//价格上涨，则计算最大利润
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);       
        } else {//价格下跌，最小值重新赋值
             minPrice = Math.min(minPrice, prices[i]);
        }
    }

    return maxProfit;
}

（2）//Kadane's Algorithm  不太好理解
    public int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            //前一个最小值小于现在缩小的值，则继续增加
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);//如果缩小量大于增长量，则置0，否则继续增加
            maxSoFar = Math.max(maxCur, maxSoFar);//求最大值
        }
        return maxSoFar;
    }
