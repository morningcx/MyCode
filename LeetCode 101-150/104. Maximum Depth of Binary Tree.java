/**
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
*/

My code:
class Solution {
    public int maxDepth(TreeNode root) {
        return cal(root,0);
    }
    int cal(TreeNode root,int cur){
        if(root!=null)
            cur++;
        else
            return cur;
        return Math.max(cal(root.left,cur),cal(root.right,cur));
        
    }
}

Discuss:
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;//返回子树的深度加1，即加上当前节点的level
    }
}
