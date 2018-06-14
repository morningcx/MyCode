/**
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
Your solution should be in logarithmic complexity.
*/

My code://i can't come up with the logarithmic complexity algorithmis
//just accpet with the sequential search,also it is not the best sequential search
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length<=1) return 0;
        if(nums[0]>nums[1]) return 0;
        for(int i=1;i<nums.length-1;i++){
            if(nums[i]>nums[i-1]&&nums[i]>nums[i+1])
                return i;
        }
        return nums.length-1;
    }
}

Discuss://二分法
class Solution {
public:
    int findPeakElement(const vector<int> &num) 
    {
        int low = 0;
        int high = num.size()-1;
        
        while(low < high)
        {
            int mid1 = (low+high)/2;//中位数
            int mid2 = mid1+1;//中位数加1
            //判断哪边是递增的边
            if(num[mid1] < num[mid2])//右边大
                low = mid2;
            else
                high = mid1;//左边大
        }
        return low;
    }
};
