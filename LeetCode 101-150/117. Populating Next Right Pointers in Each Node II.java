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
Example:

Given the following binary tree,

     1
   /  \
  2    3
 / \    \
4   5    7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL
*/

My code://the same as I ,it's slow
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root==null) return ;
        Queue<TreeLinkNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeLinkNode node=queue.poll();
                if(i!=size-1)
                    node.next=queue.peek();
                if(node.left!=null) 
                    queue.offer(node.left);
                if(node.right!=null) 
                    queue.offer(node.right);
            }
        }
    }
}

Discuss:
(1)//创建一个头结点来标记每层第一个节点
//（每层第一个节点并不是上一层第一个节点的左右节点，所以需要遍历所有上层节点的子节点来判断当前层的第一结点）
public void connect(TreeLinkNode root) {
    TreeLinkNode tempChild = new TreeLinkNode(0);//头结点（用来标记）next才是第一节点
    while (root != null) {
        TreeLinkNode currentChild = tempChild;//当前节点，会不断移动
        while (root != null) {
            if (root.left != null) {//存在左节点
                currentChild.next = root.left;//赋next
                currentChild = currentChild.next;//移动
            }
            if (root.right != null) {
                currentChild.next = root.right;
                currentChild = currentChild.next;
            }
            root = root.next;//移动上层节点
        }
        root = tempChild.next;//重新回到头结点指向的第一节点遍历
        tempChild.next = null;//把第一节点清空
    }
}
(2)//用一个head作为标记当前层第一节点
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode head=root;//The left most node in the lower level 第一个节点
        TreeLinkNode prev=null;//The previous node in the lower level 前一个节点
        TreeLinkNode curr=null;//The current node in the upper level 当前节点
        while (head!=null){
            curr=head;//重新赋值当前节点为head（第一节点）
            prev=null;//初始化
            head=null;//初始化
            while (curr!=null){
                if (curr.left!=null){
                    if (prev!=null)//存在前一个节点
                        prev.next=curr.left;
                    else //不存在前一个节点，说明当前节点为第一个节点
                        head=curr.left;
                    prev=curr.left;//把当前节点作为下一次的前一个节点
                }
                //右节点相同操作
                if (curr.right!=null){
                    if (prev!=null)
                        prev.next=curr.right;
                    else 
                        head=curr.right;
                    prev=curr.right;
                }
                curr=curr.next;//next
            }
        }
    }
}
(3)//一个while的操作
public void connect(TreeLinkNode root) {
    TreeLinkNode dummyHead = new TreeLinkNode(0);//头结点
    TreeLinkNode pre = dummyHead;//前一个节点
    while (root != null) {
	    if (root.left != null) {
		    pre.next = root.left;
		    pre = pre.next;
	    }
	    if (root.right != null) {
		    pre.next = root.right;
		    pre = pre.next;
	    }
	    root = root.next;
	    if (root == null) {//如果没有next操作
		    pre = dummyHead;//前一个节点初始化为头结点
		    root = dummyHead.next;//将root赋值为第一个节点
		    dummyHead.next = null;//把头结点的next数据清空
	    }
    }
}
