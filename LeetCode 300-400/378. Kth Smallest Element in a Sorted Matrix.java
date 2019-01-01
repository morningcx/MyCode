// my code 40ms 左右
// 当把优先队列的Comparator换成lambda表达式，耗时就会到100ms多
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        if (k == 1) return matrix[0][0];
        if (k == n * n) return matrix[n - 1][n - 1];
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return matrix[o1[0]][o1[1]] - matrix[o2[0]][o2[1]];
            }
        });
        boolean[][] visit = new boolean[n][n];
        queue.offer(new int[]{0, 0});
        int index = 0;
        while (!queue.isEmpty()) {
            int[] item = queue.poll();
            if (++index == k) return matrix[item[0]][item[1]];
            if (item[0] + 1 < n && !visit[item[0] + 1][item[1]]) {
                visit[item[0] + 1][item[1]] = true;
                queue.offer(new int[]{item[0] + 1, item[1]});
            }
            if (item[1] + 1 < n && !visit[item[0]][item[1] + 1]) {
                visit[item[0]][item[1] + 1] = true;
                queue.offer(new int[]{item[0], item[1] + 1});
            }
        }
        return 1;
    }
}

// discuss 1
// 算法思路为：在最小值和最大值之间进行中间值的猜测判断，利用二分法规定某一个数mid
// 再在矩阵中统计出所有小于等于mid数值节点个数count
// 如果count小于k，则说明第k个数比mid大，则lo = mid + 1
// 如果count大于等于k，则说明第k个数小于等于mid，因为存在重复的情况，则hi = mid，不需要减1
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  j = matrix[0].length - 1;
            for(int i = 0; i < matrix.length; i++) {
                while(j >= 0 && matrix[i][j] > mid) j--; // 从右上角开始统计个数
                count += (j + 1);
            }
            if(count < k) lo = mid + 1;
            else hi = mid; // 保留最高点，lo不管什么情况都会不停往上加
        }
        return lo;
    }
}

// discuss 2 思路和1一直，代码分层次，更加简洁可读
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = getLessEqual(matrix, mid);
            if (count < k) lo = mid + 1;
            else hi = mid - 1; 
            // 这里可以减1是因为while判断条件为lo <= hi，即使最后数值相等，则还会把lo进行加1操作，加1之后的数就是count >= k时hi的值
        }
        return lo;
    }
    
    private int getLessEqual(int[][] matrix, int val) {
        int res = 0;
        int n = matrix.length, i = n - 1, j = 0; // 从左下角开始统计每行个数
        while (i >= 0 && j < n) {
            if (matrix[i][j] > val) i--;
            else {
                res += i + 1;
                j++;
            }
        }
        return res;
    }
}
