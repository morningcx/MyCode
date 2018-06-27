/**
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.
*/

My code://15ms 67%
//基本和discuss使用hashmap的的方法差不多
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int len = s.length();
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        Map<Character,Integer> sMap = new HashMap<>();
        Map<Character,Integer> tMap = new HashMap<>();
        for (int i = 0; i < len; ++i) {
            Integer sNum = sMap.get(cs[i]);
            Integer tNum = tMap.get(ct[i]);
            if(sNum != tNum) return false;
            //讨论版里每次都会进行put操作，个人认为sNum不为null的时候不需要额外的put操作
            //就好比并查集里两个相同字符第一次出现的root节点的位置是一样的
            if(sNum == null) {
                sMap.put(cs[i], i);
                tMap.put(ct[i], i);
            }
        }
        return true;
    }
}

Discuss://用256大小的数组来存储映射位置
class Solution {
public:
    bool isIsomorphic(string s, string t) {
        int m1[256] = {0}, m2[256] = {0}, n = s.size();
        for (int i = 0; i < n; ++i) {
            if (m1[s[i]] != m2[t[i]]) return false;
            //i+1是为了防止当i=0时相应字符map标志为数组默认初始化值0
            //比如aa和ab不是同构字符，但是如果直接赋值 = i
            //ab第一个字符a所对应的值为0
            //ab第二个字符b所对应的值依然是初始化时的0
            //aa的两个字符所对应的值都是0
            //则结果为true，不符合要求，所以需要+1来区分初始值0
            m1[s[i]] = i + 1;
            m2[t[i]] = i + 1;
        }
        return true;
    }
};
