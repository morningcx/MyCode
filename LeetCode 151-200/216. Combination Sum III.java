/**
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
*/

My code://一般类似要求为元素组成相同但顺序不同归为一个结果时，需要按序来dfs，所以需要传入一个start
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        calculate(result, new ArrayList<>(), n, k, 1);
        return result;
    }
    
    private void calculate(List<List<Integer>> result, List<Integer> list, int sum, int num, int start) {
        if (num == 0) {
            if (sum == 0) 
                result.add(new ArrayList<>(list));
            return ;
        }
        //n / k为后续需要求和各值的平均数，当前值不必大于平均数
        //因为如果大于平均数，则后续所有遍历的元素都会大于其平均数，而使得最后结果一定大于目标
        for (int i = start; sum >= i && i < 10; ++i) {//sum >= i改为i <= n / k更好
            list.add(i);
            calculate(result, list, sum - i, num - 1, i + 1);
            list.remove(list.size() - 1);
        }
    }
    
}
