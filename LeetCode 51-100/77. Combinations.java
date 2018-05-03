/**
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

My code:// 3ms 100% 
//递归进行分解，分解成多个模块进行判断添加
//因为按顺序添加，限制循环添加次数
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result=new ArrayList<>();
        cal(result,new ArrayList<>(),1,n,k);
        return result;
    }
    void cal(List<List<Integer>> result,List<Integer> list,int start,int end,int num){
        if(num==0){ 
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i=start;i<end-num+2;i++){//循环end-start+1-num+1次，所以是从start到end-num+2，后续不必进行添加，因为后续元素添加不满
            list.add(i);
            cal(result,list,i+1,end,num-1);//分解
            list.remove(list.size()-1);
        }
    }
}
