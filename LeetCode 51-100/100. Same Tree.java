/**
Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false
*/

My code://对每个节点进行判断，递归
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null)//节点同为null
            return true;
        if(p==null||q==null)//节点其中有一个为null，其中一个不为null
            return false;
        if(p.val==q.val)//两个节点都存在并且数组相等
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);//对左右节点递归
        else
            return false;
    }
}

Discuss：//前两步合成一步，非常巧妙
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == NULL || q == NULL) 
        return (p == q);//都为null则true，否则false
    if(p.val == q.val)
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    return false;
}

