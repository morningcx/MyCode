// discuss 1  
public class Solution {
    private int[] nums;
    private Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(nums == null) return null;
        int[] a = nums.clone();
        for(int j = 1; j < a.length; j++) { // j == 0 时必定交换的自己，所以可以省去
            // nextInt(j + 1) 返回[0, j]的数值
            // 获取包括当前位置索引值及其之前的随机索引（包括当前索引）
            int i = random.nextInt(j + 1); // 如果不包括自己当前索引，则洗牌没有可能会恢复和原来一样的顺序
            // 至于为什么不是random.nextInt(num.length)，discuss里也没有给出结果
            swap(a, i, j);
        }
        return a;
    }
    
    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}


// discuss 2 省去交换算法
public class Solution {

    private int[] nums;
    
    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] rand = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            int r = (int) (Math.random() * (i+1));
            // 因为存在一个克隆的数组，所以也不需要交换算法，可以直接赋值
            rand[i] = rand[r]; // 将随机值赋给当前值
            rand[r] = nums[i]; // 把随机值覆盖为当前值（克隆数组中的值没变过，所以可以直接赋值）
        }
        return rand;
    }
}
