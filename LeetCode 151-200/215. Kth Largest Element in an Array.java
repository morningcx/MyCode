/**
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
*/

My code:
（1）//54ms  29%
//一开始想到的就是快排排序一遍，再选择第k个数，效率较慢，没有必要对全部进行排序
class Solution {
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }
    
    private void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int mid = separate(nums, start, end);
            quickSort(nums, start, mid - 1);
            quickSort(nums, mid + 1, end);
        }
    } 
    
    private int  separate(int[] nums, int start, int end) {
        int key = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= key) end--;
            nums[start] = nums[end];
            while (start < end && nums[start] <= key) start++;
            nums[end] = nums[start];
        }
        nums[start] = key;
        return start;
    }
}

（2）
//QuickSelect算法，将氛围内的数组分成两个部分，分治法将数组分至k所在的范围内，直到等于k
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = separate(nums, start, end);
            if (mid < k - 1)
                start = mid + 1;
            else if (mid > k - 1) 
                end = mid - 1;
            else
                break;
        }
        return nums[k - 1];
    }
    
    private int  separate(int[] nums, int start, int end) {
        //这里加上start，end，mid三值取中的操作可以将时间缩到5ms 99%
        //不加则需要50ms
        int key = nums[start];
        while (start < end) {
            while (start < end && nums[end] <= key) end--;
            nums[start] = nums[end];
            while (start < end && nums[start] >= key) start++;
            nums[end] = nums[start];
        }
        nums[start] = key;
        return start;
    }
}

Discuss：//方法很多，大致都是分治选择
（1）//partion版本1
    int partition(vector<int>& nums, int left, int right) {
        int pivot = nums[left];
        int l = left + 1, r = right;//从left+1开始分隔，其实不必+1，循环判断如果=则会自动加1，类似版本2
        while (l <= r) {
            if (nums[l] < pivot && nums[r] > pivot)
                swap(nums[l++], nums[r--]);
            if (nums[l] >= pivot) l++; 
            if (nums[r] <= pivot) r--;
        }
        swap(nums[left], nums[r]);//因为最后一个r大于pivot，left又是在左边的，大于pivot，所以可以交换
        return r;
    }
（2）//partion版本2
        private int partion(int[] nums, int start, int end) {
        int pivot = start, temp;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot]) start++;//找到大于pivot的节点，相等自动加1
            while (start <= end && nums[end] > nums[pivot]) end--;//找到小于pivot的节点
            if (start > end) break;
            temp = nums[start];//交换
            nums[start] = nums[end];
            nums[end] = temp;
        }
        temp = nums[end];
        nums[end] = nums[pivot];
        nums[pivot] = temp;
        return end;
    }
}
（3）//partion版本3
//把最右的值作为pivot，判断要比前几个版本少
    private int partition(int[] nums, int left, int right, int pivotIndex)
    {
        //以最右指针作为pivot
        int pivotValue = nums[pivotIndex];//这里传的中值
        swap(nums, pivotIndex, right);//把中值和最右值交换
        int storeIndex = left;//移动指针
        
        for (int i = left; i < right; i++)//遍历，除了最后一个right节点
        {
            //如果大于pivot的值，则交换值，移动指针，如果小于等于则跳过，指针不用移动
            //等到遍历下一个大于pivot的值则与移动指针交换
            //也就是把大值换到前面，把小值换到后面，最后循环结束指针指向第一个小值，之前都是大值
            //然后和pivot的值进行交换，在其之前都是大值，在其之后都是小值
            if (nums[i] > pivotValue)
            {
                swap(nums, i, storeIndex);//则和指针所指的值交换
                storeIndex++;//移动指针
            }
        }

        swap(nums, right, storeIndex);//交换
        
        return storeIndex;
    }
    
