/**
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/

My code://先对其中两个数进行排列组合返回结果，然后用结果和后续的数排列，以此递归
class Solution {
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0) return new ArrayList<>();
        String[] letters={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        return cal(letters,digits,0);
    }
    List<String> cal(String[] letters,String digits,int n){
        List<String> result=new ArrayList<>();
        if(n==digits.length()){//超出边界要让length为1，以便上次递归内循环进行一次
            result.add("");
            return result;
        }
        String s1=letters[digits.charAt(n)-48];
        List<String> list=cal(letters,digits,n+1);
        for(int i=0;i<s1.length();i++){
            String s=s1.substring(i,i+1);
            for(int j=0;j<list.size();j++){
                result.add(s+list.get(j));
            }
        }
        return result;
    }
}

Discuss:
(1)递归为了确认循环次数，如3x3x3，每次递归传递组成的字符串
public class Solution {
    	private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    
    	public List<String> letterCombinations(String digits) {
    		List<String> ret = new LinkedList<String>();
    		combination("", digits, 0, ret);
    		return ret;
    	}
    
    	private void combination(String prefix, String digits, int offset, List<String> ret) {
    		if (offset >= digits.length()) {
    			ret.add(prefix);//当循环次数到，则添加
    			return;
    		}
    		String letters = KEYS[(digits.charAt(offset) - '0')];
    		for (int i = 0; i < letters.length(); i++) {
    			combination(prefix + letters.charAt(i), digits, offset + 1, ret);//向下传递字符串组合
    		}
    	}
    }
    
(2)每次循环对list进行remove的元素进行记录，然后添加字符串循环重新添加
public List<String> letterCombinations(String digits) {
		LinkedList<String> ans = new LinkedList<String>();
		if(digits.isEmpty()) return ans;
		String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		ans.add("");
		while(ans.peek().length()!=digits.length()){//读取第一个元素，如果长度未达到digits的长度，即未添加完毕
			String remove = ans.remove();//记录删除第元素，remove为删除list里的第一个元素
			String map = mapping[digits.charAt(remove.length())-'0'];
			for(char c: map.toCharArray()){
				ans.addLast(remove+c);//在尾部添加增加了字符串的删除元素
			}
		}
		return ans;
	}
