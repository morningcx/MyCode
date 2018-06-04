/**
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
*/

My code:
（1）//10ms 52%
//一开始只想着做出来，没有代码上的一些优化，但是思路还是正确的dfs
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result=new ArrayList<>();
        create(result,new ArrayList<>(),s);
        return result;
    }
    
    public void create(List<List<String>> result,List<String> list,String s){
        if(s.length()==0){
            result.add(new ArrayList<>(list));
            return ;
        }
        for(int i=0;i<s.length();i++){
            String sub=s.substring(0,i+1);
            if(isPalindrome(sub)){
                list.add(sub);
                create(result,list,s.substring(i+1,s.length()));
                list.remove(list.size()-1);
            }
        }
    }
    
    public boolean isPalindrome(String s){
        int len=s.length();
        if(len==1) return true;
        int pre=0;
        int hid=0;
        if(len%2!=0){
            pre=len/2-1;
            hid=pre+2;
        }
        else{
            hid=len/2;
            pre=hid-1;
        }
        while(hid<len){
            if(s.charAt(pre--)!=s.charAt(hid++))
                return false;
        }
        return true;
    }
}

（2）//7ms 97% 
//第一次做出来以后也知道有些地方不需要额外的操作，优化了一下，提升了一点点排名，但还不是理想的代码（希望尽量少用substring）
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result=new ArrayList<>();
        create(result,new ArrayList<>(),s);
        return result;
    }
    
    public void create(List<List<String>> result,List<String> list,String s){
        if(s.length()==0){
            result.add(new ArrayList<>(list));
            return ;
        }
        //判断字符串从0到len的字符哪些是回文，然后再传递不是回文的那些字符串进行下一次递归
        for(int i=0;i<s.length();i++){
            if(isPalindrome(s,0,i)){
                list.add(s.substring(0,i+1));
                create(result,list,s.substring(i+1,s.length()));
                list.remove(list.size()-1);
            }
        }
    }
    //回文从外往内判断会更加简单清晰
    public boolean isPalindrome(String s,int start,int end){
        while(start<end){
            if(s.charAt(start++) != s.charAt(end--)){
                return false;
            }
        }
        return true;
    }
}

Discuss：//算法一致，理想中的代码
public class Solution {
    public List<List<String>> partition(String s) {
       List<List<String>> res = new ArrayList<List<String>>();
       List<String> list = new ArrayList<String>();
       dfs(s,0,list,res);
       return res;
    }
    
    public void dfs(String s, int pos, List<String> list, List<List<String>> res){
        if(pos==s.length()) res.add(new ArrayList<String>(list));
        else{
            for(int i=pos;i<s.length();i++){
                if(isPal(s,pos,i)){
                    list.add(s.substring(pos,i+1));//等待真正添加的时候用substring
                    dfs(s,i+1,list,res);//也就这里不一样，用pos代替子串
                    list.remove(list.size()-1);
                }
            }
        }
    }
    
    public boolean isPal(String s, int low, int high){
        while(low<high) if(s.charAt(low++)!=s.charAt(high--)) return false;
        return true;
    }
    
}
