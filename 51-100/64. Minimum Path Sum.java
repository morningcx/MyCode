/**
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

1 3 1 3 4    1  4  5  8  12
1 5 1 2 2    2  7  6  8  10
4 2 2 5 3 -> 6  8  8  13 13 -> min=15
1 6 3 1 4    7  13 11 12 16
6 5 1 2 1    13 18 12 14 15

My code://8ms 97%
class Solution {
    public int minPathSum(int[][] grid) {
        int row=grid.length;
        int col=grid[0].length;
        if(row==0||col==0) return 0;
        int[] line=new int[col];
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++){
                if(j==0)
                    line[j]+=grid[i][j];
                else if(i==0) //不用判断j是否溢出，因为前面判断过了
                    line[j]=grid[i][j]+line[j-1];
                else
                    line[j]=grid[i][j]+Math.min(line[j],line[j-1]);
            }
        return line[col-1];
    }
}

Discuss：//算法相同：在原数组上，不用额外数组
public int minPathSum(int[][] grid) {
	int m = grid.length;// row
	int n = grid[0].length; // column
	for(int j = 1; j < n; j++) //初始化第一行
      grid[0][j] = grid[0][j] + grid[0][j-1];
	for(int i = 1; i < m; i++) //初始化第一列
      grid[i][0] = grid[i][0] + grid[i-1][0];
	for (int i = 1; i < m; i++) {
		for (int j = 1; j < n; j++) {
				grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
		}
	}
	return grid[m - 1][n - 1];
}
