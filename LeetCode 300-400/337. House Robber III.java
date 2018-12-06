// my code 6ms 43%
class Solution {
    private Map<TreeNode, Integer> notRobMap = new HashMap<>();
    private Map<TreeNode, Integer> robMap = new HashMap<>();
    public int rob(TreeNode root) {
        return Math.max(maxRob(true, root), maxRob(false, root));
    }
    
    private int maxRob(boolean canRob, TreeNode root) {
        if (root == null) return 0;
        Integer notRob = notRobMap.get(root);
        if (notRob == null) {
            notRob = maxRob(true, root.left) + maxRob(true, root.right);
            notRobMap.put(root, notRob);
        }
        if (canRob) {
            Integer rob = robMap.get(root);
            if (rob == null) {
                rob = root.val + maxRob(false, root.left) + maxRob(false, root.right);
                robMap.put(root, rob);
            }
            notRob = Math.max(notRob, rob);
        }
        return notRob;
    }
}

// discuss
public int rob(TreeNode root) {
    int[] res = robSub(root);
    return Math.max(res[0], res[1]);
}

private int[] robSub(TreeNode root) {
    if (root == null) return new int[2];
    
    int[] left = robSub(root.left);
    int[] right = robSub(root.right);
    int[] res = new int[2];

    res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // 当前节点没偷，存在两种情况
    res[1] = root.val + left[0] + right[0]; // 当前节点偷了
    
    return res;
}
