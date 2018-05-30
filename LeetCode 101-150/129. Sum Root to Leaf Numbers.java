/**
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
*/

My code://1ms 80%
//从根节点出发，向下遍历，直到叶子节点返回结果
class Solution {
    public int sumNumbers(TreeNode root) {
        if(root==null) return 0;
        return sum(root,0);
    }
    public int sum(TreeNode root,int num){
        if(root.left==null&&root.right==null)//叶子节点不用x10直接返回
            return num+root.val;
        int left=0;
        int right=0;
        if(root.left!=null){
            left=sum(root.left,(num+root.val)*10);//左节点，传递x10以后的数值
        }
        if(root.right!=null){
            right=sum(root.right,(num+root.val)*10);//右节点，传递x10以后的数值
        }
        return left+right;//左右节点所有情况之和
    }
}

Discuss：//思想一致
class Solution {
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }
    
    public int sumNumbers(TreeNode root, int sum) {
        if (root == null) return 0;//为空返回0
        sum = sum * 10 + root.val;//传递下来再x10加本身
        if (root.left == null && root.right == null) {//叶子节点左右都为null，但不能返回0+0，要返回传递下来sumx10加上自身值的sum
            return sum;
        }
        return sumNumbers(root.left, sum) + sumNumbers(root.right, sum);
    }
}

