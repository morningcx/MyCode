/**
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:

Input:
    2
   / \
  1   3
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
             is 5 but its right child's value is 4.
*/

My code://1ms 97%
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root==null) return true;
        return (root.left==null||root.val>root.left.val)&&
            (root.right==null||root.val<root.right.val)&&
            isValidBST(root.left)&&//先判断是否为BST
            isValidBST(root.right)&&
            (root.left==null||root.val>maxVal(root.left))&&//再进行最大值最小值和根节点的判断
            (root.right==null||root.val<minVal(root.right));
    }
    public int minVal(TreeNode root){//返回这颗树的最小值
        if(root.left==null)
            return root.val;
        return minVal(root.left);
    }
    public int maxVal(TreeNode root){
        if(root.right==null)
            return root.val;
        return maxVal(root.right);
    }
}

Discuss:
(1)//将当前节点的val作为上下限传递给下一个根节点，递归判断
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }
    
    boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;
        
        if ((min != null && root.val <= min) || (max != null && root.val >= max))//超上下限则返回false
            return false;
        //将当前节点的val传递给下一个根节点
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}
(2)//判断是否为BST其实可以中序遍历二叉树，每一个后继节点必将大于当前节点，每一个前继节点也必将小于当前节点
public boolean isValidBST(TreeNode root) {
   if (root == null) return true;
   Stack<TreeNode> stack = new Stack<>();
   TreeNode pre = null;//直接前继节点
   while (root != null || !stack.isEmpty()) {//中序遍历
      while (root != null) {
         stack.push(root);
         root = root.left;
      }
      root = stack.pop();
      if(pre != null && root.val <= pre.val) return false;//如果前继节点值大于等于当前节点，false
      pre = root;//标记当前节点为下一个循环节点的前继节点
      root = root.right;
   }
   return true;
}
