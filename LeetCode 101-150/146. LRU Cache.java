/**
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/

My code://用hashmap做缓存，用deque作为双端队列
//主要因为deque里边remove查找node的方法是通过遍历查找的，比较耗时，速度较慢
class LRUCache {
    private int capacity=0;//容量固定不变
    private int size=0;//大小
    private Map<Integer,Integer> map=new HashMap<>();
    Deque<Integer> deque=new LinkedList<>();
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {//更新
        Integer value = map.get(key);
        if(value == null) return -1;
        else{
            deque.remove(key);//遍历查找node删除
            deque.add(key);//再次添加就添加至头部了
            return value;
        }
    }
    
    public void put(int key, int value) {
        if(size + 1 > capacity){//越界
            if(map.get(key) == null){ //如果不存在，则要删两个，再加两个
                map.remove(deque.removeFirst());
            }
            map.put(key,value);//存在就覆盖并且更新
            deque.remove(key);
            deque.add(key);
        }else{//没有越界
            if(map.get(key) == null){
                map.put(key,value);
                deque.add(key);
                size++;
            }else{
                map.put(key,value);
                deque.remove(key);
                deque.add(key);
            }
        }
    }
}

Discuss：
（1）//利用LinkedHashMap里边封装好的方法来实现LRU
import java.util.LinkedHashMap;

public class LRUCache {

    private Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true) {
            //要重写这个删除最老节点的方法，因为在插入新节点的时候要判断该条件，然后删除最老节点并且插入新的节点
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void set(int key, int value) {
        map.put(key,value);//添加节点会自动判断removeEldestEntry这个条件，并且删除最老节点
    }
}
（2）//自定义一个双端链表，然后map中插入的value为自定义node
//这样对于删除该节点不用遍历，只要对pre和hid进行链接操作就可以了
public class LRUCache {
    private Map<Integer, DLinkNode> cache;
    DLinkNode tail = null;
    DLinkNode head = null;
    int capacity;

    public LRUCache(int capacity) {
        cache = new HashMap<Integer, DLinkNode>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (cache.containsKey(key)) {
            DLinkNode target = cache.get(key);
            int value = target.value;
            target.update();
            return value;
        } else return -1;
    }
    
    public void set(int key, int value) {
        if (cache.containsKey(key)) {
            DLinkNode target = cache.get(key);
            target.value = value;
            target.update();
        } else {
            if (capacity == 0) return;
            if (cache.size() == capacity) {
                cache.remove(head.key);
                head.removeFromHead();
            }
            DLinkNode newNode = new DLinkNode(key, value);
            newNode.append();
            cache.put(key, newNode);
        }
    }
    
    class DLinkNode {
        int key;
        int value;
        DLinkNode left = null;
        DLinkNode right = null;
        public DLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
        // remove head from list and update head reference.
        private void removeFromHead() {    
            // if 'this' is the only node, set both head and tail to null.
            if (tail == this) {             
                head = null;
                tail = null;
            } else {
                head = this.right;
                head.left = null;
            }
        }
        private void update() {
            // no need to update if accessing the most revently used value.
            if (tail == this) return;       
            else { 
                 // remove from current postion and update nodes (if any) on both sides.
                if (this != head) {        
                    this.left.right = this.right;
                } else {
                    head = this.right;
                }
                this.right.left = this.left;
                 // append to tail.
                this.append();             
            }
        }
        private void append() {
            // inserting the first node.
            if (tail == null) {     
                head = this;
                tail = this;
            // appned as tail and update tail reference.
            } else {                
                this.right = null;
                this.left = tail;
                tail.right =this;
                tail = this; 
            }
        }
    }
}
