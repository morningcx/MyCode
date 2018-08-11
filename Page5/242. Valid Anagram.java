//My Code:4ms 59%
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;//先保证字符串长度一致
        int[] letters = new int[256];//不必要申请额外空间，直接char-'a'，既可获取26个空间数组的位置
        for (int i = 0; i < s.length(); ++i) {
            ++letters[s.charAt(i)];
            --letters[t.charAt(i)]; 
        }
        for (int i = 97; i < 123 ; ++i) {
            if (letters[i] != 0)
                return false;
        }
        return true;
    }
}

//Discuss:基本思路一样，这个方法也想过
public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;//先保证字符串长度一致
        int [] a = new int [26];
        for(Character c : s.toCharArray()) a[c - 'a']++;
        for(Character c : t.toCharArray()) {
            if(a[c -'a'] == 0) return false;//存在不一样的字符，或者相同字符数量不同，返回false
            a[c - 'a']--;
        }
        return true;
    }
}
