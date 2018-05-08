/**
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
*/

My code://1ms 100% 没有用额外空间，只用标识来完成
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode ls=null;//left start 左边链的起点
        ListNode lm=null;//left move 左边链进行赋值的移动节点
        ListNode rs=null;
        ListNode rm=null;
        while(head!=null){
            if(head.val<x){
                if(ls==null){
                    ls=head;
                    lm=head;
                }
                else{
                    lm.next=head;
                    lm=lm.next;
                }
            }
            else{
                if(rs==null){
                    rs=head;
                    rm=head;
                }
                else{
                    rm.next=head;
                    rm=rm.next;
                }
            }
            head=head.next;
        }
        if(rs==null)
            return ls;
        if(ls==null)
            return rs;
        rm.next=null;//要截断右边最后一个节点的next，因为如果最后一个节点是属于左链的，则rm.next还会连接
        lm.next=rs;
        return ls;
    }
}

Discuss://创建两个新对象来建立左右两链表
public ListNode partition(ListNode head, int x) {
    ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
    ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
    while (head!=null){
        if (head.val<x) {
            curr1.next = head;
            curr1 = head;
        }else {
            curr2.next = head;
            curr2 = head;
        }
        head = head.next;
    }
    curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
    curr1.next = dummy2.next;
    return dummy1.next;
}
