/**
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
*/

My code://13ms 86%
//和拓扑排序1一样，只是需要多加一个result数组
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int count = 0;
        int[] result = new int[numCourses];
        int[] degree = new int[numCourses];
        ArrayList<Integer>[] list = new ArrayList[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < numCourses; ++i) 
            list[i] = new ArrayList<>();
        
        for (int[] node : prerequisites) {
            degree[node[0]]++;
            list[node[1]].add(node[0]);
        }
            
        for (int i = 0; i < numCourses; ++i) {
            if (degree[i] == 0)
                queue.offer(i);
        }
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result[count++] = cur;
            for (int next : list[cur]) {
                degree[next]--;
                if (degree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        if (count == numCourses)
            return result;
        return new int[0];
    }
}

Discuss://DFS + Data Struct
class Solution {
    private int N = 0;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            courses[prerequisites[i][0]].add(courses[prerequisites[i][1]]);
        }
        for (int i = 0; i < numCourses; i++) {//各个遍历
            if (isCyclic(courses[i], result)) {//其中有一个存在环则返回空数组
                return new int[0];
            }
        }
        return result;
    }
    
    private boolean isCyclic(Course cur, int[] result) {
        if (cur.tested == true) return false;//测试完毕，返回fasle表示无环
        if (cur.visited == true) return true;//已被访问，返回true表示存在环
        //以上都不是的节点则为没有被访问过的
        cur.visited = true;//标记为已被访问
        for (Course c : cur.pre) {//对连接节点遍历
            if (isCyclic(c, result)) {
                return true;
            }
        }
        cur.tested = true;//测试完毕
        result[N++] = cur.number;//添加结果
        return false;
    }
    
    class Course {
        boolean visited = false;//访问标识
        boolean tested = false;//测试标识
        int number;//节点的数值
        List<Course> pre = new ArrayList<Course>();//节点的连接节点数组
        public Course(int i) {
            number = i;
        }
        public void add(Course c) {
            pre.add(c);
        }
    }
    }
