/**
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
*/

My code:
（1）//172ms there is no view to see my rank 233
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode move=head;
        ListNode round=head;
        while(move!=null){
            ListNode next=move.next;
            if(next==null) return null;
            if(next==move) return next;//连接自身特例，需要另加判断
            round=head;
            while(round!=move){//从头遍历链表
                if(round==next)
                    return next;
                round=round.next;
            }
            move=move.next;
        }
        return null;
    }
}
（2）//14ms 8%  
//用了额外空间hashset也就是为了省去遍历
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode move=head;
        Set<ListNode> set=new HashSet<>();
        while(move!=null){
            if(set.contains(move.next)) return move.next;
            set.add(move);
            move=move.next;
        }
        return null;
    }
}

Discuss：//这算法惊呆了！！！看哭了
//先用Linked List Cycle I的fast，slow判断是否为环，不是环就直接返回null
//若判断是环，则fast和head一起进行next操作，因为它们到循环节点的起点的距离是相等的
//head到start的距离为m，从start到相交节点的距离为n1，从相交节点到start的距离为n2
//f=m+2n1+n2,s=m+n1 -> f=2s -> m+2n1+n2=2m+2n1 -> m=n2
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                while(head!=fast){
                    head=head.next;
                    fast=fast.next;
                }
                return fast;
            }
        }
        return null;
    }
}
