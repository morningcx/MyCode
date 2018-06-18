/**
Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Note: Your solution should be in logarithmic time complexity.
*/

My code://sum+=(n/=5)这步操作可以合并在一起
//比如求100！的运行结果，目标是求出1-100中所有数可以拆分出5的数量的总和
//先求100里能被5整除的数，共有20个，分别是5、10、15...95、100，加入总和
//现在其实还需要确定这20个数中是否还存在能由5*(5*n)所组成的数，比如25=5*5,50=5*5*2，这时这个数还能拆分出5
//现在因为第一次是否能被5整除的判断已经结束，所以可以把所有数除以5，假设成1、2、3...19、20 -> (m)
//这些数组表示，所筛选出来的数由m*5组成
//现在就是去第二次判断由m所组成的数组中仍然有多少个数可以被5整除，加入总和
//重复这几步，直到拆分不出5，即n/5为0，结束
class Solution {
    public int trailingZeroes(int n) {
        int sum=0;
        while(n!=0){
            sum+=(n/=5);
        }
        return sum;
    }
}

Discuss：
class Solution {
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
