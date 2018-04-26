/**
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
*/

My code:
（1）//第一次就用穷举法循环
class Solution {
    public int mySqrt(int x) {
        int mid=0;
        for(int i=0;i<=x;i++){
            if(i*i>x||i*i<mid)
                return i-1;
            mid=i*i;
        }
        return mid;
    }
}
（2）//第二次根据x的位数缩小范围
class Solution {
    public int mySqrt(int x) {
        int start=0;
        int end=9;
        int count=1;
        int n=x;
        while(n>=10){
            count++;
            n/=10;
        }
        for(int i=1;i<(count+1)/2;i++){
            start=end+1;
            end=10*start-1;
        }
        int mid=0;
        int i=0;
        for(i=start;i<=end;i++){
            if(i*i>x||i*i<mid)
                return i-1;
            mid=i*i;
        }
        return i-1;
    }
}
（3）//看了submission用二分法重写了一个（还试过利用（2）的缩小范围提交过，不过时间消耗比（3）长）
class Solution {
    public int mySqrt(int x) {
        int start=0;
        int end=x;
        while(start<end){
            int mid=start+(end-start)/2+1;//加1操作，记得前面的题学来的
            if(x/mid>mid)
                start=mid;
            else if(x/mid<mid)//mid*mid有可能越界，用除法判断完美
                end=mid-1;
            else
                return mid;
        }
        return start;
    }
}

Submission：//left和right都做加1操作，因为会判断mid+1
public int sqrt(int x) {
    if (x == 0)
        return 0;
    int left = 1;
    int right = Integer.MAX_VALUE; //right其实可以设置为x
    while (true) {
        int mid = left + (right - left)/2;
        if (mid > x/mid) {
            right = mid - 1;
        } else {
            if (mid + 1 > x/(mid + 1))
                return mid;
            left = mid + 1;
        }
    }
}
