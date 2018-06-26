/**
Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
*/

My code://TLE 一个个判断过去是不是素数，是就加1
class Solution {
    public int countPrimes(int n) {
        int count = 0;
        for(int i = 2; i < n; i++)
            if(isPrime(i))
                count++;
        return count;
    }
    private boolean isPrime(int num) {
        if(num == 2) return true;
        for(int i = 2; i <= (num / i); i++) {
            if(num % i == 0)
                return false;
        }
        return true;
    }
}
Discuss：//都是创建一个n的额外空间，用来标识 排除 为素数倍数的数
（1）//从2开始遍历素数，同时标识所有素数倍数的数，下次遍历遇到就跳过
public class Solution {
    public int countPrimes(int n) {
         boolean[] m = new boolean[n];
        int count = 0;
        for (int i=2; i<n; i++) {//改成for(int i = 3; i < n; i += 2)理论上性能更好一点
            if (m[i])//如果当前值是已经遍历过的数的倍数（即不是素数）
                continue;
             
            count++;
            for (int j=i; j<n; j=j+i)//把所有素数的倍数的数标记for (int j=i*i; j<n; j+=i)也会更好一些
                m[j] = true;
        }
         
        return count;
    }
}
（2）//论坛里最快的算法（不是很理解）
publib int countPrimes(int n) {
    if (n < 3) //小于3的都返回0
        return 0;
    boolean[] f = new boolean[n];//创建n空间的boolean数组
    int count = n / 2;//先把所有偶数包括1给忽略不计
    //从3开始+2+2的递增，越过偶数，i * i < n是因为所有大于i的所有合数在内循环里已经被遍历判断标记过了
    //100以内的数为例：(大于i的数要不是素数，要不就可以由小于i的数乘积组成)
    //3 6 9 12 15...99
    //5 10 15 20...95
    //7 14 21 28...98
    //9 18 27 36...99
    for (int i = 3; i * i < n; i += 2) {
        if (f[i])//如果是合数则跳过
            continue;
        //小于i * i的x * i数可以被前面遍历过的数给标记过，因为i本身就是奇数，所以需要j += 2 * i
        for (int j = i * i; j < n; j += 2 * i) {
            if (!f[j]) {
                --count;//遇到一个合数则减1
                f[j] = true;//标记合数
            }
        }
    }
    return count;
}
