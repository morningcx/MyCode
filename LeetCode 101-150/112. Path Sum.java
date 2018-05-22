/**
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/

My code://1ms 99%
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null)
            return false;
            
        //要判断是否为叶子节点，并判断两值是否相等
        if(root.left==null&&root.right==null)
            return sum==root.val;
        //其中一个子树为null则分情况返回
        else if(root.left==null&&root.right!=null)//其实这里root.left不需要判断，递归传递下去回到第一个root==null的判断就会返回false
            return hasPathSum(root.right,sum-root.val);
        else if(root.right==null&&root.left!=null)
            return hasPathSum(root.left,sum-root.val);
            
        //子树都不为null
        return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);
    }
}

Discuss://思想一样，逻辑处理较好
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        
        //只需要判断是否为叶子节点既可
        if(root.left == null && root.right == null)
            return sum == root.val;
            
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
