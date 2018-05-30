/**
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
*/

My code://11ms 56%
//先进行一次过滤，再判断长度奇偶，最后取中心向外展开，判断是否为回文
class Solution {
    public boolean isPalindrome(String s) {
        List<Character> list=new ArrayList<>();
        for(int i=0;i<s.length();i++){//过滤
            char c=s.charAt(i);
            if(c>=65&&c<=90||c>=48&&c<=57)
                list.add(c);
            else if(c>=97&&c<=122)//小写变大写
                list.add((char)(c-32));
        }
        int size=list.size();
        int mid=size/2;//中心
        return size%2==0?isSymmetry(list,mid-1,mid):isSymmetry(list,mid-1,mid+1);
    }
    //判断是否为对称回文
    public boolean isSymmetry(List<Character> list,int pre,int beh){
        int size=list.size();
        while(beh<=size-1){
            if(list.get(pre--)!=list.get(beh++))
                return false;
        }
        return true;
    }
}

Discuss：
（1）//惊艳！
public class Solution {
    public boolean isPalindrome(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}
（2）//从头部和尾部向中心判断，遇到非数字字母符号则跳过
public class Solution {
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
        	return true;
        }
        int head = 0, tail = s.length() - 1;//设置头尾
        char cHead, cTail;
        while(head <= tail) {//等于号可以去除
        	cHead = s.charAt(head);
        	cTail = s.charAt(tail);
        	if (!Character.isLetterOrDigit(cHead)) {//如果头部不为规定字符
        		head++;
        	} else if(!Character.isLetterOrDigit(cTail)) {//如果尾部不为规定字符
        		tail--;
        	} else {//两边都为数字或者字母，取小写判断是否相等
        		if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
        			return false;//不相等返回false
        		}
        		head++;//相等向内靠拢
        		tail--;
        	}
        }
        
        return true;
    }
}
