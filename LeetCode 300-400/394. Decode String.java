// my code 1ms 100%
class Solution {
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') { // 如果是数字
                int left = 0;
                int count = c - '0';
                while (left == 0) { // 因为数字不止一位，所以要记录数字长度
                    char in = s.charAt(++i);
                    if (in == '[') {
                        ++left;
                    } else {
                        count = count * 10 + in - '0';
                    }
                }
                int begin = ++i;
                while (left != 0) { // 记录第一个出现左括号[]中的str
                    char in = s.charAt(i);
                    if (in == ']') {
                        --left;
                    } 
                    if (in == '[') {
                        ++left;
                    }
                    ++i;
                }
                String sub = s.substring(begin, i - 1);
                String decodeSub = decodeString(sub); // dfs
                for (int j = 0; j < count; ++j) {
                    sb.append(decodeSub); // 添加
                }
                
            } else {
                sb.append(c);
                ++i;
            }
        }
        return sb.toString();
    }
}

// discuss c++ 时间复杂度比mycode较优
class Solution {
public:
    string decodeString(const string& s, int& i) {
        string res;
        // 如果这里所传入的值为左括号'['之后的str，所以需要用s[i] != ']'来阻断循环，返回res后再执行上一个栈函数，判断并执行']'之后的元素
        while (i < s.length() && s[i] != ']') {
            if (!isdigit(s[i])) // 是字母则添加res
                res += s[i++];
            else {
                int n = 0;
                while (i < s.length() && isdigit(s[i])) // 是数字则需要记录左括号[之前的数值
                    n = n * 10 + s[i++] - '0';
                // 循环结束的时候i的指向的元素为'['
                i++; // 所以需要i++跳过左括号'[' 
                string t = decodeString(s, i); // 这里i一定要是引用，才能使dfs变更的i所保留
                i++; // 执行完成[]中的值以后，跳过右括号']'，添加n * t，并继续判断']'之后的元素 
                
                while (n-- > 0)
                    res += t; // 循环记录
            }
        }
        
        return res;
    }

    string decodeString(string s) {
        int i = 0;
        return decodeString(s, i);
    }
};
