/**
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
*/

My code://1ms 100%
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode first=head;
        ListNode move=head;
        while(head!=null){
            while(head.next!=null&&head.val==head.next.val)//当前元素越过后续重复元素
                head=head.next;
            move.next=head.next;//移动指针next等于下一个不重复元素
            move=move.next;//移位
            head=head.next;//判断下一个元素
        }
        return first;
    }
}

Discuss：//遇到重复元素就忽略删除
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode list = head;
         while(list != null) {
        	 if (list.next == null) {//下一个为null则跳出循环，最后一个null是在上一次循环设置的
        		 break;
        	 }
        	 if (list.val == list.next.val) {//有重复元素则把重复元素删除
        		 list.next = list.next.next;
        	 } else {
        		 list = list.next;//不重复则跳过
        	 }
         }
         return head;
    }
}
