/**
Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
*/
My code://4ms 97% 虽然做出来但是感觉不熟练
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m==n) return head;
        ListNode first=new ListNode(0);
        first.next=head;
        ListNode move=first;//移动指针
        ListNode begin=null,start=null,pre=null;//前连接标识，子链头部，前一个节点
        int count=0;
        while(true){
            if(count>m&&count<=n){
                ListNode l=move.next;
                move.next=pre;
                pre=move;
                if(count==n)
                    begin.next=move;
                move=l;
                count++;
                continue;
            }
            if(count==m-1) 
                begin=move;
            if(count==m){
                start=move;
                pre=start;
            }
            if(count==n+1){
                start.next=move;
                break;
            }
            move=move.next;
            count++;
        }
        return first.next;
    }
}
Discuss:
（1）
//先移动指针到要反转节点前一个节点处，然后在范围内进行反转操作
//反转操作是将后一个节点进行插入操作
// first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4 
// second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

public ListNode reverseBetween(ListNode head, int m, int n) {
    if(head == null) return null;
    ListNode dummy = new ListNode(0); 
    dummy.next = head;
    ListNode pre = dummy; 
    for(int i = 0; i<m-1; i++) pre = pre.next;
    //一直指向第一个节点，即反转后的最后一个节点
    ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
    //要反转的节点
    ListNode then = start.next; // a pointer to a node that will be reversed
    
    // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
    // dummy-> 1 -> 2 -> 3 -> 4 -> 5
    
    for(int i=0; i<n-m; i++)
    {
        start.next = then.next;
        then.next = pre.next;
        pre.next = then;
        then = start.next;
    }
    
    // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
    // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)
    
    return dummy.next;
    
}
（2）C++
class Solution {  
public:
    ListNode* reverseBetween(ListNode* head, int m, int n) {
        ListNode* new_head = new ListNode(0);
        new_head -> next = head;
        ListNode* pre = new_head;
        for (int i = 0; i < m - 1; i++)
            pre = pre -> next;//链表头前一个
        ListNode* cur = pre -> next;//要翻转链表的头部
        for (int i = 0; i < n - m; i++) {
            ListNode* move = cur -> next; //因为每次反转后cur都会向后移一位，即后面一位就是要插入到头部的节点
            cur -> next = move -> next;
            move -> next = pre -> next;
            pre -> next = move;
        }
        return new_head -> next;
    }
};
