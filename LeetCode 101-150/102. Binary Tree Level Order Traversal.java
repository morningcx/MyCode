/**
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
*/

My Code://3ms 35%  对下一层所有节点先进行从左到右的记录，然后添加当前层所有节点数值
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) return new ArrayList<>();
        List<List<Integer>> result=new ArrayList<>();
        Queue<TreeNode> holder=new LinkedList<>();
        holder.offer(root);
        while(!holder.isEmpty()){
            List<Integer> list=new ArrayList<>();
            Queue<TreeNode> mid=new LinkedList<>();//新建队列
            while(!holder.isEmpty()){
                TreeNode first=holder.poll();//记录出队元素
                list.add(first.val);
                if(first.left!=null) mid.offer(first.left);
                if(first.right!=null) mid.offer(first.right);
            }
            result.add(list);
            holder=mid;
        }
        return result;
    }
}

Discuss：
（1）2ms 86% //思路相同，比我少记录新建队列的那一步骤，加快了速度
List<List<Integer>> res = new ArrayList<>();  
if (root == null) return res;  
Queue<TreeNode> queue = new LinkedList<>();  
queue.add(root);  
while (!queue.isEmpty()) {  
　　List<Integer> level = new ArrayList<>();  
　　int cnt = queue.size();  //记录上层节点数量
　　for (int i = 0; i < cnt; i++) {  //利用cnt记录的数量循环出队操作，可以避免遍历到在后面添加的元素
　　　　TreeNode node = queue.poll();  
　　　　level.add(node.val);  
　　　　if (node.left != null) {  
　　　　　　queue.add(node.left);  //直接加在queue后面，遍历不到
　　　　}
　　　　if (node.right != null) {  
　　　　　　queue.add(node.right);  
　　　　}  
　　}  
　　res.add(level);   
}  
return res;

（2）//DFS深度优先遍历 对每一层添加一个空list，先序遍历二叉树，对各层进行添加操作
public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        return res;
    }
    
    public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height >= res.size()) {
            res.add(new LinkedList<Integer>());//有大于最大层并且不为null的元素存在，则添加新层
        }
        res.get(height).add(root.val);//添加当前根节点
        levelHelper(res, root.left, height+1);
        levelHelper(res, root.right, height+1);
    }
