/**
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
*/

My code://17ms 93%
class Solution {
        int start=0;
        int end=0;
        int max=0;
    public String longestPalindrome(String s) {
        int len=s.length();
        if(len<2) return s;
        for(int i=1;i<len;i++){
            cal(s,i-1,i+1);//对称轴就是该元素
            if(s.charAt(i)==s.charAt(i-1))
                cal(s,i-1,i);//对称轴是两数中间
        }
        return s.substring(start,end+1);
    }
    void cal(String s,int pre,int hid){
        while(pre>=0&&hid<=s.length()-1&&s.charAt(pre)==s.charAt(hid)){
                pre--;
                hid++;
        }
        if(hid-pre-1>max){
            max=hid-pre-1;
            start=pre+1;
            end=hid-1;
        }
    }
}

Discuss:
(1)将对称轴视为一个元素或者多个元素组，下一次判断就可以越过视为对称轴的元素或者元素组
public class Solution {
    int len = 0, maxLength = 0, init = 0;
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        len = s.length();
        if (len <= 1) return s;
        for (int i = 0; i < len; i++) {
            i = manacher(chars, i);
        }
        return s.substring(init, init + maxLength);
    }
    public int manacher(char[] chars, int k) {
        int i = k - 1, j = k;
        while (j < len - 1 && chars[j] == chars[j + 1]) j++;//越过重复的元素
        int nextCenter = j++;//返回重复元素的后面一个
        while (i >= 0 && j < len && chars[i] == chars[j]) {
            i--;
            j++;
        }
        if (j - i - 1 > maxLength) {
            maxLength = j - i - 1;
            init = i + 1;
        }
        return nextCenter;
    }
}
(2)
public class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        int currLength = 0;
        for(int i=0;i<s.length();i++){
            if(isPalindrome(s,i-currLength-1,i)){
                res = s.substring(i-currLength-1,i+1);
                currLength = currLength+2;
            }
            else if(isPalindrome(s,i-currLength,i)){
                res = s.substring(i-currLength,i+1);
                currLength = currLength+1;
            }
        }
        return res;
    }
    
    public boolean isPalindrome(String s, int begin, int end){
        if(begin<0) return false;
        while(begin<end){
        	if(s.charAt(begin++)!=s.charAt(end--)) return false;
        }
        return true;
    }
}
