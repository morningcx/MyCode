
//My code
//当p、q节点分别在root节点的两边时，最小祖先节点值即为root
//当p、q节点都比root小，或者大时，需要重新判断对应的子节点
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode big = p.val > q.val ? p : q;
        TreeNode small = p == big ? q : p;
        if (root.val <= big.val && root.val >= small.val) {
            return root;
        } else if (root.val < small.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }
}

//Discuss
//(1)判断两节点是否为一边的既可
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }
    }
}

//(2)通过root和p、q相减乘积结果正负来判断是否为一边
//然后循环内部判断是哪一边的
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while ((root.val - p.val) * (root.val - q.val) > 0)
        root = p.val < root.val ? root.left : root.right;
    return root;
}
