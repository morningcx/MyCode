//My Code:3ms 78%
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        //可以新建一个map用来存储可能重复的表达式，可以减少不必要的表达式判断，提高速度
        List<Integer> result = new ArrayList<>();
        boolean hasOpe = false;
        for (int i = 0; i < input.length(); ++i) {
            char ope = input.charAt(i);
            if (ope < '0' || ope > '9') {
                hasOpe = true;
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1, input.length()));
                for (int l : left) {
                    for (int r : right) {
                        result.add(operation(l, r, ope));
                    }
                }
            }
        }
        if (!hasOpe) result.add(Integer.valueOf(input));//还可以判断result是否为空，为空则说明input字符为数字
        return result;
    }
    
    private int operation(int num1, int num2, char ope) {
        int result = 0;
        switch(ope) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
        }
        return result;
    }
}
