/**
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3
*/

My code:1ms 100% 思路很简单，判断当前元素是否和前一个以及后一个元素相等，都不相等说明是不重复的，其余都是重复的
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return null;
        ListNode first=new ListNode(head.val+1);//避免和第一个相等
        ListNode move=first;
        ListNode pre=first;
        while(head!=null){
            if(head.val!=pre.val&&(head.next==null||head.val!=head.next.val)){//都不相等则添加链
                move.next=head;
                move=move.next;
            }
            pre=head;
            head=head.next;
        }
        move.next=null;//如果最后一个元素重复，要把next给截断，不然因为链表的连续性会连带结果
        return first.next;
    }
}

Discuss：//判断是否重复，重复pre.next则跨越元素，不重复则移动pre
public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return null;
        ListNode FakeHead=new ListNode(0);
        FakeHead.next=head;
        ListNode pre=FakeHead;
        ListNode cur=head;
        while(cur!=null){
            while(cur.next!=null&&cur.val==cur.next.val){//重复则执行下面的第二个判断，不重复则第一个
                cur=cur.next;
            }
            if(pre.next==cur){//当指定的元素就是pre所指向的元素时，说明元素不重复
                pre=pre.next;//仅移动pre对下一个元素进行判断既可
            }
            else{//是重复元素
                pre.next=cur.next;//越过所有重复元素，对下一个元素进行判断
            }
            cur=cur.next;
        }
        return FakeHead.next;
    }
