/**
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/

My code://4ms 65%
//用map存储各个节点对应的拷贝节点
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null) return head;
        Map<RandomListNode,RandomListNode> map=new HashMap<>();
        RandomListNode move=head;
        RandomListNode copy=new RandomListNode(0);
        RandomListNode copyMove=copy;
        while(move!=null){
            RandomListNode node=new RandomListNode(move.label);//头结点
            copyMove.next=node;//连接
            map.put(move,node);//对应map
            copyMove=copyMove.next;
            move=move.next;
        }
        move=head;
        copyMove=copy.next;
        while(move!=null){
            copyMove.random=map.get(move.random);//连接对应random
            move=move.next;
            copyMove=copyMove.next;
        }
        return copy.next;
    }
}

Discuss：
（1）//思想大致一致，只是这个算法把连接和random放在一个循环里
public RandomListNode copyRandomList(RandomListNode head) {
  if (head == null) return null;
  
  Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
  
  // loop 1. copy all the nodes
  RandomListNode node = head;
  while (node != null) {
    map.put(node, new RandomListNode(node.label));//拷贝所有节点
    node = node.next;
  }
  
  // loop 2. assign next and random pointers
  node = head;
  while (node != null) {//也不一定要按顺序遍历，也可以用entrySet()遍历
    map.get(node).next = map.get(node.next);//拷贝next
    map.get(node).random = map.get(node.random);//拷贝random
    node = node.next;
  }
  
  return map.get(head);
}

（2）//除了必要空间外，没有额外空间，三次循环解决
public RandomListNode copyRandomList(RandomListNode head) {
	RandomListNode iter = head, next;

	// First round: make copy of each node,
	// and link them together side-by-side in a single list.
  //先将拷贝节点和源节点合并到一起
  //1,1,2,2,3,3,4,4
	while (iter != null) {
		next = iter.next;

		RandomListNode copy = new RandomListNode(iter.label);
		iter.next = copy;
		copy.next = next;

		iter = next;
	}

	// Second round: assign random pointers for the copy nodes.
  //再进行random赋值
	iter = head;
	while (iter != null) {
		if (iter.random != null) {
			iter.next.random = iter.random.next;
		}
		iter = iter.next.next;
	}

	// Third round: restore the original list, and extract the copy list.
	iter = head;
	RandomListNode pseudoHead = new RandomListNode(0);
	RandomListNode copy, copyIter = pseudoHead;
  //最后进行合并链表的拆分
	while (iter != null) {
		next = iter.next.next;

		// extract the copy
		copy = iter.next;
		copyIter.next = copy;
		copyIter = copy;

		// restore the original list
		iter.next = next;

		iter = next;
	}

	return pseudoHead.next;
}
