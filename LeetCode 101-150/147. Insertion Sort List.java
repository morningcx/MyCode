/**
Sort a linked list using insertion sort.


A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 

Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
*/

My code://43ms 38%
//用了顺序数组的插入排序算法，在原链上排序，不过时间消耗比较多
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode move=head;
        ListNode pre=head;
        while(move!=null){
            if(move.val<pre.val){
                pre.next=move.next;
                if(move.val<=head.val){
                    move.next=head;
                    head=move;
                }else{
                    ListNode iterator=head;
                    while(iterator.next.val<move.val) iterator=iterator.next;
                    ListNode next=iterator.next;
                    iterator.next=move;
                    move.next=next;
                }
            }
            pre=move;
            move=move.next;
        }
        return head;
    }
}

Discuss：//10ms 94%
//新建一个头结点，在新链上排序
class Solution {
    public ListNode insertionSortList(ListNode head) {
     ListNode dummy = new ListNode(0);//新链头结点
     ListNode prev = dummy;

    while (head != null) {
        ListNode temp = head.next;
        
        /* Before insert, the prev is at the last node of the sorted list.
           Only the last node's value is larger than the current inserting node 
           should we move the temp back to the head*/
        //不用每次都回到第一个节点
        if (prev.val >= head.val) prev = dummy;//判断上次停留的节点数值是否大于要插入节点，大于则回到头部遍历，小于则继续遍历

        while (prev.next != null && prev.next.val < head.val) {//不用判断等于
            prev = prev.next;
        }
        
        head.next = prev.next;
        prev.next = head;
        // prev = dummy; // Don't set prev to the head of the list after insert
        head = temp;
    }
    return dummy.next;
}
}
