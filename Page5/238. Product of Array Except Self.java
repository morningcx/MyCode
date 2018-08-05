//不用除法，O(n)复杂度
//先求左边数值乘积，再求右边数值乘积
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; ++i) {
            result[i] = result[i - 1] * nums[i - 1]; 
        }
        for (int i = nums.length - 1,right = 1; i >= 0; --i) {
            result[i] *= right;
            right *= nums[i];
        }
        return result;
    }
}
