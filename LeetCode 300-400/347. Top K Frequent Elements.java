// 用一个string数组存储出现相应次数的数字，用逗号隔开
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        String[] count = new String[nums.length + 1];
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(); // map记录各个数出现的次数
        for (int num : nums) {
            Integer item = map.get(num);
            map.put(num, item == null ? 1 : item + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) { // count[n]记录出现n次数的数字，如果有多个则用逗号隔开
            if (count[entry.getValue()] == null) {
                count[entry.getValue()] = "" + entry.getKey();
            } else {
                count[entry.getValue()] += "," + entry.getKey();
            }
        }
        for (int i = nums.length; i >= 0; --i) { // 倒序添加出现次数前k的数字
            if (count[i] != null) {
                for (String s : count[i].split(",")) {
                    result.add(Integer.valueOf(s));
                    if (--k == 0) return result;
                }
            }
        }
        return result;
    }
}

// 思路一样，用了list的数组来记录出现次数相同的数字，速度较快
public List<Integer> topKFrequent(int[] nums, int k) {

	List<Integer>[] bucket = new List[nums.length + 1];
	Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

	for (int n : nums) {
		frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
	}

	for (int key : frequencyMap.keySet()) {
		int frequency = frequencyMap.get(key);
		if (bucket[frequency] == null) {
			bucket[frequency] = new ArrayList<>();
		}
		bucket[frequency].add(key);
	}

	List<Integer> res = new ArrayList<>();

	for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
		if (bucket[pos] != null) {
			res.addAll(bucket[pos]);
		}
	}
	return res.subList(0,k); // 这里原来是return res，当出现次数相同的数字有多个就会出现错误(oj测试数据不够)
}

// 使用优先队列自动排序，重写Comparator
public List<Integer> topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> counterMap = new HashMap<>();
    for(int num : nums) {
        int count = counterMap.getOrDefault(num, 0);
        counterMap.put(num, count+1);
    }
    
    PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue()-b.getValue());
    for(Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
        pq.offer(entry);
        if(pq.size() > k) pq.poll();
    }
    
    List<Integer> res = new LinkedList<>();
    while(!pq.isEmpty()) {
        res.add(0, pq.poll().getKey());
    }
    return res;
}
