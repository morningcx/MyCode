/**
Given an integer, write a function to determine if it is a power of two.

Example 1:

Input: 1
Output: true 
Explanation: 20 = 1
Example 2:

Input: 16
Output: true
Explanation: 24 = 16
Example 3:

Input: 218
Output: false
*/

My code://1ms 100%
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n % 2 == 0 ? n == 0 ? false : isPowerOfTwo(n / 2) : n == 1;
    }
}

Discuss://n是2的倍数表明，数字位中只有一位为1
//n-1将唯一为1的那位后面的所有0改为1，相与则结果为0
//结果不为0说明其他位还存在1
 public boolean isPowerOfTwo(int n) {
    return ((n & (n-1))==0 && n>0);
}
