// my code 4ms 33%
class Solution {
    public Node construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length);
    }
    private Node dfs(int[][] grid, int x, int y, int len) {
        if (len == 1) {
            return new Node(grid[x][y] == 1, true, null, null, null, null);
        }
        int halfLen = len >> 1;
        Node topLeft = dfs(grid, x, y, halfLen);
        Node topRight = dfs(grid, x, y + halfLen, halfLen);
        Node bottomLeft = dfs(grid, x + halfLen, y, halfLen);
        Node bottomRight = dfs(grid, x + halfLen, y + halfLen, halfLen);
        if (topLeft.val && topRight.val && bottomLeft.val && bottomRight.val) {
            return new Node(true, true, null, null, null, null);
        } else {
            if (topLeft.isLeaf && !topLeft.val && 
                topRight.isLeaf && !topRight.val && 
                bottomLeft.isLeaf && !bottomLeft.val && 
                bottomRight.isLeaf && !bottomRight.val) {
                return new Node(false, true, null, null, null, null);
            } else {
                return new Node(false, false, topLeft, topRight, 
                                bottomLeft, bottomRight);
            }
        }
    }
    
// discuss
    class Solution {
    public Node construct(int[][] grid) {
        return helper(grid, 0, 0, grid.length);
    }
    private Node helper(int[][] grid, int x, int y, int len) {
        if (len == 1) {
            return new Node(grid[x][y] != 0, true, null, null, null, null);
        }
        Node result = new Node();
        Node topLeft = helper(grid, x, y, len / 2);
        Node topRight = helper(grid, x, y + len / 2, len / 2);
        Node bottomLeft = helper(grid, x + len / 2, y, len / 2);
        Node bottomRight = helper(grid, x + len / 2, y + len / 2, len / 2);
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf // 都是根节点
           && topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) { // 并且数值都一致
            result.isLeaf = true;
            result.val = topLeft.val;
        } else {
            result.topLeft = topLeft;
            result.topRight = topRight;
            result.bottomLeft = bottomLeft;
            result.bottomRight = bottomRight;
        }
        return result;
    }
}
    
    
    
    
    
    
    
}
