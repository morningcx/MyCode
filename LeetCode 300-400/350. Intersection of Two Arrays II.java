// my code
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map2 = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        initMap(nums2, map2);
        for (int i : nums1) {
            Integer count = map2.get(i);
            if (count != null && count > 0) {
                list.add(i);
                map2.put(i, count - 1);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            result[i] = list.get(i);
        }
        return result;
    }
    
    private void initMap(int[] nums, Map<Integer, Integer> map) {
        for (int num : nums) {
            Integer count = map.get(num);
            map.put(num, count == null ? 1 : count + 1);
        }
    }
}

// 双排序算法
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<Integer>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for(int i = 0, j = 0; i< nums1.length && j<nums2.length;){
                if(nums1[i]<nums2[j]){
                    i++;
                }
                else if(nums1[i] == nums2[j]){
                    res.add(nums1[i]);
                    i++;
                    j++;
                }
                else{
                    j++;
                }
        }
        int[] result = new int[res.size()];
        for(int i = 0; i<res.size();i++){
            result[i] = res.get(i);
        }
        return result;
    }
}
