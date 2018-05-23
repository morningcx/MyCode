/**
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
*/

My code:
(1)//18ms 41% 先序遍历，右子树入栈并置左子树为null
class Solution {
    public void flatten(TreeNode root) {
        TreeNode head=root;
        TreeNode move=root;//右子树移动指针
        Stack<TreeNode> stack=new Stack<>();
        while(root!=null||!stack.isEmpty()){
            while(root!=null){
                if(root!=head){//第一次根节点不用赋值
                    move.right=root;
                    move=move.right;
                }
                stack.push(root.right);//右子树入栈
                TreeNode pre=root.left;//删除左子树
                root.left=null;
                root=pre;
            }
            root=stack.pop();
        }
    }
}
(2)//递归方法，分治法
class Solution {
    public void flatten(TreeNode root) {
        if(root==null) return ;
        flatten(root.left);//对左压平
        flatten(root.right);//对右压平
        if(root.left!=null){//如果左子树不存在，不需要对左子树进行插入右子树操作，只需要对右子树进行压平
            TreeNode right=root.right;
            root.right=root.left;
            TreeNode cur=root.left;
            //如果没有判断则cur==null，cur.right可能会抛出异常
            while(cur.right!=null) cur=cur.right;//对左子树求最大值，以作为连接右子树的节点
            cur.right=right;
            root.left=null;
        }
    }
}

Discuss：
(1)//其实和我(2)的方法一样
public void flatten(TreeNode root) {
        if (root == null) return;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.left = null;
        
        flatten(left);
        flatten(right);
        
        root.right = left;//直接将left赋给右子树，然后求最大节点(这样操作就不用担心异常，因为当前节点不为null)
        TreeNode cur = root;//这个多余了
        while (cur.right != null) cur = cur.right;//即使cur.right==null也不会进行循环
        cur.right = right;//赋值给最大节点的右子树
    }
    
(2)//后序遍历为基础
public void flatten(TreeNode root) {
    flatten(root,null);
}
private TreeNode flatten(TreeNode root, TreeNode pre) {
    if(root==null) return pre;//为null则需要添加的节点为根节点
    pre=flatten(root.right,pre);//先对右子树进行压平，返回根节点
    //处理好的右子树就可以视为一个右节点，在处理左子树的时候会添加
    pre=flatten(root.left,pre);//对左子树进行压平，并且连接右子树，返回根节点
    root.right=pre;//右子树赋给已经处理好的节点
    root.left=null;
    pre=root;
    return pre;//返回处理好的根节点
}
