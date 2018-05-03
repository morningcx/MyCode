/**
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

MyCode://3ms 97% 
//受上一题的影响从0开始添加到nums.length个字符在nums数组中的组合，不过效果还不错
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        for(int i=0;i<=nums.length;i++)
            cal(result,new ArrayList<>(),0,nums.length-1,i,nums);
        return result;
    }
    void cal(List<List<Integer>> result,List<Integer> list,int start,int end,int num,int[] nums){
        if(num==0){
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i=start;i<end-num+2;i++){
            list.add(nums[i]);
            cal(result,list,i+1,end,num-1,nums);
            list.remove(list.size()-1);
        }
    }
}

Discuss：
（1）//从第一个数字的所有组合开始添加，直到最后一个数字
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        cal(result,new ArrayList<>(),0,nums);
        return result;
    }
    void cal(List<List<Integer>> result,List<Integer> list,int start,int[] nums){
        result.add(new ArrayList<>(list));//首个步骤就是添加拷贝list，后面都是基于list上进行的添加操作
        for(int i=start;i<nums.length;i++){
            list.add(nums[i]);
            cal(result,list,i+1,nums);
            list.remove(list.size()-1);
        }
    }
}
（2）
/**The idea is:
起始subset集为：[]
添加S0后为：[], [S0]
添加S1后为：[], [S0], [S1], [S0, S1]
添加S2后为：[], [S0], [S1], [S0, S1], [S2], [S0, S2], [S1, S2], [S0, S1, S2]
红色subset为每次新增的。显然规律为添加Si后，新增的subset为克隆现有的所有subset，并在它们后面都加上Si。
*/
 public List<List<Integer>> subsets_arraylist_clone(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    if(nums == null || nums.length ==0)
        return ret;
    Arrays.sort(nums);
    ret.add(new ArrayList<>());//添加空集合
    for(int n: nums){
        int tmpsize = ret.size();
        for(int i = 0; i < tmpsize; i ++){
            List<Integer> clone = new ArrayList<>(ret.get(i));
            clone.add(n);//每个克隆出来的list都添加当前遍历的n值
            ret.add(clone);
        }
    }
    return ret;
}
