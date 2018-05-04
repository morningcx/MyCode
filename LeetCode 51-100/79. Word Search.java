/**
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/

My code:
（1）一开始做的，开始总以为没那么麻烦，一直在想其他算法，判断"是否访问过"用了很多冗余空间
class Solution {//188ms 6%  
    public boolean exist(char[][] board, String word) {
        if(word.length()==0) return false;
        char first=word.charAt(0);
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==first&&cal(new HashSet<>(),word,i,j,1,board))
                    return true;
            }
        }
        return false;
    }
    boolean cal(Set<List<Integer>> exist,String word,int i,int j,int num,char[][] board){
        List<Integer> list=new ArrayList<>();
        list.add(i);list.add(j);
        if(exist.contains(list))
            return false;
        Set<List<Integer>> set=new HashSet<>(exist);
        set.add(list);
        if(num==word.length()) return true;
        char next=word.charAt(num);
        if(i>0&&board[i-1][j]==next&&cal(set,word,i-1,j,num+1,board)
          ||i<board.length-1&&board[i+1][j]==next&&cal(set,word,i+1,j,num+1,board)
          ||j>0&&board[i][j-1]==next&&cal(set,word,i,j-1,num+1,board)
          ||j<board[0].length-1&&board[i][j+1]==next&&cal(set,word,i,j+1,num+1,board))
            return true;
        return false;
    }
}
（2）第二次参考了一部分submission，为了判断是否访问过设置一个全局二维布朗数组
class Solution {//17ms 62%
    boolean[][] visit;
    public boolean exist(char[][] board, String word) {
        if(word.length()==0) return false;
        visit=new boolean[board.length][board[0].length];
        char first=word.charAt(0);
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==first&&cal(word,i,j,1,board))
                    return true;
            }
        }
        return false;
    }
    boolean cal(String word,int i,int j,int num,char[][] board){
        if(visit[i][j])
            return false;
        if(num==word.length()) 
            return true;
        visit[i][j]=true;
        char next=word.charAt(num);
        if(i>0&&board[i-1][j]==next&&cal(word,i-1,j,num+1,board)
          ||i<board.length-1&&board[i+1][j]==next&&cal(word,i+1,j,num+1,board)
          ||j>0&&board[i][j-1]==next&&cal(word,i,j-1,num+1,board)
          ||j<board[0].length-1&&board[i][j+1]==next&&cal(word,i,j+1,num+1,board))
            return true;
        visit[i][j]=false;
        return false;
    }
}
（3）第三次不再使用额外布朗数组，访问过后的元素置'#'以便标记，下次访问屏蔽
class Solution {
    public boolean exist(char[][] board, String word) {
        if(word.length()==0) return false;
        char first=word.charAt(0);
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[i].length;j++)
                if(board[i][j]==first&&cal(word,i,j,1,board))//遍历，查找到第一个元素则进入判断
                    return true;
        return false;
    }
    boolean cal(String word,int i,int j,int num,char[][] board){
        if(num==word.length())//当最后一个元素找到，越界说明已经全部找到，返回true
            return true;
        char temp=board[i][j];//记录当前字符值，不是查询的目标则要进行回溯
        board[i][j]='#';//屏蔽
        char next=word.charAt(num);
        if(i>0&&board[i-1][j]==next&&cal(word,i-1,j,num+1,board)//上
          ||i<board.length-1&&board[i+1][j]==next&&cal(word,i+1,j,num+1,board)//下
          ||j>0&&board[i][j-1]==next&&cal(word,i,j-1,num+1,board)//左
          ||j<board[0].length-1&&board[i][j+1]==next&&cal(word,i,j+1,num+1,board))//右
            return true;
        board[i][j]=temp;//当对周围遍历不存在指定元素则回溯
        return false;
    }
}

Discuss：//简单清晰，可读性强
public boolean exist(char[][] board, String word) {
    char[] w = word.toCharArray();
    for (int y=0; y<board.length; y++) {
    	for (int x=0; x<board[y].length; x++) {
    		if (exist(board, y, x, w, 0)) return true;//对每个元素判断，其实也不用这么麻烦，但是清晰代码少
    	}
    }
    return false;
}

private boolean exist(char[][] board, int y, int x, char[] word, int i) {
	if (i == word.length) return true;
	if (y<0 || x<0 || y == board.length || x == board[y].length) return false;//对传递下来的i，j判断是否越界，减少判断条件
	if (board[y][x] != word[i]) return false;//不相等或者已经访问过，则fasle
	board[y][x] ^= 256;//亦或，第9位取反，其他位不变
	boolean exist = exist(board, y, x+1, word, i+1)
		|| exist(board, y, x-1, word, i+1)
		|| exist(board, y+1, x, word, i+1)
		|| exist(board, y-1, x, word, i+1);
	board[y][x] ^= 256;//回溯
	return exist;
}
