/**
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:
Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
*/

My Code://对行和列进行标记，用了O（m+n）的额外空间
class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix.length==0) return;
        int[] row=new int[matrix.length];
        int[] col=new int[matrix[0].length];
        for(int i=0;i<row.length;i++){
            for(int j=0;j<col.length;j++){
                if(matrix[i][j]==0){
                    row[i]=1;//对行标记
                    col[j]=1;//对列标记
                }
            }
        }
        for(int i=0;i<row.length;i++){
            for(int j=0;j<col.length;j++){
                if(row[i]==1||col[j]==1)
                    matrix[i][j]=0;
                }
            }
        }
}

Discuss://通过第一行和第一列标记是否占有，还解决了冲突问题
(1)//通过第一行和第一列的标记，先填充除了这两个以外的地方，最后填充这两个标记
public class Solution {
public void setZeroes(int[][] matrix) {
    boolean fr = false,fc = false;
    for(int i = 0; i < matrix.length; i++) {
        for(int j = 0; j < matrix[0].length; j++) {
            if(matrix[i][j] == 0) {
                if(i == 0) fr = true;//如果第一行中存在0，标记
                if(j == 0) fc = true;//如果第一列中存在0，标记
                matrix[0][j] = 0;//对首位行和列置0
                matrix[i][0] = 0;
            }
        }
    }
    for(int i = 1; i < matrix.length; i++) {
        for(int j = 1; j < matrix[0].length; j++) {
            if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;//对内部进行置0
            }
        }
    }
    if(fr) {//对第一行置0
        for(int j = 0; j < matrix[0].length; j++) {
            matrix[0][j] = 0;
        }
    }
    if(fc) {//对第一列置0
        for(int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 0;
        }
    }
}
}
(2)//只对第一列进行标记，对除第一列以外部分反向置0
void setZeroes(vector<vector<int> > &matrix) {
    int col0 = 1, rows = matrix.size(), cols = matrix[0].size();

    for (int i = 0; i < rows; i++) {
        if (matrix[i][0] == 0) col0 = 0;//如果第一列中存在0，标记
        for (int j = 1; j < cols; j++)
            if (matrix[i][j] == 0)
                matrix[i][0] = matrix[0][j] = 0;//对首部行和列置0
    }
    //如果如(1)正向赋值，就要排除第一行，因为会影响内部赋值，并且要多一次循环对第一行的赋值
    //反向赋值则只需略去第一列，并且循环覆盖了第一行
    for (int i = rows - 1; i >= 0; i--) {
        for (int j = cols - 1; j >= 1; j--)
            if (matrix[i][0] == 0 || matrix[0][j] == 0)
                matrix[i][j] = 0;
        if (col0 == 0) matrix[i][0] = 0;//内部行判断完成后，对第一列进行赋值
    }
}
