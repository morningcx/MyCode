/**
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
*/

My  Code:
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return is(root.left,root.right);
    }
    boolean is(TreeNode root1,TreeNode root2){
        if(root1==null||root2==null)
            return root1==root2;
        if(root1.val==root2.val)
            return is(root1.left,root2.right)&&is(root1.right,root2.left);
        else
            return false;
    }
}
