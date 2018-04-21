Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

My code:
class Solution {
    public int maxSubArray(int[] nums) {
        int max=Integer.MIN_VALUE;
        int n=0;
        for(int i=0;i<nums.length;i++){
            n+=nums[i];
            if(n<nums[i])//前面总和小于当前值
                n=nums[i];
            if(n>max)//大于最大值
                max=n;
        }
        return max;
    }
}

Discuss:
（1）
public static int maxSubArray(int[] A) {
    int maxSoFar=A[0], maxEndingHere=A[0];
    for (int i=1;i<A.length;++i){
    	maxEndingHere= Math.max(maxEndingHere+A[i],A[i]);
    	maxSoFar=Math.max(maxSoFar, maxEndingHere);	
    }
    return maxSoFar;
}
（2）//分治法
//以中间元素将数组分成左右两块，所以和最大的子数组存在3种情况
//1.在左边
//2.在右边
//3.又包含左边，又包含右边，即包含中间元素（递归要做的事）
//所以对左右两边进行相同的递归
class Solution {
public:
    int maxSubArray(int A[], int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(n==0) return 0;
        return maxSubArrayHelperFunction(A,0,n-1);
    }
    //这个递归是默认求第三种情况
    int maxSubArrayHelperFunction(int A[], int left, int right) {
        if(right == left) return A[left];
        int middle = (left+right)/2;
        int leftans = maxSubArrayHelperFunction(A, left, middle);//对左边递归返回的最大值
        int rightans = maxSubArrayHelperFunction(A, middle+1, right);//对右边递归返回的最大值
        int leftmax = A[middle];//用来记录当前部分左边最大值
        int rightmax = A[middle+1];//右边最大值
        int temp = 0;
        for(int i=middle;i>=left;i--) {
            temp += A[i];
            if(temp > leftmax) leftmax = temp;
        }
        temp = 0;
        for(int i=middle+1;i<=right;i++) {
            temp += A[i];
            if(temp > rightmax) rightmax = temp;
        }
        return max(max(leftans, rightans),leftmax+rightmax);//比较左右两边最大值和这部分中间最大值
    }
};
