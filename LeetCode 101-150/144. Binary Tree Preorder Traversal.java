/**
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?
*/

My code:
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        while(root!=null||!stack.isEmpty()){
            if(root!=null){
                result.add(root.val);
                if(root.right!=null) //这里其实不用判断，直接入栈，出栈为null则会继续出栈
                    stack.push(root.right);
                root=root.left;
            }
            else
                root=stack.pop();
        }
        return result;
    }
}

Discuss：//先加中间节点，再右入栈，左入栈，因为取出来要相反
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new LinkedList<>();
    Deque<TreeNode> stack = new LinkedList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        if (node != null) {
            result.add(node.val);
            stack.push(node.right);
            stack.push(node.left);
        }
    }
    return result;
}
