/**
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

*/

My code://6ms 93%
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int count=0;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i:inorder)
            map.put(i,count++);
        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1,map);
    }
    public TreeNode build(int[] preorder,int start,int end,int[] inorder,int s,int e,Map<Integer,Integer> map){
        if(start>end)
            return null;
        TreeNode root=new TreeNode(preorder[start]);//第一个节点即为根节点
        int count=map.get(root.val);//获取start节点在中序中的位置，中序遍历中左边为start的左子树节点，右边为右子树节点
        //count-s表示start节点左边元素的个数，因为是基于先序数组的，所以要取start+1到start+count-s为左子树
        //start+count-s+1到end为右子树，然后再分开判断
        root.left=build(preorder,start+1,start+count-s,inorder,s,count-1,map);
        root.right=build(preorder,start+count-s+1,end,inorder,count+1,e,map);//其实e不用传递
        return root;
    }
}

Discuss:// O(n)算法，比较难以理解
class Solution {
    private int in = 0;//中序标识
    private int pre = 0;//先序标识
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }
    
    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre >= preorder.length) return null;
        if (inorder[in] == stop) {//判断传递下来的节点是否已经添加完毕
            in++;
            return null;
        }
        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = build(preorder, inorder, node.val);//把当前节点当作根节点传递下去
        node.right = build(preorder, inorder, stop);
        return node;        
    }
}
