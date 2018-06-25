/**
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
*/

My code://2ms 90% 
//DFS和discuss一模一样
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        addRight(root, list, 1);
        return list;
    }
    
    private void addRight(TreeNode root, List<Integer> list, int level){
        if (root == null) return ;
        if (list.size() < level) list.add(root.val);//当前层数没有节点说明当前节点是最右节点
        addRight(root.right, list, level + 1);//优先遍历右子树
        addRight(root.left, list, level + 1);
    }
}

Discuss：//BFS层序遍历二叉树，把每层的最后一个节点加入result，速度不如DFS，入队出队操作较消耗时间
//一开始就是这样的想法，后来感觉递归更好一些
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return result;
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    // 每层最后一个节点
                    result.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }
}
