/**
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
*/

My code://7ms 82%
class Solution {
    public int numIslands(char[][] grid) {
        int row = grid.length;
        if(row == 0) return 0;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];//标识已经访问过的节点
        int count = 0;
        for (int i = 0; i < row; i++) 
            for (int j = 0; j < col; j++) 
                if (grid[i][j] == '1' && !visited[i][j]) {//值为‘1’，并且没有被访问过
                    connect(visited, grid, i, j);
                    ++count;
                }
        return count;
    }
    //把所有连接的节点标识为访问true
    public void connect(boolean[][] visited, char[][] grid, int i, int j) {
        if (grid[i][j] == '0') return ;
        visited[i][j] = true;
        if(i < visited.length - 1&& !visited[i + 1][j]) connect(visited, grid, i + 1, j);
        if(i > 0 && !visited[i - 1][j]) connect(visited, grid, i - 1, j);
        if(j < visited[0].length - 1&& !visited[i][j + 1]) connect(visited, grid, i, j + 1);
        if(j > 0 && !visited[i][j - 1]) connect(visited, grid, i, j - 1);
    }
}

Discuss:
（1）//思路一样DFS，这种算法把访问过的1节点直接替换成0，之后的判断就少了很多
public class Solution {
    public int numIslands(char[][] grid) {
        int islands = 0;
        for (int i=0; i<grid.length; i++)
            for (int j=0; j<grid[i].length; j++)
                islands += sink(grid, i, j);
        return islands;
    }
    int sink(char[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[i].length || grid[i][j] == '0')
            return 0;
        grid[i][j] = '0';
        for (int k=0; k<4; k++)
            sink(grid, i+d[k], j+d[k+1]);//....学到了
        return 1;
    }
    int[] d = {0, 1, 0, -1, 0};//(0,1),(1,2),(2,3),(3,4)
}

（2）//并查集算法
public class Solution {
public int numIslands(char[][] grid) {
if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
int row = grid.length;
int col = grid[0].length;

    UnionFind island = new UnionFind(row, col, grid);
    
    for(int i = 0; i < row; i++){
        for(int j = 0; j < col; j++){
            if(grid[i][j] == '1'){//遇到为‘1’的节点
                int p = i * col + j;
                //只要合并右节点和下节点，因为之后的遍历左节点和上节点都已经合并过了
                //right
                if(j < col - 1 && grid[i][j + 1] == '1'){//右节点存在，并且为1
                    int q = i * col + j + 1;//定位到集合表的位置
                    if(!island.find(p, q)){//如果两个节点的root节点不同
                        island.union(p, q);//把这两个节点联合，即合并至同一个root的集合中
                    }
                }
                //down
                if(i < row - 1 && grid[i + 1][j] == '1'){//下节点存在，并且为1
                    int q = (i + 1) * col + j;
                    if(!island.find(p, q)){
                        island.union(p, q);
                    }
                }
            }
        }
    }
    return island.size();
}
}
//并查集结构
class UnionFind{
private int[] id, size;
private int count;

public UnionFind(int row, int col, char[][] grid){
    id = new int[row * col];//相互连接的节点
    size = new int[row * col];//各个节点的容量
    
    for(int i = 0; i < row; i++){
        for(int j = 0; j < col; j++){
            if(grid[i][j] == '1') this.count++;//‘1’的数量
        }
    }
    
    for(int i = 0; i < row * col; i++){//创建一维的集合表
        id[i] = i;//每个节点指向自己
        size[i] = 1;//每个节点容量都为1
    }
}

public int size(){return this.count;}

private int root(int i){//查找每个节点的root节点
    while(i != id[i]){//root点都是自己指向自己
        id[i] = id[id[i]];
        i = id[i];
    }
    return i;
}

public boolean find(int p, int q){//判断两个节点是否属于同一集合
    return root(p) == root(q);//只要判断是否属于同一root
}

public void union(int p, int q){//将两个节点联合
    int i = root(p);//获取两个节点的root节点
    int j = root(q);
    //标准一点这里需要判断i和j是否相等，相等则return，但是主函数里已经判断过了
    if(size[i] < size[j]){//i节点的容量小，则把i集合合并到j集合
        id[i] = j;//把i节点指向自己的数值改成j节点的数值(变成一颗树了)
        size[j] += size[i];//j节点的容量需要重置
    }
    else{
        id[j] = i;//反之同理
        //这里原来是减号，查找资料看了一下，是作者写错了，不过是减号也不影响，只是之后的合并都将被动的添加到另一个root节点上
        //不影响两个节点对root节点的判断
        //但是其实这里如果用减号，只不过是把容量大的节点当做子树来处理(这道题目直接变成链表了)，在寻找root节点的时候会消耗更多的时间
        size[i] += size[j];
    }
    count --;
}
}

