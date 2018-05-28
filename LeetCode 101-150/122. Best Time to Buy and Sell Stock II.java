/**
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/

My code://贪心法：遇到价格下跌之前就把股票卖完，遇到价格上涨之前就把股票买进来
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length==0) return 0;
        int sum=0;
        int min=prices[0];
        int max=prices[0];
        for(int i=0;i<prices.length;i++){
            if(prices[i]<max){//遇到价格下跌
                sum+=max-min;//卖掉
                min=prices[i];//重新赋值
                max=prices[i];
            }
            else{
                max=prices[i];//价格上涨就等待时机
                if(i==prices.length-1)//最后一天结束要卖掉
                    sum+=max-min;
            }
        }
        return sum;
    }
}

Discuss：
（1）//就算法而言非常巧妙，但是计算利润的思想是间隔性的加法运算
//比如：1,2,3 该算法profit=0+(2-1)+(3-2) 而不是profit=0+(3-1)
public class Solution {
public int maxProfit(int[] prices) {
    int total = 0;
    for (int i=0; i< prices.length-1; i++) {
        if (prices[i+1]>prices[i]) 
            total += prices[i+1]-prices[i];
    }
    return total;
}
（2）//思想和我一样，但是判断条件有点多，感觉不如自己的代码清晰
public int maxProfit(int[] prices) {
    int profit = 0, i = 0;
    while (i < prices.length) {
        // find next local minimum
        while (i < prices.length-1 && prices[i+1] <= prices[i]) i++;
        int min = prices[i++]; // need increment to avoid infinite loop for "[1]"
        // find next local maximum
        while (i < prices.length-1 && prices[i+1] >= prices[i]) i++;
        profit += i < prices.length ? prices[i++] - min : 0;
    }
    return profit;
}
