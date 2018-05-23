/**
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/

My code://对前两个数进行穷举，对后两个数进行双边靠拢
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums==null||nums.length==0) return result;
        Arrays.sort(nums);
        cal(result,new ArrayList<>(),nums,0,target,4);
        return result;
    }
    public void cal(List<List<Integer>> res,List<Integer> list,int[] nums,int num, int target,int find){
        if(target<0&&nums[num]>0)
            return ;
        if(find==2){
            int start=num;
            int end=nums.length-1;
            while(start<end){
                int tar=nums[start]+nums[end];
                if(tar==target){
                    list.add(nums[start]);
                    list.add(nums[end]);
                    if(!res.contains(list))
                       res.add(new ArrayList<>(list));
                    list.remove(list.size()-1);
                    list.remove(list.size()-1);
                    start++;
                    end--;
                }
                else if(tar<target)
                    start++;
                else if(tar>target)
                    end--;
            }
            return ;
        }
        int len=nums.length-find;
        for(int i=num;i<=len;i++){
            list.add(nums[i]);
            cal(res,list,nums,i+1,target-nums[i],find-1);
            list.remove(list.size()-1);
        } 
    }
}

Discuss：//虽然知道降解的思路，但是还是不能非常清楚
（1）//先转化为3阶，再转化为2阶求解
public class Solution {
public List<List<Integer>> fourSum(int[] num, int target) {
    ArrayList<List<Integer>> ans = new ArrayList<>();
    if(num.length<4)return ans;
    Arrays.sort(num);
    for(int i=0; i<num.length-3; i++){
        if(num[i]+num[i+1]+num[i+2]+num[i+3]>target)break; //first candidate too large, search finished
        if(num[i]+num[num.length-1]+num[num.length-2]+num[num.length-3]<target)continue; //first candidate too small
        if(i>0&&num[i]==num[i-1])continue; //prevents duplicate result in ans list
        for(int j=i+1; j<num.length-2; j++){
            if(num[i]+num[j]+num[j+1]+num[j+2]>target)break; //second candidate too large
            if(num[i]+num[j]+num[num.length-1]+num[num.length-2]<target)continue; //second candidate too small
            if(j>i+1&&num[j]==num[j-1])continue; //prevents duplicate results in ans list
            int low=j+1, high=num.length-1;
            while(low<high){
                int sum=num[i]+num[j]+num[low]+num[high];
                if(sum==target){
                    ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
                    while(low<high&&num[low]==num[low+1])low++; //skipping over duplicate on low
                    while(low<high&&num[high]==num[high-1])high--; //skipping over duplicate on high
                    low++; 
                    high--;
                }
                //move window
                else if(sum<target)low++; 
                else high--;
            }
        }
    }
    return ans;
}
（2）//k sum
List<List<Integer>> kSum_Trim(int[] a, int target, int k) {
    List<List<Integer>> result = new ArrayList<>();
    if (a == null || a.length < k || k < 2) return result;
    Arrays.sort(a);
    kSum_Trim(a, target, k, 0, result, new ArrayList<>());
    return result;
}

void kSum_Trim(int[] a, int target, int k, int start, List<List<Integer>> result, List<Integer> path) {
    int max = a[a.length - 1];
    if (a[start] * k > target || max * k < target) return;
    
    if (k == 2) {                        // 2 Sum
        int left = start;
        int right = a.length - 1;
        while (left < right) {
            if      (a[left] + a[right] < target) left++;
            else if (a[left] + a[right] > target) right--;
            else {
                result.add(new ArrayList<>(path));
                result.get(result.size() - 1).addAll(Arrays.asList(a[left], a[right]));
                left++; right--;
                while (left < right && a[left] == a[left - 1]) left++;
                while (left < right && a[right] == a[right + 1]) right--;
            }
        }
    }
    else {                        // k Sum
        for (int i = start; i < a.length - k + 1; i++) {
            if (i > start && a[i] == a[i - 1]) continue;
            if (a[i] + max * (k - 1) < target) continue;
            if (a[i] * k > target) break;
            if (a[i] * k == target) {
                if (a[i + k - 1] == a[i]) {
                    result.add(new ArrayList<>(path));
                    List<Integer> temp = new ArrayList<>();
                    for (int x = 0; x < k; x++) temp.add(a[i]);
                    result.get(result.size() - 1).addAll(temp);    // Add result immediately.
                }
                break;
            }
            path.add(a[i]);
            kSum_Trim(a, target - a[i], k - 1, i + 1, result, path);
            path.remove(path.size() - 1);        // Backtracking
        }
    }
}
