/**
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/

My code://6ms 48%
//用一个set记录出现过的数字，若存在数字说明存在循环
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while(!set.contains(n)) {//这里循环判断可以直接用set.add(n)，存在则添加返回false，跳出循环
            set.add(n);
            int sum = 0;
            while(n != 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;
            if (n == 1) return true;
        }
        return false;
    }
}

Discuss：
（1）//和判断单链表是否存在环一样，设一个slow，一个fast，若相等说明存在数字环
    int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }
    
    bool isHappy(int n) {
        int slow, fast;
        slow = fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
            if(fast == 1) return 1;//若fast先于slow到1，说明不存在环
        } while(slow != fast);//没有发生碰撞则继续
         return 0;
}
（2）//不是欢乐数的数字在循环中必定会出现4这个数
//好像没有标准的数学证明方法，维基百科上有提到一点
class Solution {
    public boolean isHappy(int n) {
        while (n != 1 && n != 4) {//循环中出现4则跳出循环
            int cur = n;
            n = 0;
            while (cur > 0) {
                int d = cur % 10;
                n += d*d;
                cur /= 10;
            }
        }

        return n == 1;
    }
}
（3）//任何数不停执行下去必将算到一个小于10的数
//从1-9中判断只有1和7是欢乐数
public boolean isHappy(int n) {
        if(n<=0) return false;
        while(true){
            int sum=0;
            while(n!=0){
              sum+=(n%10)*(n%10);
              n=n/10;
            }
            if(sum/10==0){
               if(sum==1||sum==7) return true;//如果循环过程中出现7则说明是欢乐数
               else return false;
            }
            n=sum;
        }
    }
