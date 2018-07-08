/**
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
*/

My code://11ms 97%
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            Integer pos = map.get(nums[i]);
            if (pos != null && (i - pos) <= k) return true;
            map.put(nums[i], i);
        }
        return false;
    }
}

Discuss://滑动窗口
public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);//固定窗口大小为k，删除i-k-1的节点
            if(!set.add(nums[i])) return true;//固定窗口中存在重复数，则返回true
        }
        return false;
 }
