/**
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
*/

My code://28ms 53%
//拓扑排序，每次删除度为0的节点，并且减少被删除节点指向节点的度，重复操作直至没有度为0的节点
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int count = 0;
        int[] list = new int[numCourses];
        for (int[] n : prerequisites) {
            list[n[0]]++;//计算每个节点的度
        }
        while (true) {
            Integer pos = findZero(list);//寻找度为0的节点，遍历耗时较多
            if (pos == null) break;//没有就跳出循环
            updateList(prerequisites, list, pos);//更新被删除节点指向节点的度
            ++count;
        }
        return count == numCourses;//删除节点的数量等于numCourses则说明该图为有向无环图
    }
    
    private Integer findZero(int[] list) {
        int num = 0;
        for (int i : list) {
           if (i == 0) {
               list[num] = -1;//标记节点
               return num;
           }
           ++num;
        }
        return null;
    }
    
    private void updateList(int[][] prerequisites, int[] list, int num) {
        for (int[] n : prerequisites) {
            if (n[1] == num) {
                list[n[0]]--;
            }
        }
    }
}

Discuss：
（1）//BFS 
//思想一样，用队列和顺序表来遍历度为0的节点和节点所指向的节点，更加高效，速度快
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];//没有指定泛型的类型
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList();
        int count=0;
        
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();//创建每个节点所连接到的节点顺序表
            
        //这里1和0作者弄反了，其实不影响结果，因为反向图仍然存在环
        for(int i=0; i<prerequisites.length;i++){
            degree[prerequisites[i][1]]++;//计算每个节点的度
            graph[prerequisites[i][0]].add(prerequisites[i][1]);//向相应顺序表内添加指向节点
        }
        for(int i=0; i<degree.length;i++){
            if(degree[i] == 0){
                queue.add(i);//若存在度为0的节点，添加至队列
                count++;//增加一门可以学习完的课程
            }
        }
        
        while(queue.size() != 0){
            int course = (int)queue.poll();//创建顺序表数组的时候没有指定类型，这里是object转int
            for(int i=0; i<graph[course].size();i++){//遍历要删除节点所连接的节点，并将连接的那些节点的度-1
                int pointer = (int)graph[course].get(i);
                degree[pointer]--;//将它的度减1
                if(degree[pointer] == 0){//减1之后度为0的话，则继续添加至队列
                    queue.add(pointer);
                    count++;//增加一门可以学习完的课程
                }
            }
        }
        //这里直接 return count == numCourses;
        if(count == numCourses)
            return true;
        else    
            return false;
    }
}
（2）//DFS
class Solution {
    public boolean canFinish(int num, int[][] prereq) {
        ArrayList[] adj = new ArrayList[num];
        
        for(int i =0; i<num; i++)
        {
            adj[i] = new ArrayList<Integer>();//创建每个节点所连接到的节点顺序表
        }
        
        for(int i =0; i<prereq.length; i++)
        {
            adj[prereq[i][0]].add(prereq[i][1]);//向相应顺序表内添加指向节点
        }
        
        int[] visited = new int [num];//标记dfs所遍历过的元素

        for(int i =0; i<num; i++)//遍历每个节点
        {
            if(!isCycle(adj,visited,i))//深度优先遍历，查看是否其中存在环
                return false;
        }
        return true;
    }
    
    public boolean isCycle(ArrayList[] adj, int[] visited, int node) {
        if(visited[node] == 2)//2：表示节点在先前的遍历中已经完成，并且该节点上的dfs不存在环，所以不用重复进行dfs
            return true;
        if(visited[node] == 1)//1：表示节点已经被遍历，遇到这样的节点说明图中存在环
            return false;
        visited[node] = 1;//先标记当前节点已经遍历过
        for(int i =0; i<adj[node].size(); i++)//利用顺序表遍历当前节点所指向的节点
        {
            if(!isCycle(adj,visited,(int)adj[node].get(i)))//遍历所指向节点的顺序表
                return false;
        }
         visited[node] = 2;//标记节点完成dfs，说明此次dfs不存在环，标记节点不必进行再次遍历
        return true;
    }
}
