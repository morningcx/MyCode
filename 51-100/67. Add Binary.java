/**
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
*/

My code://3ms 99%
class Solution {
    public String addBinary(String a1, String b1) {
        if(a1.length()==0&&b1.length()==0) return "0";
        int len=Math.max(a1.length(),b1.length());
        char[] result=new char[len+1];
        char[] a=a1.toCharArray();
        char[] b=b1.toCharArray();
        int i=a1.length()-1;
        int j=b1.length()-1;
        int jin=0;
        while(i>=0&&j>=0){
            int mid=a[i--]+b[j--]+jin-96;
            result[len--]=(char)(mid%2+48);
            jin=mid/2;
        }
        while(j>=0){
            int mid=b[j--]+jin-48;
            result[len--]=(char)(mid%2+48);
            jin=mid/2;  
        }
        while(i>=0){
            int mid=a[i--]+jin-48;
            result[len--]=(char)(mid%2+48);
            jin=mid/2;  
        }
        if(jin==0)
            return String.valueOf(result).substring(1,result.length);
        result[0]=49;
        return String.valueOf(result);
    }
}

Submission://数组长度不必max+1，sum分情况只需一次循环，有进位首位只需1+result，没有则直接返回
class Solution {
    public String addBinary(String a, String b) {
        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();
        int ai = ac.length -1;
        int bi = bc.length -1;
        int max = Math.max(ai, bi);
        char[] result = new char[max+1];//char[]长度设置为其中的最大长度
        int sum = 0;
        while(ai>=0 || bi>=0) {
            if(ai>=0) { //进位sum分情况添加则只需进行一次循环
                sum += ac[ai--] - '0';
            }
            if(bi>=0) {
                sum +=bc[bi--] - '0';
            }
            result[max--] = (char)(sum%2 + 48);
            sum /= 2;
        }
        String r = new String(result);//char[]转换成字符串的另一种形式
        if(sum==1) {
            r = sum + r;//sum=1自动转换成"1"
        }
        return r;
    }
