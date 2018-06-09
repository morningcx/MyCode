/**
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
*/

My code://3ms 100%
//先找到单链表的中间节点，然后分为两半，前一半顺序不变，后一边反转，再合成一个目标链表
class Solution {
    public void reorderList(ListNode head) {
        if(head==null) return ; 
        ListNode move=head;
        int count=0;
        while(move!=null){//链表长度计数
            count++;
            move=move.next;
        }
        move=head;
        count=count%2==0?count/2:count/2+1;//取中间节点，长度为奇数要加1
        while(count-->1) move=move.next;//移动move置中间节点
        ListNode head2=move.next;//后一半链表的头部
        move.next=null;//将前一半和后一半链表截断
        //反转后半链表
        ListNode pre=null;
        while(head2!=null){
            ListNode next=head2.next;
            head2.next=pre;
            pre=head2;
            head2=next;
        }
        //合并前后两半链表
        head2=pre;
        move=head;
        while(head2!=null){
            ListNode next1=move.next;
            ListNode next2=head2.next;
            move.next=head2;
            head2.next=next1;
            move=next1;
            head2=next2;
        }
    }
}

Discuss：//思想一致，其中单链表找中间节点的算法值得学习！
//这个算法没有将链表分为两半，而是采用了前面做过的，范围内翻转链表，然后再相应链接得到目标链表
public void reorderList(ListNode head) {
            if(head==null||head.next==null) return;
            //Find the middle of the list
            ListNode p1=head;
            ListNode p2=head;
            while(p2.next!=null&&p2.next.next!=null){ //寻找中间节点！只需遍历n/2次
                p1=p1.next;
                p2=p2.next.next;
            }
            
            //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
            //后半链表进行范围内插入式的翻转链表
            ListNode preMiddle=p1;
            ListNode preCurrent=p1.next;
            while(preCurrent.next!=null){
                ListNode current=preCurrent.next;
                preCurrent.next=current.next;
                current.next=preMiddle.next;
                preMiddle.next=current;
            }
            
            //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
            //将后半链表的各个元素，插入前半链表两个节点中间
            p1=head;
            p2=preMiddle.next;
            //前半链表最后一个节点不需要进行操作，因为每次插入后，最后一个节点即3，会一直连接插入节点后一个节点
            while(p1!=preMiddle){
                preMiddle.next=p2.next;//将前半链表最后一个节点连接置插入节点的后一个节点
                p2.next=p1.next;
                p1.next=p2;
                p1=p2.next;
                p2=preMiddle.next;
            }
        }
