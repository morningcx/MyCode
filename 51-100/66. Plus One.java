/**
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
*/
My code://1ms 68%
class Solution {
    public int[] plusOne(int[] digits) {
        int count=0;
        for(int i:digits)
            if(i==9)
                count++;
        if(count==digits.length){ //如果全为9，则新建数组返回
            int[] result=new int[digits.length+1];
            result[0]=1;
            return result;
        }
        count=digits.length-1;
        do{
            digits[count]=(digits[count]+1)%10;//加1操作
        }while(digits[count--]==0);//如果加法后为0，即原数为9
        return digits;
    }
}

Discuss://遍历一次
class Solution {
public int[] plusOne(int[] digits) { 
    int n = digits.length;
    for(int i=n-1; i>=0; i--) {
        if(digits[i] < 9) {//小于9则加1后直接返回
            digits[i]++;
            return digits;
        }
        digits[i] = 0;//为9则赋0
    }
    int[] newNumber = new int [n+1];//如果没有结束，说明全为9，新建并返回
    newNumber[0] = 1;
    return newNumber;
}
}
