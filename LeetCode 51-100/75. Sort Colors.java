/**
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
*/

My Code://没想出O(n)的方法
（1）//O(2n)
class Solution {
    public void sortColors(int[] nums) {
        int[] colors=new int[3];
        for(int i:nums){
            colors[i]++;
        }
        int j=0;
        for(int i=0;i<nums.length;i++){
            while(colors[j]==0)
                j++;
            nums[i]=j;
            colors[j]--;
        }
    }
}
（2）//参照Discuss之后做的，标记首部和尾部，遇到0或者2进行交换操作，1不用管
class Solution {
    public void sortColors(int[] nums) {
        if(nums==null || nums.length<2) return;
        int start=0;//标记首部
        int end=nums.length-1;//标记尾部
        int move=0;
        while(move<=end){
            if(nums[move]==0)//遇到0则与首部进行交换，并且move加1(因为交换的不论是012，后续还会进行判断)
                swap(nums,move++,start++);
            else if(nums[move]==2)//遇到2则与尾部交换，这里move不能加1（因为交换过后的move可能是0，还需要一次对首部的交换）
                swap(nums,move,end--);
            else//遇到1则直接越过，因为不需要进行主动交换操作，但可能会被动交换
                move++;
        }
    }
    void swap(int[] nums,int i,int j){
        if(i==j) return;//不用额外标识的两个坏处：1、容易溢出 2、i==j时要另做判断
        nums[i]=nums[i]+nums[j];
        nums[j]=nums[i]-nums[j];
        nums[i]=nums[i]-nums[j];
    }
}

Discuss：
   public void sortColors(int[] A) {
       if(A==null || A.length<2) return;
       int low = 0; 
       int high = A.length-1;
       for(int i = low; i<=high;) {
           if(A[i]==0) {
              // swap A[i] and A[low] and i,low both ++
              int temp = A[i];
              A[i] = A[low];
              A[low]=temp;
              i++;low++;
           }else if(A[i]==2) {
               //swap A[i] and A[high] and high--;
              int temp = A[i];
              A[i] = A[high];
              A[high]=temp;
              high--;
           }else {
               i++;
           }
       }
   }
