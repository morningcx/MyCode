/**
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
*/

My code://14ms 67%
//利用栈，遇到数字入栈，遇到运算符出栈两次，判断运算符号计算后再次入栈
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        for(String word:tokens){
            char c=word.charAt(word.length()-1);
            if(c>='0'&&c<='9')
                stack.push(Integer.valueOf(word));
            else{
                int j=stack.pop();
                int i=stack.pop();
                stack.push(calculate(i,j,word));
            }
        }
        return stack.pop();
    }
    public Integer calculate(Integer i,Integer j,String operator){
        switch(operator){
            case "+":
                return i+j;
            case "-":
                return i-j;
            case "*":
                return i*j;
            case "/":
                return i/j;
        }
        return null;
    }
}

Discuss：
（1）//15ms 但是感觉代码清晰
public int evalRPN(String[] a) {
  Stack<Integer> stack = new Stack<Integer>();
  
  for (int i = 0; i < a.length; i++) {
    switch (a[i]) {
      case "+":
        stack.push(stack.pop() + stack.pop());
        break;
          
      case "-":
        stack.push(-stack.pop() + stack.pop());//因为先出栈的元素是减数，故"-"
        break;
          
      case "*":
        stack.push(stack.pop() * stack.pop());
        break;

      case "/":
        int n1 = stack.pop(), n2 = stack.pop();
        stack.push(n2 / n1);
        break;
          
      default:
        stack.push(Integer.parseInt(a[i]));
    }
  }
  
  return stack.pop();
}
 
（2）//6ms 99%
//所有方法思路基本一致，但是这个算法利用的数组而不是栈，速度将会比用栈快一倍
public int evalRPN(String[] tokens) {
 int[] ls = new int[tokens.length/2+1];
    int index = 0;
    for (String token : tokens) {
        switch (token) {
            case "+":
                ls[index - 2] = ls[index - 2] + ls[index - 1];
                index--;
                break;
            case "-":
                ls[index - 2] = ls[index - 2] - ls[index - 1];
                index--;
                break;
            case "*":
                ls[index - 2] = ls[index - 2] * ls[index - 1];
                index--;
                break;
            case "/":
                ls[index - 2] = ls[index - 2] / ls[index - 1];
                index--;//后退一格
                break;
            default:
                ls[index++] = Integer.parseInt(token);//计算完成遇到数字时会覆盖之前运算完成的字符
                break;
        }
    }
    return ls[0];
}
