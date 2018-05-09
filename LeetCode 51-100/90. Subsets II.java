/**
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

My code://4ms 99% DFS深度遍历优先
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);//子集需要排序，因为元素相同顺序不同算一个子集，全排列不需要
        List<List<Integer>> result=new ArrayList<>();
        cal(nums,result,new ArrayList<>(),0,nums.length-1);//第一次加入空集
        return result;
    }
    void cal(int[] nums,List<List<Integer>> result,List<Integer> list,int start,int end){//end其实不需要设置
        result.add(new ArrayList<>(list));
        for(int i=start;i<=end;i++)
            if(!exist(nums,start,i-1,nums[i])){//前面不存在重复元素
                list.add(nums[i]);
                cal(nums,result,list,i+1,end);//对i+1至end递归
                list.remove(list.size()-1);
            }
    }
    boolean exist(int[] nums,int start,int end,int target){//判断是否存在target
        for(int i=start;i<=end;i++)
            if(nums[i]==target)
                return true;
        return false;
    }
}

Discuss：
（1）标准DFS算法，一些地方处理的比我好
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result= new ArrayList<>();
        dfs(nums,0,new ArrayList<Integer>(),result);
        return result;
    }
    
    public void dfs(int[] nums,int index,List<Integer> path,List<List<Integer>> result){//end不需要设置
        result.add(path);
        for(int i=index;i<nums.length;i++){
            if(i>index&&nums[i]==nums[i-1]) continue;//因为nums已经排过序，所有判断前一个元素是否重复，既可判断是否可递归
            List<Integer> nPath= new ArrayList<>(path);
            nPath.add(nums[i]);
            dfs(nums,i+1,nPath,result);
        }
    }
}
（2）//有重复元素，则在前一个基础上加上一个重复元素，不重复则从0开始在后面添加不重复元素（还是有点不清楚）
//比如[1,1,1,2,2]
//[]+1+11+111 + 2+12+112+1112 + 22+122+1122+11122 
public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
	List<List<Integer>> result = new ArrayList<List<Integer>>();
	result.add(new ArrayList<Integer>());
	int begin = 0;
	for(int i = 0; i < nums.length; i++){
		if(i == 0 || nums[i] != nums[i - 1]) begin = 0;//不为重复元素则从0开始添值
		int size = result.size();//长度
		for(int j = begin; j < size; j++){
			List<Integer> cur = new ArrayList<Integer>(result.get(j));
			cur.add(nums[i]);
			result.add(cur);
		}
		begin = size;//前一个不是重复元素的位置
	}
	return result;
}
