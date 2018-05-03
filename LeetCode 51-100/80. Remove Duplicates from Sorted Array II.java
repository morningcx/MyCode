/**
Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.
*/

My Code://2ms 65% 
//用move来标记为填充的位置，当元素重复出现的次数小于2，则添加，大于2则越过，改变则添加重新计数
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length==0) return 0;
        int move=0;//移动填充的位置
        int pre=nums[0];//先前出现的元素
        int count=0;//元素出现的次数
        for(int i=0;i<nums.length;i++){
            if(nums[i]==pre){
                count++;
                if(count<=2){//出现的次数小于等于2，则添加并往后移位
                    nums[move]=pre;
                    move++;
                }
            }
            else{
                pre=nums[i];//记录为下一个元素的先前元素
                nums[move++]=nums[i];//添加后移
                count=1;//元素出现次数重新置1
            }               
        }
        return move;
    }
}
Discuss:
public int removeDuplicates(int[] nums) {
    int i = 0;
    for (int n : nums)
        if (i < 2 || n > nums[i-2])//当前元素和标记前两个元素进行比较，因为已经sort操作，大于则表示元素改变
            nums[i++] = n;
    return i;
}
