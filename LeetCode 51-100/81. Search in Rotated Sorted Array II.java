/**
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?
*/

My code://先找出分界点，然后对target进行判断，选择start和end进行二分查找
//其实没有理解，但是accept了
class Solution {
    public boolean search(int[] nums, int target) {
        if(nums.length==0) return false;
        int start=0;
        int end=nums.length-1;
        while(start<end){
            while(nums[start]==nums[end]&&start<end) start++;
            int mid=start+(end-start)/2;
            if(nums[mid]<=nums[end])
                end=mid;
            else
                start=mid+1;
        }
        if(target>nums[nums.length-1]){
            end=start-1;
            start=0;
            
        }
        else
            end=nums.length-1;
            
        return binary(nums,start,end,target);
    }
    boolean binary(int[] nums,int start,int end,int target){
         while(start<=end){
            int mid=(start+end)/2;
            if(nums[mid]==target) 
                return true;
            if(nums[mid]>target) 
                end=mid-1;
            else  
                start=mid+1;
        }
         return false;
     }
}

Discuss：//对左右两侧进行判断哪边有序，然后判断target是否在有序数组中，在则二分查找，不在则取另一边，返回第一步
bool search(int A[], int n, int key) {
    int l = 0, r = n - 1;
    while (l <= r) {
        int m = l + (r - l)/2;
        if (A[m] == key) return true; //return m in Search in Rotated Array I
        if (A[l] < A[m]) { //left half is sorted 说明左边有序
            if (A[l] <= key && key < A[m])//在有序数组中
                r = m - 1;
            else//不在有序数组中则转移到另一侧，再次进行循环判断
                l = m + 1;
        } else if (A[l] > A[m]) { //right half is sorted 说明右边有序
            if (A[m] < key && key <= A[r])
                l = m + 1;
            else
                r = m - 1;
        } else l++;//如果判断不了哪边有序，则left++，应该也可以right--
    }
    return false;
}
