/**
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
*/

My code://2ms 100% 
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result=new ArrayList<>();
        searchSum(result,new ArrayList<>(),root,sum);
        return result;
    }
    public void searchSum(List<List<Integer>> result,List<Integer> list,TreeNode root, int sum){
        if(root==null) return ;//当前节点为null，说明到叶子节点的时候root.val!=sum说明不相等，则不添加直接结束函数
        list.add(root.val);
        if(root.left==null&&root.right==null&&sum==root.val){
            result.add(new ArrayList<>(list));
            list.remove(list.size()-1);//需要回溯
            return ;
        }
        searchSum(result,list,root.left,sum-root.val);
        searchSum(result,list,root.right,sum-root.val);
        list.remove(list.size()-1);//回溯
    }
}

Discuss：//思路完全一样，但是回溯条件只需要调用一次
public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>>ret = new ArrayList<List<Integer>>(); 
    List<Integer> cur = new ArrayList<Integer>(); 
    pathSum(root, sum, cur, ret);
    return ret;
}

public void pathSum(TreeNode root, int sum, List<Integer>cur, List<List<Integer>>ret){
    if (root == null){
        return; 
    }
    cur.add(root.val);
    if (root.left == null && root.right == null && root.val == sum){
        ret.add(new ArrayList(cur));
    }else{
        pathSum(root.left, sum - root.val, cur, ret);
        pathSum(root.right, sum - root.val, cur, ret);
    }
    cur.remove(cur.size()-1);//只需调用一次回溯
}
