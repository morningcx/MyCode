/**
Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Example 1:

Input: [1,2,3,1]
Output: true
Example 2:

Input: [1,2,3,4]
Output: false
Example 3:

Input: [1,1,1,3,3,4,3,2,4,2]
Output: true
*/

My code:
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.add(i))
                return true;
        }
        return false;
    }
}

Submissions://不太规范的方法，取最大最小值，创建一个很大的boolean数组，将每个元素位置减去偏移量设置为true，下次再次遇到则return true
//下次万一最大数为MAX_VALUE，最小值MIN_VALUE，则会发生内存溢出
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length <= 1 ){
            return false;
        }

        //找出数组中的最大数和最小数
        int min = nums[0];
        int max = nums[0];
        for(int i = 1, size = nums.length; i < size; i++){
            if(nums[i] < min){
                min = nums[i];
            }else if(nums[i] > max){
                max = nums[i];
            }
        }

        //若最大最小数的区间小于数组长度，必定存在重复数据
        if((max - min + 1 ) < nums.length){
            return true;
        }
        boolean[] results = new boolean[max - min + 1];
        for(int i = 0,size = nums.length; i < size; i++){
            //减去最小数，保证偏移量为整数
            int index = nums[i] - min;

            //若boolean数组该位置被置为true，表示该数已经出现过
            if(results[index]){
                return true;
            }
            //将boolean该偏移位置设置为true
            results[index] = true;
        }
        return false;
    }
