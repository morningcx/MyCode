/**
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4
*/

My code:
class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int i:nums){
            if(set.contains(i))
                set.remove(i);
            else
                set.add(i);
        }
        for(int i:set)
            return i;
        return 0;
    }
}

Discuss://亦或运算，相等数值将会消除
class Solution {
    public int singleNumber(int[] nums) {
        int result=0;
        for(int i:nums)
            result^=i;
        return result;
    }
}
