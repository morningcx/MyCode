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
（1）//将A-1，Z-26假设成A-0，Z-25
public class Solution {
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            n--;//每次除完要减1
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
