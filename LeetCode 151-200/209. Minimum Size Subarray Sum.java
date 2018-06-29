/**
Given an array of n positive integers and a positive integer s, find the minimal length of 

a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time 

complexity is O(n log n). 
*/

My code://双指针滑动窗口
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int min = len + 1;
        int left = 0;
        int right = 0;
        int sum = nums[0];//先给sum赋值为第一个值
        while(left <= right){
            if(sum >= s) { //当sum大于等于s时
                min = Math.min(min, right - left + 1);//记录窗口大小
                sum -= nums[left++];//左指针右移 
            }else{
                if(++right == len) break;//右指针右移并判断是否越界
                sum += nums[right];//加上右指针的值
            }
        }
        return min == len + 1 ? 0 : min;
    }
}

Discuss：//因为题目给定输入数值都为正数，所以此算法正确
//如果输入s为负数需要另外加上一个left<=right的附加条件
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0, from = 0, win = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) { //给定范围，右指针就不会越界了
            sum += nums[i];
            while (sum >= s) { //当sum大于s时循环右移左指针
                win = Math.min(win, i - from + 1);//记录窗口大小
                sum -= nums[from++];
            }
        }
        return (win == Integer.MAX_VALUE) ? 0 : win;
    }
