/**
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

My code://time limit exceeded 根据II的解法输出超时
class Solution {
    public int numTrees(int n) {
        return sum(1,n);
    }
    public int sum(int start,int end){
        if(start>=end)//如果是边界或者前后的第二个数
            return 1;//返回1，不必进行下一次的判断
        int sum=0;
        for(int i=start;i<=end;i++){//从start到end循环
            //如果是第一个或者是最后一个数，说明左边或者右边节点为null
            //如果是第二个或者是倒数第二个数，说明左边或者右边节点情况为1
            //出现上述情况都要返回1，因为是树的一种情况。
            int left=sum(start,i-1);//对小于i的情况计数
            int right=sum(i+1,end);//对大于i的情况计数
            sum+=left*right;
        }
        return sum;
    }
}

Discuss：//思想是对2,3,4,5的情况数量等于对1,2,3,4的情况的数量
//即现在左边存在3个数，右边存在4个数，都需要判断存在情况，只需对1,2,3情况的数量乘以1,2,3,4情况的数量就能得到结果
class Solution {
    public int numTrees(int n) {
        if(n==0) return 1;
        int[] nums=new int[n+1];
        nums[0]=1;//初始化0
        nums[1]=1;//需要对前两个进行初始化
        for(int i=2;i<=n;i++){
            for(int j=1;j<=i;j++){
                nums[i]+=nums[j-1]*nums[i-j];//左边数量乘以右边数量
            }
        }
        return nums[n];
    }
}
