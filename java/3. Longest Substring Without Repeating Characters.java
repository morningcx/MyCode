/**
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. 

Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

My code://115ms 11% 
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len=s.length();
        if(len==0||s==null)
            return 0;
        int max=Integer.MIN_VALUE;
        Set<Character> set=new HashSet<>();
        for(int i=0;i<len;i++){
            set.clear();
            for(int j=i;j<len;j++){
                char c=s.charAt(j);
                if(set.contains(c))
                    break;
                else{
                    set.add(c);
                    max=Math.max(max,set.size());
                }
            }
        }
        return max;
    }
}

Discuss:
（1）时间复杂度O(n+?)
public int lengthOfLongestSubstring(String s) {
    int i = 0, j = 0, max = 0;
    Set<Character> set = new HashSet<>();
    while (j < s.length()) {
        if (!set.contains(s.charAt(j))) {//如果set中不存在字符
            set.add(s.charAt(j++));
            max = Math.max(max, set.size());
        } else { //如果set存在字符，一直循环删除前列元素，直到set中不存在，重新开始添加
            set.remove(s.charAt(i++));
        }
    }
    return max;
}
（2）其中的map可以用int[128]来代替，直接a[s.charAt(i)]取值
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            //重复出现的字母取位置最大的（abcdedc）c重复的时候j的值仍然是d+1
            if (map.containsKey(s.charAt(i))){ //存在元素，则记录之前存在元素位置j的最大值，因为map没有删除元素，所以有可能
                j = Math.max(j,map.get(s.charAt(i))+1);//重复出现之前已经应该如（1）一样删除的元素，故取j的最大值
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);//max取当前i减去重复元素位置
        }
        return max;
    }
}
（3）问题本质是找到之前遍历的最后一个和当前字符重复的字符的位置
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 1;
        int start = 0;
        for (int i = 1; i < s.length(); i++) {
            for (int j = i - 1; j >= start; j--) { //从后往前遍历
                if (s.charAt(j) == s.charAt(i)) { //如果相等
                    start = j + 1; //重新计数的位置为j+1
                    break;
                }
            }
            max = Math.max(max, i - start + 1);//每次循环判断最大值
        }
        return max;
    }
}
