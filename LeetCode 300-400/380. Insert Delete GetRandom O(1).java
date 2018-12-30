// my code 115ms 20%
// map记录value的位置，list保存插入的value用于随机获取
// 主要思想是添加时正常添加，删除时map获取相应位置list对应位置置为null，获取随机数从list获取，若value为null，则再获取一次（其实这里不算是O（1））
class RandomizedSet {
    
    private Map<Integer, Integer> map;
    private List<Integer> list;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer index = map.remove(val);
        if (index == null) return false;
        list.set(index, null);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int index = (int)(Math.random() * list.size());
        Integer num = list.get(index);
        return num == null ? getRandom() : num;
    }
}


// discuss 
// map和list作用一致，关键点在于，remove操作时，将list最后一个元素的值拷贝到删除元素所在的位置，然后删除list的最后一个元素
public class RandomizedSet {
    ArrayList<Integer> nums;
    HashMap<Integer, Integer> locs;
    java.util.Random rand = new java.util.Random();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        locs = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = locs.containsKey(val);
        if ( contain ) return false;
        locs.put( val, nums.size());
        nums.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean contain = locs.containsKey(val);
        if ( ! contain ) return false;
        int loc = locs.get(val);
        // 判断当前删除元素的位置是否为list的最后一个元素
        if (loc < nums.size() - 1 ) { // not the last one than swap the last one with this val
            int lastone = nums.get(nums.size() - 1 );
            nums.set( loc , lastone ); // 要删除的元素如果不是最后一个元素，则把最后一个元素的值覆盖拷贝给loc
            locs.put(lastone, loc); // 重置map
        }
        locs.remove(val); // 因为已经把值拷贝到loc的位置，所以删除list最后一个无用元素，时间复杂度为O（1）
        nums.remove(nums.size() - 1); // map删除
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get( rand.nextInt(nums.size()) ); // 随机数既可通过list的容量获取
    }
}
