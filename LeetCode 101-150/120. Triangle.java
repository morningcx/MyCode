/**
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

My code://dp solution,without use the extra space,but it's slow
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null||triangle.size()==0) return 0;
        for(int i=1;i<triangle.size();i++){
            List<Integer> pre=triangle.get(i-1);//前一行
            List<Integer> cur=triangle.get(i);//当前行
            for(int j=0;j<cur.size();j++){
                if(j>0&&j<cur.size()-1)//中间点
                    cur.set(j,cur.get(j)+Math.min(pre.get(j),pre.get(j-1)));//取上一行两个相邻值的最小值
                else if(j==0)//起点
                    cur.set(j,cur.get(j)+pre.get(0));
                else//末尾点
                    cur.set(j,cur.get(j)+pre.get(pre.size()-1));
            }
        }
        List<Integer> result=triangle.get(triangle.size()-1);
        int min=Integer.MAX_VALUE;
        for(int i=0;i<result.size();i++){//找最后一行中最小值
            int value=result.get(i);
            if(value<min)
                min=value;
        }
        return min;
    }
}

Discuss:
（1）//用到额外空间
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size() + 1];//size+1，为了防止给dp数组赋初值时最后一个数的Math.min(dp[j], dp[j + 1])越界
        for (int i = triangle.size() - 1; i >= 0; i--) {//倒序，这样结果就会归并到dp[0]
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                dp[j] = list.get(j) + Math.min(dp[j], dp[j + 1]);//list当前值加上前两个路径的最小值
            }
        }
        return dp[0];
    }
}
（2）//没有用到额外空间
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        for(int i=len-2; i>=0; i--){//从倒数第二行开始，这样就不用和（1）那样防止越界并且省去赋值
            for(int j=0; j<=i; j++){
                //将当前list值赋值为初始值+能到当前节点两条路径的最小值
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1)));
            }
        }
        return triangle.get(0).get(0);
    }
}
