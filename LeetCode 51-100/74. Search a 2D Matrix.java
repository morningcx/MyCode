/**
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
*/

My code://先对第一列进行二分查找，找出第一个首部元素小于target的行，然后对那行进行第二次二分查找
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0) return false;
        int start=0;
        int end=matrix.length-1;
        while(start<end){
            int mid=start+(end-start)/2+1;
            if(matrix[mid][0]<target)
                start=mid;
            else if(matrix[mid][0]>target)
                end=mid-1;
            else
                return true;
        }
        int row=start;
        start=0;
        end=matrix[row].length-1;
        while(start<end){
            int mid=start+(end-start)/2;
            if(matrix[row][mid]<target)
                start=mid+1;
            else if(matrix[row][mid]>target)
                end=mid-1;
            else
                return true;
        }
        if(matrix[row][start]==target)
            return true;
        return false;
    }
}

Discuss：//把整个矩阵视为排序好的一维数组，进行二分查找
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        if (matrix == null || matrix.length == 0 || matrix[0].length ==0) {
            return false;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        int left = 0;
        int right = row * col -1;
        
        while (left<=right){//小于等于多一次判断，因此要把==放在第一个判断
            int mid = left + (right - left)/2;
            if (target == matrix[mid/col][mid%col]){//对row和col的数学换算
                return true;
            } else if (target < matrix[mid/col][mid%col]){
                right = mid-1;
            } else {
                left = mid +1;
            }
        }
        return false;
    }
}
