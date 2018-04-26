/**
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
*/

My code://4ms 98%
class Solution {
    public int climbStairs(int n) {
        int way=1;
        int before=0;
        for(int i=0;i<n;i++){
            int mid=way;
            way+=before;
            before=mid;
        }
        return way;
    }
}

class Solution {
    public int climbStairs(int n) {
        int way=1;
        int before=0;
        while(n-->0)
            before=(way+=before)-before;
        return way;
    }
}

1,1->         1,2->1,2      1,3->2,3      2,5->3,5
Discuss://初始两值都为1，先相加求第三个数覆盖第二个数，然后第三个数-第一个数=源第二个数即为现第一个数
public int climbStairs(int n) {
    int a = 1, b = 1;
    while (n-- > 0)
        a = (b += a) - a;
    return a;
}
