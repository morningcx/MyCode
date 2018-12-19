//my code 400ms
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int max = 0;
        int dp[] = new int[envelopes.length];
        // 不想写自己写排序，还是偷偷看了一下discuss，没想到Comparator还可以这样用
        Arrays.sort(envelopes, new Comparator<int[]>(){ 
        public int compare(int[] arr1, int[] arr2){
            if(arr1[0] == arr2[0])
                return arr2[1] - arr1[1]; // 一开始我还是不知道这里height是降序排列
            else
                return arr1[0] - arr2[0];
       }
    });
    // 动态规划思想，还是去看了一下300. Longest Increasing Subsequence的解题思路
        Arrays.fill(dp, 1);
        for (int i = 0; i < envelopes.length; ++i) {
            int width = envelopes[i][0];
            int height = envelopes[i][1];
            for (int j = i + 1; j < envelopes.length; ++j) {
                if (envelopes[j][0] > width && envelopes[j][1] > height) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

// discuss 18ms
// 一开始就有种感觉往Longest Increasing Subsequence去想，也想着用O(nlogn)的时间复杂度去想
// 后来发现几个问题：
// 1、排序写起来很麻烦（Comparator也想到了，但是没想到可以这么用）
// 2、宽度高度按升序排列（一般人都这么想），在宽度相同的时候高度判断还得遍历一遍宽度相同的信封，也挺麻烦的
// discuss的解决方法：宽度升序，高度降序，LIS算法不影响结果（等宽度信封最多只插入一次，并且高度信息存储在数组中）。两个问题直接全部解决。
public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes.length < 2) return envelopes.length;
        
        Arrays.sort(envelopes, new EnvelopeComparator()); // 高度降序
        int[] dp = new int[envelopes.length];
        int size = 0;
        
        for(int[] envelope: envelopes) {
            // binary search 
            // 二分法查找[left, right)中第一个大于等于envelope[1]的索引位置
            // 之所以right为开，相当于right = realRight + 1 
            // 相当于middle = left + (realRight - left + 1) / 2;
            int left = 0, right = size, middle = 0;     // right = size
            while(left < right) {
                middle = left + (right - left) / 2;
                if(dp[middle] < envelope[1]
                ) left = middle + 1;
                else right = middle;
            }
            
            // left is the right position to 'replace' in dp array
            dp[left] = envelope[1];
            if(left == size) size++;
        }
        return size;
    }
    
    class EnvelopeComparator implements Comparator<int[]> {
        public int compare(int[] e1, int[] e2) {
            return e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0];
        }
    }
}
