/**
Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:

Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
Example 2:

Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
*/

My code://循环进行倒数第二个，这样可以少一个判断，但是时间好像没有减少多少
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) return result;
        int len = nums.length;
        int preNum = nums[0];
        for (int i = 0; i < len - 1; ++i) {
            if (nums[i] != (nums[i + 1] - 1)) {
                result.add(preNum == nums[i] ? "" + preNum : preNum + "->" + nums[i]);
                preNum = nums[i + 1];
            }
        }
        result.add(preNum == nums[len - 1] ? "" + preNum : preNum + "->" + nums[len - 1]);
        return result;
    }
}

Discuss://代码比较清晰简单
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];//先记录第一个数值
            while ( i < nums.length - 1 && nums[i] == nums[i + 1] - 1) {
                i++;//一直找到不连续的那个数
            } 
            if (num != nums[i]) { //不相等则需要增加箭头
                res.add(num + "->" + nums[i]);
            } else { //相等则转换为字符直接添加
                res.add(num + "");
            }
        }
        return res;
        
    }
}
