/**
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.
*/

My code://1ms 99% 
class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        int left=minDepth(root.left);
        int right=minDepth(root.right);
        int mid=0;
//和求二叉树的深度不同，当左子树或者右子树为深度为0的时候不能取它为其中一个最小值的判断，因为二叉树深度的定义为：根节点到最近叶子节点的最短路径上的节点数量
        if(left==0)//其中有一个为0，则取另一个的值
            mid=right;
        else if(right==0)
            mid=left;
        else
            mid=Math.min(left,right);//都不为0，则取最小值
        return mid+1;
    }
}

Discuss：//思路一样，逻辑代码处理较好
（1）//其中有一个为0，则取另一个的值，其实可以转化为left+right，即0+另一个数
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;
       
    }
}
（2）//判断左右节点层数，少一次入栈递归操作
public static int minDepth(TreeNode root) {
	if (root == null)	return 0;
	if (root.left == null)	return minDepth(root.right) + 1;//左为0，则返回右
	if (root.right == null) return minDepth(root.left) + 1;//右为0，则返回左
	return Math.min(minDepth(root.left),minDepth(root.right)) + 1;//取最小值返回
}
