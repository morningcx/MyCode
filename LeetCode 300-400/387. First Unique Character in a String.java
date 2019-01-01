// my code 
class Solution {
    public int firstUniqChar(String s) {
        int[] count = new int[256];
        char[] array = s.toCharArray();
        for (char c : array) {
            ++count[c];
        }
        int i = 0;
        for (char c : array) {
            if (count[c] == 1) return i;
            i++;
        }
        return -1;
    }
}

// discuss 1 linkedhashmap
public int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                if (map.get(s.charAt(i)) != null) {
                    map.remove(s.charAt(i)); // 出现过两次的则进行删除
                }
            } else {
                map.put(s.charAt(i), i);
                set.add(s.charAt(i));
            }
        }
        return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue(); // linkedhashmap保留插入顺序，返回第一个值既可

// discuss 2 遍历过程其实比所有的答案多，但是速度是最快的
class Solution {
    public int firstUniqChar(String s) {
        if(s==null||s.length()==0) return -1;
        
        int result = s.length();

        for(char c ='a'; c <= 'z' ; c++ ) { // 每一个小写字母进行遍历
        	int index = s.indexOf(c); // 定位
        	if(index !=-1 && index == s.lastIndexOf(c)) // 定位的位置等于最后一个出现的位置，则说明是出现一次的字母
                result = Math.min(result, index);
        }
        
        return result==s.length()?-1:result;
    }
}
