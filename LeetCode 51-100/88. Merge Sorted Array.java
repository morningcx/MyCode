/**
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
*/

My code://从后往前插入数据
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pos=m+n-1;
        for(int i=pos;i>=0;i--){
            if(m-1>=0&&n-1>=0){//都没还遍历玩
                if(nums1[m-1]>nums2[n-1])
                    nums1[i]=nums1[m---1];
                else
                    nums1[i]=nums2[n---1];
            }
            else if(m-1<0)//nums1遍历完就插入num2
                nums1[i]=nums2[n---1];
            else if(n-1<0)
                nums1[i]=nums1[m---1];
        }
    }
}

Discuss：//不必考虑是否遍历完全
class Solution {
public:
    void merge(int A[], int m, int B[], int n) {
    int i=m-1;
		int j=n-1;
		int k = m+n-1;
		while(i >=0 && j>=0)//都未遍历完
		{
			if(A[i] > B[j])
				A[k--] = A[i--];
			else
				A[k--] = B[j--];
		}
		while(j>=0)//只要判断num2是否剩余既可，因为num1已经在数组内了
			A[k--] = B[j--];
    }
};
