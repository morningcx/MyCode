/**
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 

1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
*/

My code://11ms 97%
//dfs每一个节点当做正方形的左顶点，然后一个个判断能组成正方形的最大值
class Solution {
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;
        int max = 0;
        for (int i = 0; i < row - max; ++i) {//最大值实时更新，可以免去不必要的遍历
            for (int j = 0; j < col - max; ++j) {
                max = Math.max(max, maxSquare(matrix, max, i, j));
            }
        }
        return max*max;
        
    }
    
    private int maxSquare(char[][] matrix, int max, int i, int j) {
        if (matrix[i][j] == '0' || i + max + 1 > matrix.length || j + max + 1 > matrix

[0].length) 
            return 0;
        for (int row = i; row < i + max + 1; ++row) {
            for (int col = j; col < j + max + 1; ++col) {
                if (matrix[row][col] == '0')
                    return 0;
            }
        }
        return Math.max(max + 1, maxSquare(matrix, max + 1, i, j));
    }
}

Discuss://动态规划
//dp[i][j] 代表在以i, j这一格为右下角的正方形边长。
//如果这一格的值是1，那这个正方形的边长就是他的上面，左手边，和斜上的值的最小边长 +1。
//左边值表示左边拥有连续节点长度的最小值，右边同理
//斜上值表示可以组成正方形的边长
//然后取上述三者的最小值+1，即为以当前节点作为正方形右下角能组成最大正方形的边长
public int maximalSquare(char[][] a) {
    if(a.length == 0) return 0;
    int m = a.length, n = a[0].length, result = 0;
    int[][] b = new int[m+1][n+1];
    for (int i = 1 ; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if(a[i-1][j-1] == '1') {
                b[i][j] = Math.min(Math.min(b[i][j-1] , b[i-1][j-1]), b[i-1][j]) + 1;
                result = Math.max(b[i][j], result); // update result
            }
        }
    }
    return result*result;
}
