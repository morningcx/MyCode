/**
Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Example 1:

Input: version1 = "0.1", version2 = "1.1"
Output: -1
Example 2:

Input: version1 = "1.0.1", version2 = "1"
Output: 1
Example 3:

Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1
*/

My code://3ms 66%
//split分裂后逐一判断
class Solution {
    public int compareVersion(String version1, String version2) {
        int pos=0;
        String[] s1=version1.split("\\.");//这里正则表达式如果用[.]要慢4ms
        String[] s2=version2.split("\\.");
        int len1=s1.length;
        int len2=s2.length;
        while(pos<len1||pos<len2){
            int digit1=pos<len1?Integer.valueOf(s1[pos]):0;
            int digit2=pos<len2?Integer.valueOf(s2[pos]):0;
            if(digit1>digit2)
                return 1;
            else if(digit1<digit2)
                return -1;
            else
                pos++;
        }
        return 0;
    }
}

Discuss：//不用分裂，遍历一次，把每个.之间元素转化为整形，判断大小
public class Solution {
public int compareVersion(String version1, String version2) {
    int temp1 = 0,temp2 = 0;
    int len1 = version1.length(),len2 = version2.length();
    int i = 0,j = 0;
    while(i<len1 || j<len2) {
        temp1 = 0;
        temp2 = 0;
        while(i<len1 && version1.charAt(i) != '.') {
            temp1 = temp1*10 + version1.charAt(i++)-'0';
            
        }
        while(j<len2 && version2.charAt(j) != '.') {
            temp2 = temp2*10 + version2.charAt(j++)-'0';
            
        }
        if(temp1>temp2) return 1;
        else if(temp1<temp2) return -1;
        else {
            i++;
            j++;
        }
    }
    return 0;
}
}
