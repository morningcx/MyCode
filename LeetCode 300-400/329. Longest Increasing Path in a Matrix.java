// my code 33ms 13%
// 将矩阵中出现过的数字和它出现的位置用map来存储，然后对出现的数字进行排序操作（从小到大）
// 从最小的数字位置开始，利用dp数组判断上下左右的最大dp值，不停循环至最大的那个数
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        Map<Integer, List<int[]>> map = new HashMap<>();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                List<int[]> list = map.get(matrix[i][j]);
                if (list == null) {
                    nums.add(matrix[i][j]);
                    list = new ArrayList<>();
                    map.put(matrix[i][j], list);
                }
                list.add(new int[]{i, j});
            }
        }
        Collections.sort(nums);
        int max = 0;
        for (int num : nums) {
            List<int[]> posList = map.get(num);
            for (int[] pos : posList) {
                max = Math.max((dp[pos[0]][pos[1]] = (maxAround(dp, pos[0], pos[1], matrix) + 1)), max);
            }
        }
        return max;
    }
    
    
    private int maxAround(int[][] dp, int i, int j, int[][] matrix) {
        int max = 0;
        int curr = matrix[i][j];
        if (i - 1 >= 0 && matrix[i - 1][j] != curr) max = Math.max(max, dp[i - 1][j]);
        if (i + 1 < dp.length && matrix[i + 1][j] != curr) max = Math.max(max, dp[i + 1][j]);
        if (j - 1 >= 0 && matrix[i][j - 1] != curr) max = Math.max(max, dp[i][j - 1]);
        if (j + 1 < dp[0].length && matrix[i][j + 1] != curr) max = Math.max(max, dp[i][j + 1]);
        return max;
    }
}

// discuss
// dfs，矩阵中每个点作为起点进行深度遍历，遍历的过程中用cache缓存数组，
// 记录经过的节点所拥有最大的深度，下次再次遍历到此节点时就可以直接返回cache
public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

public int longestIncreasingPath(int[][] matrix) {
    if(matrix.length == 0) return 0;
    int m = matrix.length, n = matrix[0].length;
    int[][] cache = new int[m][n];
    int max = 1;
    for(int i = 0; i < m; i++) {
        for(int j = 0; j < n; j++) {
            int len = dfs(matrix, i, j, m, n, cache);
            max = Math.max(max, len);
        }
    }   
    return max;
}

public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
    if(cache[i][j] != 0) return cache[i][j];
    int max = 1;
    for(int[] dir: dirs) {
        int x = i + dir[0], y = j + dir[1];
        if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
        int len = 1 + dfs(matrix, x, y, m, n, cache);
        max = Math.max(max, len);
    }
    cache[i][j] = max;
    return max;
}
