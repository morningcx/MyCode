/**
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
*/

My code://2ms 96%
//先求出两个链表的长度，计算差值，长的链表先进行差值的移位，移位完成后就等长了
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        int lenA = 1;
        int lenB = 1;
        ListNode lastA = headA;
        ListNode lastB = headB;
        while(lastA.next != null){//A的长度
            lenA++;
            lastA = lastA.next;
        }
        while(lastB.next != null){//B的长度
            lenB++;
            lastB = lastB.next;
        }
        if(lastA != lastB) return null;//先判断最后一个元素是否相等，不相等说明没有交点
        
        int gap = Math.abs(lenA - lenB);//差值
        ListNode short1 = null;
        ListNode long1 = null;
        if(lenA >= lenB){
            short1 = headB;
            long1 = headA;
        }else{
            short1 = headA;
            long1 = headB;
        }
        while(gap-- > 0) long1 = long1.next;//长链表进行差值移位
        while(short1 != null){//此时两链表长度相等，遍历就完事儿了
            if(short1 == long1)
                return short1;
            short1 = short1.next;
            long1 = long1.next;
        }
        return null;
    }
}

Discuss：//惊艳！
//先对每个链表遍历一遍，然后指针指向另一个链表继续遍历
//其实当其中一个链表遍历完成，他们两个链表之间的差距即为两个链表长度的差
//先指向另一个链表头部的指针一定是短链表的指针，之后指向长链表
//待长链表遍历完成，指向短链表
//此时两个指针所指向的后续链表长度相等
//一起移动指针，若存在交点，直接返回，不存在，则有一刻同时指向null，返回null
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //boundary check
    if(headA == null || headB == null) return null;
    
    ListNode a = headA;
    ListNode b = headB;
    
    //if a & b have different len, then we will stop the loop after second iteration
    while( a != b){
    	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
        a = a == null? headB : a.next;
        b = b == null? headA : b.next;    
    }
    
    return a;
}
