/**
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.
*/

My code://需要遍历所有节点，时间复杂度高
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        return isBalanced(root.left)&&isBalanced(root.right)&&Math.abs(depth(root.left)-depth(root.right))<=1;
    }
    public int depth(TreeNode root){//获取子树的深度
        if(root==null) return 0;
        return Math.max(depth(root.left),depth(root.right))+1;
    }
}

Discuss：//O(n)方法 避免一些不必要的搜索
public boolean isBalanced(TreeNode root) {
    if(root==null){
        return true;
    }
    return height(root)!=-1;
    
}
public int height(TreeNode node){//返回-1表示该子树不平衡，即可以停止遍历
    if(node==null){
        return 0;
    }
    int lH=height(node.left);
    if(lH==-1){//判断左子树是否为平衡二叉树
        return -1;
    }
    int rH=height(node.right);
    if(rH==-1){//判断右子树是否为平衡二叉树
        return -1;
    }
    if(lH-rH<-1 || lH-rH>1){//如果左深度右深度之差大于1
        return -1;//返回-1表示不平衡，回溯之后后面的遍历计算就因为return而不进行
    }
    return Math.max(lH,rH)+1;//平衡则返回深度
}
