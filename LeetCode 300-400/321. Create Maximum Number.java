// my code 124ms 1% 
// 如果将List全改为int[]，优先队列改为判断最大值，并且对getLimitMaxNum进行优化，执行时长将缩短为16ms
// 先穷举：组成长度为k的最大数字，第一个数组需要n个数字，则第二个数组需要k - n个数字，不合法的越过
// 再筛选：每个数组固定长度可以组成的最大数字， getLimitMaxNum
// 接着合并：第一个数组和第二个数组中两个最大数字进行合并， createMaxNum
// 选出最大数字：穷举循环下，对合并完的每一个数字进行比较，最大的即为答案
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] num = new int[k];
        int len = Math.min(nums1.length, k);
        // 其实不需要优先队列，使用biggerThan判断出最大的那个数组既可，这里速度慢了好多
        PriorityQueue<int[]> numList = new PriorityQueue<>((o1, o2) -> { 
            for (int i = 0; i < o1.length; ++i) {
                if (o1[i] < o2[i]) return 1;
                if (o1[i] > o2[i]) return -1;
            }
            return 0;
        });
        for (int i = 0; i <= len; ++i) {
            int remind = k - i;
            if (remind < 0 || remind > nums2.length) continue;
            List<Integer> num1 = getLimitMaxNum(nums1, i);
            List<Integer> num2 = getLimitMaxNum(nums2, remind);
            numList.offer(createMaxNum(num1, num2, k));
        }
        return numList.poll();
    }
    
    private int[] createMaxNum(List<Integer> num1, List<Integer> num2, int len) {
        int[] maxNum = new int[len];
        int index = 0, b = 0, s = 0;
        while (b != num1.size() && s != num2.size()) {
            if (biggerThan(num1, b, num2, s)) {
                maxNum[index++] = num1.get(b++);
            } else {
                maxNum[index++] = num2.get(s++);
            }
        }
        while (b != num1.size()) maxNum[index++] = num1.get(b++);
        while (s != num2.size()) maxNum[index++] = num2.get(s++);
        return maxNum;
    }
    
    private boolean biggerThan(List<Integer> num1, int start1, List<Integer> num2, int start2) {
        while (start1 < num1.size() && start2 < num2.size()) {
            if (num1.get(start1) > num2.get(start2)) return true;
            if (num1.get(start1) < num2.get(start2)) return false;
            ++start1;
            ++start2;
        }
        return start1 != num1.size();
    }
    // 这里如果用一个数值作最大节点位置的标记，而不是用visit来标记，速度将会快60ms
    private List<Integer> getLimitMaxNum(int[] nums, int n) {
        boolean[] visit = new boolean[nums.length];
        List<Integer> num = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int index = 0, max = Integer.MIN_VALUE;
            for (int j = i; j <= i + nums.length - n; ++j) {
                if (!visit[j] && nums[j] > max) {
                    max = nums[j];
                    index = j;
                }
            }
            while (index != i - 1) visit[index--] = true;
            num.add(max);
        }
        return num;
    }
}


// discuss 思路一样
public int[] maxNumber(int[] nums1, int[] nums2, int k) {
    int n = nums1.length;
    int m = nums2.length;
    int[] ans = new int[k];
    for (int i = Math.max(0, k - m); i <= k && i <= n; ++i) {
        int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
        if (greater(candidate, 0, ans, 0)) ans = candidate; // 判断大小
    }
    return ans;
}
private int[] merge(int[] nums1, int[] nums2, int k) {
    int[] ans = new int[k];
    for (int i = 0, j = 0, r = 0; r < k; ++r)
        ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++]; // greater来做判断，不怕越界
    return ans;
}
public boolean greater(int[] nums1, int i, int[] nums2, int j) {
    while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
        i++;
        j++;
    }
    return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
}
public int[] maxArray(int[] nums, int k) {
    int n = nums.length;
    int[] ans = new int[k];
    for (int i = 0, j = 0; i < n; ++i) {
        while (n - i + j > k && j > 0 && ans[j - 1] < nums[i]) j--;
        if (j < k) ans[j++] = nums[i];
    }
    return ans;
}

// 取数组固定长度下最大数字，另一种可读性较好的写法
private int[] getMax(int[] arr, int k) {
	int n = arr.length;
	int[] res = new int[k];  	

	int last = -1; //position of previous digit
	for(int i = 0; i < k ; i++) {// k positions to fetch
		for(int j = last + 1; j + (k - i - 1) < n; j++) {
			if(res[i] < arr[j]) {
				res[i] = arr[j];
				last = j;
			}
		}
	}
	
	return res;
}
