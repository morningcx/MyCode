// my code 8ms 60%
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int i = 0;
        int j = matrix[0].length - 1;
        int curr = 0;
        while ((curr = matrix[i][j]) != target) {
            if (curr < target) {
                ++i;
            } else {
                --j;
            }
            if (j < 0 || i >= matrix.length) return false;
        }
        return true;
    }
}

//discuss 一样，只是换了一种写法
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}
