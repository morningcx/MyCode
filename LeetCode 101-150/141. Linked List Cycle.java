/**
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
*/

My code://虽然accept，但是破坏了原有的数据结构并且用了一个new空间
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode end=new ListNode(0);
        while(head!=null){
            if(head.next==end)
                return true;
            ListNode next=head.next;
            head.next=end;//都指向end节点，可以指向头结点这样就不用创建一个新节点了
            head=next;
        }
        return false;
    }
}

Discuss：//建立两个指针，一个一次移动一格，一个一次移动两格，若存在环，则必定会相撞
（1）
public boolean hasCycle(ListNode head) {
    if(head==null) return false;
    ListNode walker = head;
    ListNode runner = head;
    while(runner.next!=null && runner.next.next!=null) {
        walker = walker.next;
        runner = runner.next.next;
        if(walker==runner) return true;
    }
    return false;
}
（2）
public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        //对移动过后的fast进行判断，可以省去head==null的判断
        while(fast != null && fast.next != null){//第一个判断排除了非环链，第二个判断防止fast的两步抛出异常
            slow = slow.next;
            fast = fast.next.next;//防止这里异常，即使fast在这里被赋值null，下次循环会结束返回fasle
            if(slow == fast) return true;
        }
        return false;
    }
