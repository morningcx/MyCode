// my code 6ms 63%
// 将出现少于k次的字母作为分隔符，对分开的子字符串进行相同的判断操作
class Solution {
    public int longestSubstring(String s, int k) {
        if (s.length() < k) return 0;
        Set<Character> valid = new HashSet<>(); // 记录出现次数大于k次的几个数字
        Map<Character, Integer> map = new HashMap<>(); // 用int[]存储，后面循环判断其实更快，这里懒得再优化了
        for (char c : s.toCharArray()) {
            Integer count = map.get(c);
            if (count == null) count = 0;
            if (count + 1 >= k) valid.add(c);
            map.put(c, count + 1);
        }
        if (valid.size() == 0) return 0;
        if (valid.size() == map.size()) return s.length();
        int pre = -1, max = 0, i = 0;
        for (i = 0; i < s.length(); ++i) {
            if (!valid.contains(s.charAt(i))) {
                String sub = s.substring(pre + 1, i);
                max = Math.max(max, longestSubstring(sub, k));
                pre = i;
            }
        }
        String sub = s.substring(pre + 1, i);
        max = Math.max(max, longestSubstring(sub, k));
        return max;
    }
}

// discuss 1 
// 思路一致，但是是分成左右两块，左右两块进行判断，其实这时左右两块中仍存在已经判断过的元素
// 有机会将自己的算法重新改造一下
public int longestSubstring(String s, int k) {
    return helper(s.toCharArray(), 0, s.length(), k);
}
public int helper(char[] chs, int left, int right, int k) { // 利用left和right，不切分字符串提高效率
    if(right - left < k) return 0;
    int[] count = new int[26];
    for(int i = left; i < right; i++)
        count[chs[i]-'a']++;
    for(int i = left; i < right; i++) {
        if(count[chs[i]-'a'] < k) {
            int j = i + 1;
            while(j < right && count[chs[j]-'a'] < k) j++;
            return Math.max(helper(chs, left, i, k), helper(chs, j, right, k));
        }
    }
    return right - left;
}

// disucss 2 看不懂，先做出76题滑动窗口之后再回来理解吧
public class Solution {
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int[] counts = new int[26];
        int h, i, j, idx, max = 0, unique, noLessThanK;
        
        for (h = 1; h <= 26; h++) {
            Arrays.fill(counts, 0);
            i = 0; 
            j = 0;
            unique = 0;
            noLessThanK = 0;
            while (j < str.length) {
                if (unique <= h) {
                    idx = str[j] - 'a';
                    if (counts[idx] == 0)
                        unique++;
                    counts[idx]++;
                    if (counts[idx] == k)
                        noLessThanK++;
                    j++;
                }
                else {
                    idx = str[i] - 'a';
                    if (counts[idx] == k)
                        noLessThanK--;
                    counts[idx]--;
                    if (counts[idx] == 0)
                        unique--;
                    i++;
                }
                if (unique == h && unique == noLessThanK)
                    max = Math.max(j - i, max);
            }
        }
        
        return max;
    }
}
