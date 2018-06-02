/**
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:
Surrounded regions shouldn’t be on the border, which means that any 'O' on the 
border of the board are not flipped to 'X'. Any 'O' that is not on the border 
and it is not connected to an 'O' on the border will be flipped to 'X'.Two cells 
are connected if they are adjacent cells connected horizontally or vertically.
*/

My code://8ms 89%
//首先遍历四条边界为O的节点，并且dfs判断与之连接所有O节点做好标记，然后对内部值为O并且没有标记的覆盖成X
//不过额外需要一个m*n空间和一个栈空间
class Solution {
    public void solve(char[][] board) {
        if(board.length<3) return;
        int row=board.length;
        int col=board[0].length;
        boolean[][] borderConnect=new boolean[row][col];//判断是否和边界连接，连接说明没有被包围
        for(int i=0;i<col;i++){
            if(board[0][i]=='O')
                setConnect(borderConnect,0,i,board);//第一行
            if(board[row-1][i]=='O')
                setConnect(borderConnect,row-1,i,board);//最后一行
        }
        for(int i=1;i<row-1;i++){
            if(board[i][0]=='O')
                setConnect(borderConnect,i,0,board);//第一列
            if(board[i][col-1]=='O')
                setConnect(borderConnect,i,col-1,board);//最后一列
        }
        for(int i=1;i<row-1;i++){
            for(int j=1;j<col-1;j++){
                if(board[i][j]=='O'&&!borderConnect[i][j])
                    board[i][j]='X';
            }
        }
    }
    
    public void setConnect(boolean[][] borderConnect,int row,int col,char[][] board){
        if(row<0||row>=borderConnect.length||col<0||col>=borderConnect[0].length||//越界
           borderConnect[row][col]||board[row][col]=='X') //为O并且标记过的和为X的
            return;
        borderConnect[row][col]=true;
        //上下左右
        setConnect(borderConnect,row-1,col,board);
        setConnect(borderConnect,row+1,col,board);
        setConnect(borderConnect,row,col-1,board);
        setConnect(borderConnect,row,col+1,board);
    }
}

Discuss：//算法思想一致，但是深度优先遍历有缺陷
/**
OOOOOOOOOO
XXXXXXXXXO
OOOOOOOOOO
OXXXXXXXXX
OOOOOOOOOO
XXXXXXXXXO
OOOOOOOOOO
OXXXXXXXXX
OOOOOOOOOO
XXXXXXXXXO
这里为了防止dfs的时候出现S性的矩阵出现，导致第一次dfs栈溢出，这个solution加了一个i>1而不是i>=1
避免了testcase里一个特殊案例，但是如果矩阵这样：
OOOOOOOOOOOX
XXXXXXXXXXOX
XOOOOOOOOOOX
XOXXXXXXXXXX
XOOOOOOOOOOX
XXXXXXXXXXOX
XOOOOOOOOOOX
XOXXXXXXXXXX
XOOOOOOOOOOX
XXXXXXXXXXOX
dfs依然会导致溢出，所以最好的解决方案是bfs
*/
public class Solution {
    public void solve(char[][] board) {
        int i, j;
        int row = board.length;
        if(row == 0) return;
        int col = board[0].length;
        
        for(i = 0; i < row; i++) {
            check(board, i, 0, row, col);
            if(col > 1) 
                check(board, i, col-1, row, col);
        }
        for(j=1; j +1 <col; j++) {
            check(board, 0, j, row, col);
            if(row > 1) 
                check(board, row - 1, j, row, col);
        }
        for(i =0; i < row; i++) 
            for(j = 0; j < col; j++)
                if(board[i][j]=='O')
                    board[i][j] = 'X';
        for(i=0; i < row; i++) 
            for(j = 0; j < col; j++)
                if(board[i][j]=='1')
                    board[i][j] = 'O';
    }
    
    private void check(char[][] vec, int i, int j, int row, int col) {
        if(vec[i][j]=='O') {
            vec[i][j]='1';
            if(i>1)
                check(vec, i -1, j, row, col);
            if(j>1)
                check(vec, i, j-1, row, col);
            if(i+1<row)
                check(vec, i+1, j, row, col);
            if(j+1 < col)
                check(vec, i, j+1, row, col);
        }
    }
}
