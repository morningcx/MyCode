/**
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
My code:
（1）递归
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        inOrder(result,root);
        return result;
    }
    void inOrder(List<Integer> list,TreeNode root){
        if(root==null) return ;
        inOrder(list,root.left);
        list.add(root.val);
        inOrder(list,root.right);
    }
}
（2）迭代1 一个while
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>(); 
        while(root!=null||!stack.isEmpty()){
            if(root!=null){ //不为空则入栈，指向left
                stack.push(root);
                root=root.left;
            }
            else{//为空则出栈，添加父节点，对right进行相同操作
                root=stack.pop();
                result.add(root.val);
                root=root.right;
            }
        }
        return result;
    }
}
（3）迭代2 2个while 原理都一样
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<Integer>();

    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode cur = root;

    while(cur!=null || !stack.empty()){
        while(cur!=null){ //和（2）只是换了一个形式的循环
            stack.add(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        list.add(cur.val);
        cur = cur.right;
    }

    return list;
}
