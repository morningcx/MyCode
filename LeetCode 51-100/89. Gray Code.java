/**
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/

比如n=3：
0+1+11+10+110+111+101+100->[0,1,3,2,6,7,5,4]
My code://1ms 100% 先从低位开始分情况添加，多一位则反向遍历list，在其基础上添加高位
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result=new ArrayList<>();
        result.add(0);//先添加一个0
        if(n==0) return result;
        cal(result,n,1,1);//从1开始添加
        return result;
    }
    void cal(List<Integer> list,int n,int num,int pow){
        for(int i=list.size()-1;i>=0;i--)//反向遍历list，添加当前元素加上pow值
            list.add(list.get(i)+pow);
        if(n==num) return;
        cal(list,n,num+1,pow*2);
    }
}

Discuss:
（1）//不需要额外空间的递归
public List<Integer> grayCode(int n) {
    List<Integer> rs=new ArrayList<Integer>();
    rs.add(0);
    for(int i=0;i<n;i++){
        int size=rs.size();
        for(int k=size-1;k>=0;k--)
            rs.add(rs.get(k) | 1<<i);//1<<i即2^i，因为1<<i的最高位比rs.get(k)位数高，可以直接|得结果
    }
    return rs;
}
（2）//G(i) = i^ (i/2).
public List<Integer> grayCode(int n) {
    List<Integer> result = new LinkedList<>();
    for (int i = 0; i < 1<<n; i++) result.add(i ^ i>>1);
    return result;
}
