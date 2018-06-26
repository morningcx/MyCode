/**
Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
*/

My code:
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode first = new ListNode(0);
        first.next = head;
        ListNode move = first;
        while(move.next != null) {
            if(move.next.val == val) {//if else也行
                move.next = move.next.next;
                continue;
            }
            move = move.next;
        }
        return first.next;
    }
}

Discuss：
（1）/递归
public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;//若当前节点数值为val则返回当前的下一个节点去连接上一个节点
}
（2）//不需要创建额外头结点，中间步骤一样，最后return判断头结点是否需要删除
public ListNode removeElements(ListNode head, int val) {
    if (head == null) return null;
    ListNode pointer = head;
    while (pointer.next != null) {
        if (pointer.next.val == val) pointer.next = pointer.next.next;
        else pointer = pointer.next;
    }
    return head.val == val ? head.next : head;//判断头结点是否需要删除，若需要则返回next节点
}
