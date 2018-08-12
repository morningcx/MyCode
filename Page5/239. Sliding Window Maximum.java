//My code:2ms 99% 最坏情况时间复杂度会提高至和每次遍历一样
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        boolean getMax = true;//是否需要重新遍历，选出窗口中的最大值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < result.length; ++i) {
            max = Math.max(max, nums[k + i - 1]);//如果新入窗口的数值比原有的max大，则无需进行后续操作
            if (i != 0 && nums[i - 1] == max) {//如果最大值离开窗口，则需要将getMax置为true，重新遍历
                max = Integer.MIN_VALUE;
                getMax = true;
            }
            for (int j = i; getMax && j < k + i; ++j) {
                max = Math.max(max, nums[j]);
            }
            getMax = false;
            result[i] = max;
        }
        return result;
    }
}

//Discuss:双端队列，保持窗口中最大值位于头部，时间复杂度稳定
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return nums;
        }
        int[] result = new int[n - k + 1];
        LinkedList<Integer> dq = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            //如果头节点(这里相当于最大值)索引超过窗口范围，删除最大值
            if (!dq.isEmpty() && dq.peek() < i - k + 1) { 
                dq.poll();
            }
            //当前节点值和尾节点相比较，将比它小的索引都删除，如果当前节点是最大值，将删除所有队列中的值
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offer(i);//将当前节点放入队列，不论何时队列都是有序的
            if (i - k + 1 >= 0) {
                result[i - k + 1] = nums[dq.peek()];//每次出队的都为窗口中的最大值
            }
        }
        return result;
    }
}
