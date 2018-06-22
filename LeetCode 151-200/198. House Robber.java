/**
You are a professional robber planning to rob houses along a street. Each house has a 

certain amount of money stashed, the only constraint stopping you from robbing each of them 

is that adjacent houses have security system connected and it will automatically contact 

the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 

determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
*/

My code://DP算法，当前节点所能偷取的最大值为之前第二个和之前第三个节点能偷取值的最大值
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        if(len == 1) return nums[0];
        if(len == 2) return Math.max(nums[len - 1], nums[len - 2]);//len<3时另做判断
        nums[2] += nums[0];//len>=3则第三个值确定
        for(int i = 3; i < len; i++) {
            nums[i] += Math.max(nums[i - 2], nums[i - 3]);//动态求最大值
        }
        return Math.max(nums[len - 1], nums[len - 2]);//最后两个值中取最大值
    }
}

Discuss://用当前节点是否偷取来求得最大值
public static int rob(int[] nums) 
	{
	    int ifRobbedPrevious = 0; 	// max monney can get if rob current house
	    int ifDidntRobPrevious = 0; // max money can get if not rob current house

	    for(int i=0; i < nums.length; i++) {
      
          //若当前节点偷取，则rob为前一节点没有偷取的最大值和当前值之和
	        int currRobbed = ifDidntRobPrevious + nums[i];
          
          //若当前节点不偷取，则notrob为前一节点偷取和不偷取两值的最大值(有可能前前节点值较大，此时前一节点不偷取比偷取值大)
	        int currNotRobbed = Math.max(ifDidntRobPrevious, ifRobbedPrevious); 
	        
	        ifDidntRobPrevious  = currNotRobbed;
	        ifRobbedPrevious = currRobbed;
	    }
	    
	    return Math.max(ifRobbedPrevious, ifDidntRobPrevious);
	}
