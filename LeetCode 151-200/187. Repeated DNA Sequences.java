/**
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]

*/

My code://hashmap存储元素，记录元素个数，遍历map取出计数个数大于1的添加到list
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int len = s.length();
        for(int i = 0; i < len - 9; i++){
            String sub = s.substring(i, i + 10);
            Integer temp = map.get(sub);
            if(temp == null) {
                map.put(sub, 1);
            }else{
                map.put(sub, temp + 1);
            }
        }
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() > 1){
                result.add(entry.getKey());
            }
        }
        return result;
    }
}

Discuss：
（1）//两个set，第一个set用于判断是否出现过，第二个set收集第二次或以上出现过的元素
public List<String> findRepeatedDnaSequences(String s) {
    Set seen = new HashSet(), repeated = new HashSet();
    for (int i = 0; i + 9 < s.length(); i++) {
        String ten = s.substring(i, i + 10);
        if (!seen.add(ten))
            repeated.add(ten);
    }
    return new ArrayList(repeated);
}
（2）//用比特代表字母00-A，01-C，10-G，11-T，因题目说以10个字母为标准，则总共需要2*10个bit作为字符串的hash值来存储数据
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Set<Integer> word = new HashSet<>();//用integer，4字节，32bit用作存储hash值
        Set<Integer> secondWord = new HashSet<>();
        int[] map = new int[26];
        //'A' 默认 00
        map['C' - 'A'] = 1;//01-C
        map['G' - 'A'] = 2;//10-G
        map['T' - 'A'] = 3;//11-T
        int value = 0;
        for (int i = 0; i < s.length(); i++) {
            value <<= 2;//左移两位
            value |= map[s.charAt(i) - 'A'];//相或
            value &= 0xfffff;//用于清除超过20个bit的高位
            if (i < 9) {//首先要存储第一个10个字母，所以前10个字母都先
                continue;
            }
            //第一个word用于判断是否存在，若不存在则添加完成返回true，但是!word.add是false，所以结束判断
            //若存在则判断第二个word,若不存在则说明是第二次出现，返回添加成功，并且同时添加至result
            //若已经存在说明元素是第三次或以上出现，已经被添加至result，返回false退出判断(避免重复添加)
            if (!word.add(value) && secondWord.add(value)) {
                result.add(s.substring(i - 9, i + 1));
            }
        }
        return result;
    }
}
