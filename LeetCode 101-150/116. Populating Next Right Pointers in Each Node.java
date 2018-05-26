/**
Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
Example:

Given the following perfect binary tree,

     1
   /  \
  2    3
 / \  / \
4  5  6  7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL
*/

My code://和层序遍历有点相似，然后连接
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root==null) return ;
        Queue<TreeLinkNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeLinkNode node=queue.poll();
                if(i!=size-1)//当前层数上最后一个点不用连接
                    node.next=queue.peek();
                if(node.left!=null) 
                    queue.offer(node.left);
                if(node.right!=null) 
                    queue.offer(node.right);
            }
        }
    }
}

Discuss：
（1）//递归，虽然用了额外栈空间，但是清晰快速
public class Solution {
    public void connect(TreeLinkNode root) {
        if( root == null )
            return;
        if( root.left != null )//左节点连接右节点
        {
            root.left.next = root.right;
        }
        
        if( root.right != null)
        {
            if( root.next != null )//next节点不为空则继续连接
            {
                root.right.next = root.next.left;
            }
        }
        
        connect( root.left );
        connect( root.right );
    }
（2）//O（1）空间，O（n）时间复杂度
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode level_start=root;//记录当前层数的第一个节点
        while(level_start!=null){
            TreeLinkNode cur=level_start;
            while(cur!=null){
                if(cur.left!=null) cur.left.next=cur.right;//连接下一层节点的next
                if(cur.right!=null && cur.next!=null) cur.right.next=cur.next.left;//右节点多一个边界的判断
                
                cur=cur.next;//next节点
            }
            level_start=level_start.left;//赋值为下一层节点的第一个节点
        }
    }
}
