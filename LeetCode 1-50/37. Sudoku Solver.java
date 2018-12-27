// my code 2ms 98% 
// 自己只做到唯一数字的填充，一旦初始填充数独的数字不到20个，或者运气不好，则单一的确定算法是填充不满的
// 然后也算是借鉴了discuss的最直接方法，把时间缩到98%
// 还可以优化，将未填充节点可能数值的数量进行排序，先从可能性最少的节点开始穷举（可以用优先队列）
class Solution {
    private boolean[][] row = new boolean[9][9]; // true代表该数字已经被占
    private boolean[][] col = new boolean[9][9];
    private boolean[][] block = new boolean[9][9];
    public void solveSudoku(char[][] board) {
        // 先确定每一行，每一列，每一块的数字限制
        check(board);
        // 检查当前未填充节点是否为唯一确定的数字，是的话则更新所在的行列块数字限制，
        // 并且动态关联自己所在的三个区域中所有的未填充节点，再进行dfs
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9 ; ++j) {
                dfs(i, j, board);
            }
        }
        // 如果上述算法运气好，每个未填充节点都存在确定的数值，则填充完成
        // 如果执行完上述算法，出现每个未填充节点都存在两个及以上的数值可能，则需要穷举
        // 当然还可以优化，将未填充节点可能数值的数量进行排序，先从可能性最少的节点开始穷举（可以用优先队列）
        // 下面借鉴了discuss的穷举算法（这里就是按顺序穷举，没有按优先穷举）
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9 ; ++j) {
                if (board[i][j] == '.') {
                    dfsTry(board, 0, 0);
                    return ;
                }
            }
        }
    
    }
    
    // 每次dfs不必从头开始遍历数独，dfs下一个节点既可
    private boolean dfsTry(char[][] board, int startRow, int startCol) {
        for (int i = startRow; i < 9; ++i, startCol = 0) { // 每次新行需要把startCol重置为0
            for (int j = startCol; j < 9 ; ++j) { // j + 1 > 8了也没关系，这里不会执行循环，跳出去把startCol置为0
                if (board[i][j] == '.') {
                    int index = j / 3 + (i / 3) * 3;
                    for (int k = 0; k < 9; ++k) {
                        if (!row[i][k] && !col[j][k] && !block[index][k]) {
                            board[i][j] = (char) (k + 49);
                            row[i][k] = true;
                            col[j][k] = true;
                            block[index][k] = true;
                            if (dfsTry(board, i, j + 1)) return true; 
                            else {
                                board[i][j] = '.';
                                row[i][k] = false;
                                col[j][k] = false;
                                block[index][k] = false;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private void dfs(int i, int j, char[][] board) {
        if (board[i][j] != '.') {
            return ;
        }
        int index = j / 3 + (i / 3) * 3, count = 0;
        char target = '#';
        for (int k = 0; k < 9; ++k) {
            if (!row[i][k] && !col[j][k] && !block[index][k]) {
                target = (char) (k + 49);
                ++count;
            }
        }
        if (count == 1) {
            int pos = target - 49;
            board[i][j] = target;
            row[i][pos] = true;
            col[j][pos] = true;
            block[index][pos] = true;
            for (int k = 0; k < 9; ++k) {
                dfs(i, k, board);
                dfs(k, j, board);
                dfs((index / 3) * 3 + k / 3, (index % 3) * 3 + k % 3, board);
            }
        }
    }
    
    private void check(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            checkRow(i, board);
            checkCol(i, board);
            checkBlock(i, board);
        }
    }
    /* check可以简化成这样
    private void check(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if(board[i][j]!='.') {
                    int num = board[i][j] - '1'; 
                    int k = i / 3 * 3 + j / 3;
                    row[i][num] = col[j][num] = block[k][num] = true;
                }
            }
        }
    }
    */
    
    private void checkRow(int i, char[][] board) {
        for (int k = 0; k < 9; ++k) { // row
            char num = board[i][k];
            if (num != '.') {
                row[i][num - 49] = true;
            }
        }
    }
    
    private void checkCol(int j, char[][] board) {
        for (int k = 0; k < 9; ++k) { // col
            char num = board[k][j];
            if (num != '.') {
                col[j][num - 49] = true;
            }
        }
    }
    
    private void checkBlock(int index, char[][] board) {
        int i = (index / 3) * 3;
        int j = (index % 3) * 3;
        for (int k = 0; k < 9; ++k) { // block
            char num = board[i + k / 3][j + k % 3];
            if (num != '.') {
                block[index][num - 49] = true;
            }
        }
    }
}


// discuss 10ms 借鉴的就是这个算法，这里他没有一开始就把范围缩小至唯一确定的值，所以dfs范围比我的大，速度比较慢
 public void solveSudoku(char[][] board) {
        doSolve(board, 0, 0);
    }
    
    private boolean doSolve(char[][] board, int row, int col) {
        for (int i = row; i < 9; i++, col = 0) { // note: must reset col here!
            for (int j = col; j < 9; j++) {
                if (board[i][j] != '.') continue;
                for (char num = '1'; num <= '9'; num++) {
                    if (isValid(board, i, j, num)) {
                        board[i][j] = num;
                        if (doSolve(board, i, j + 1))
                            return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char num) {
        int blkrow = (row / 3) * 3, blkcol = (col / 3) * 3; // Block no. is i/3, first element is i/3*3
        for (int i = 0; i < 9; i++)
            if (board[i][col] == num || board[row][i] == num || 
                    board[blkrow + i / 3][blkcol + i % 3] == num)
                return false;
        return true;
    }
    

// discuss2 思路也是dfs，一样是回溯，代码简洁
private char[][] b;
private boolean[][] row = new boolean[9][9];
private boolean[][] col = new boolean[9][9];
private boolean[][] block = new boolean[9][9];
public void solveSudoku(char[][] board) {
    b = board;
    int num, k;
    for (int i=0; i<9; i++) {
        for (int j=0; j<9; j++) {
            if(board[i][j]!='.') {
                num = board[i][j]-'1'; 
                k = i/3*3 + j/3;
                row[i][num] = col[j][num] = block[k][num] = true;
            }
        }
    }
    Helper(0);
}
public boolean Helper(int ind){
    if(ind==81) return true; 
    int i=ind/9, j=ind%9, num, k;
    if(b[i][j]!='.') return Helper(ind+1);
    else{
        for(char f='1'; f<='9'; f++){
            num = f-'1'; 
            k= i/3*3 + j/3;
            if(!row[i][num] && !col[j][num] && !block[k][num]){
                b[i][j]= f;
                row[i][num] = col[j][num] = block[k][num] = true;
                if(Helper(ind+1)) return true;                
                b[i][j]='.';
                row[i][num] = col[j][num] = block[k][num] = false;
            }
        }
        return false;
    }
}
