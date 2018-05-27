/**
Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 3
Output: [1,3,3,1]
Follow up:

Could you optimize your algorithm to use only O(k) extra space?
*/

My code://2ms 88% 
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<=rowIndex;i++){//从0行开始，0行就是第一行
            for(int j=result.size()-1;j>=1;j--){//倒序添加
                result.set(j,result.get(j)+result.get(j-1));
            }
            result.add(1);//最后加1
        }
        return result;
    }
}

Discuss：//数学方法
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result=new ArrayList<Integer>();
        result.add(1);
        for(int i=1;i<=rowIndex;i++)
        {
            result.add((int)((long) result.get(i-1)*(rowIndex - (i-1))/i  ));
        }
        return result;
    }
}
