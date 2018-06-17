/**
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
Example 1:

Input: 1
Output: "A"
Example 2:

Input: 28
Output: "AB"
Example 3:

Input: 701
Output: "ZY"
*/

My code://0ms 100%
//其实是很简单一道题，也就是26进制数转换
//但是因为进制数的开头是从0开始的，而这里A的起始数为1
class Solution {
    public String convertToTitle(int n) {
        StringBuilder result=new StringBuilder();
        while(n>26){
            if(n%26==0){
                result.append('Z');
                n=n/26-1;
            }else{
                result.append((char)(n%26+64));
                n/=26;
            }
        }
        result.append((char)(n+64));
        return result.reverse().toString();
    }
}

Discuss：
（1）
//每次添加位数时要减1
//因为26一般进制数转换从0,1,2,3,4...25 -> 10(26)
//所以一开始的数可以表示成00,01,02,03...25 -> 10(26)
//但是现在从A,B,C..Z之后的AA(26)开始，它的第二位是从0(A)开始的，而不是和正常进制数一样，进位以后要从1开始
//所以每次添加每一位的数的时候，需要-1操作，把原本除出来的结果-1，从而会从0开始判断相应字母
//然后原来的A(1)B(2)C(3)..Z(26)对应A(0)B(1)C(2)..Z(25)
/**
 Why --n instead of (n - 1)? 
 because even the current digit is 26, it should not have a carry 1 to the next digit. e.g. 52 -> AZ not BZ.

(n - 1) makes it 52/26 = 2 (represents 'B') -> BZ ->false
--n makes it 51/26 = 1 (represents 'A') -> AZ -> true
*/
public class Solution {
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            n--;//每次添加位数时要减1
            result.append((char)('A' + n % 26));
            n /= 26;
        }
        result.reverse();
        return result.toString();
    }
}
（2）//和我方法一样
class Solution {
    public String convertToTitle(int n) {
        StringBuilder res = new StringBuilder();
        while(n>0){
            res.append((n%26) != 0 ? (char)('A'- 1 + (n%26)) : (char)('Z'));
            n= (n-1)/26;//减1除26，能被整除的结果减1，不能被整除的结果不变
        }
        return res.reverse().toString();
    }
}
