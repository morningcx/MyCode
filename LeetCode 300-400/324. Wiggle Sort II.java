// my code 4ms 96%
class Solution {
    public void wiggleSort(int[] nums) {
        int[] cNums = nums.clone();
        Arrays.sort(cNums);
        int index = 0;
        index = nums.length % 2 == 0 ? nums.length / 2 - 1 : nums.length / 2;
        for (int i = 0; i < nums.length; i += 2) {
            nums[i] = cNums[index--];
        }
        index = nums.length - 1;
        for (int i = 1; i < nums.length; i += 2) {
            nums[i] = cNums[index--];
        }
    }
}

// discuss比较好理解的版本，virtual index看不懂
public void wiggleSort(int[] nums) {
    int median=findKthLargest(nums,(nums.length+1)/2); // 找到中位数
    int odd=1;
    // 因为可能出现多个mid，所以需要分开，odd和even就要特殊处理
    int even=nums.length%2==0?nums.length-2:nums.length-1;
    int[] tmpArr=new int[nums.length];
    // 比中位数大的放在偶数位（index为奇数）， 小的放在基数位（index为偶数），相等的不作处理
    for(int i=0;i<nums.length;i++){
        if(nums[i]>median){
            tmpArr[odd]=nums[i];
            odd+=2;
            continue;
        }
        if(nums[i]<median){
            tmpArr[even]=nums[i];
            even-=2;
            continue;
        }
    }
    // 填充等于mid的index
    while(odd<nums.length){
        tmpArr[odd]=median;
        odd+=2;
    }
    while(even>=0){
        tmpArr[even]=median;
        even-=2;
    }
    for(int i=0;i<nums.length;i++){
        nums[i]=tmpArr[i];
    }
}
