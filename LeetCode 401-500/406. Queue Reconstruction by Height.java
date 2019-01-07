// my code 29ms 46%
// 思路：对每个人(h, k)，进行h优先k其次的排序
// 从最矮的人开始，如果身高相等的只有一个人，则占据第k位，并删除该位置
// 如果身高相同的人有多位，则分别占据所剩下位置的第k位，最后再一并删除该身高下所占据的所有位置
// 接着判断下一个身高的人，重复上述步骤
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() { // 从小到大排序
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });
        List<Integer> remind = new ArrayList<>();
        int[][] result = new int[people.length][2];
        for (int i = 0; i < people.length; ++i) {
            remind.add(i); // 新建要插入的位置节点
        }
        int i = 0;
        while (i < people.length) {
            int pre = people[i][0];
            List<Integer> remove = new ArrayList<>(); // 需要删除的位置
            while (i < people.length && pre == people[i][0]) { // 如果第一位的数一样，则需要保留节点list
                int index = remind.get(people[i][1]); // 获取剩下位置中的第（当前people的第二位）位
                result[index] = people[i];
                remove.add(index);
                i++;
            }
            remind.removeAll(remove); // 删除已经被占用的节点
        }
        return result;
    }
}

// discuss
// 反向排序，从大到小插入队伍中，因为后插入的人矮，对先插入人的属性没有任何影响，
// 并且同等身高的插入顺序是按照k从小到大进行的，所以每个人只需要插入到队伍的第k个位置即可
public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,new Comparator<int[]>(){ // 对(h, k)进行h为反序k为正序的排序
           @Override
           public int compare(int[] o1, int[] o2){
               return o1[0]!=o2[0]?-o1[0]+o2[0]:o1[1]-o2[1];
           }
        });
        List<int[]> res = new LinkedList<>();
        for(int[] cur : people){
            res.add(cur[1],cur); // 对于每个人插入到队伍的第k个位置，不会越界
        }
        return res.toArray(new int[people.length][]);
    }
}
