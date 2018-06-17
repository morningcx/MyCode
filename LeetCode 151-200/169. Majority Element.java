/**
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
*/

My code://hashmap
class Solution {
    public int majorityElement(int[] nums) {
        int num=nums.length/2+1;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i:nums){
            Integer j=map.get(i);
            if(j==null)
                map.put(i,1);
            else{
                if(++j==num) return i;
                map.put(i,j);
            }
        }
        return nums[0];
    }
}

Discuss://时间O(n)空间O(1)
//遇到相同元素加1，遇到不同元素减1，当count为0，则换成当前元素
//因为多数元素数量一定比其他元素数量总和多，所以最后major一定是那个元素
public class Solution {
    public int majorityElement(int[] num) {

        int major=num[0], count = 1;
        for(int i=1; i<num.length;i++){
            if(count==0){
                count++;
                major=num[i];
            }else if(major==num[i]){
                count++;
            }else count--;
            
        }
        return major;
    }
}
