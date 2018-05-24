/**
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
*/

My code:// 9ms 30% 思路还算是清晰，代码有点乱
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k==1||k==0||head==null) return head;
        ListNode result=head;//result
        boolean first=true;//是否第一次判断
        ListNode move=head;//反转移动指针
        ListNode judge=head;//判断是否>=k
        ListNode pre=null;//前一个最后一个节点
        int count=1;
        while(true){
            while(count<k&&judge!=null&&judge.next!=null){//当前节点之后count的数量
                judge=judge.next;
                count++;
            }
            if(count<k) break;//小于就跳出
            if(first){//第一次要设置result
                result=judge;
                first=false;
            }
            else
                pre.next=judge;//除了第一次要设置最后一个节点的后接节点
            judge=judge.next;//趁现在有序，判断指针后移
            ListNode start=move;//第一个节点
            while(count>1){//反转count-1次
                ListNode next=move.next;
                move.next=next.next;
                next.next=start;
                start=next;
                count--;
            }
            pre=move;//最后一个节点
            move=move.next;//下移
        }
        return result;
    }
}

Discuss：
（1）//O（1）空间复杂度
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0), start = dummy;//新建一个节点，当做第一次的最后节点
        dummy.next = head;
        while(true) {
            //这里的p就是上一次开始循环的节点，也就是反转以后最后一个节点
            ListNode p = start, c, n = p;
            start = p.next;//开始节点
            for(int i = 0; i < k && n != null; i++) n = n.next;//后移
            if(n == null) break;//后移数量内如果结果为null，跳出循环
            for(int i = 0; i < k-1; i++) {//反转k-1次，有作为前一次最后节点的p，就相当于进行k-1次反转
                c = p.next;
                p.next = c.next;
                c.next = n.next;
                n.next = c;
            }
        }
        return dummy.next;
    }
}
（2）//递归，分治法
 public ListNode reverseKGroup(ListNode head, int k) {
    //1. test weather we have more then k node left, if less then k node left we just return head 
    ListNode node = head;
    int count = 0;
    while (count < k) { 
        if(node == null)return head;//对当前节点判断是否有k个数，小于k则不进行反转
        node = node.next;
        count++;
    }
    // 2.reverse k node at current level 
    //将node当做一个新链表进行相同的判断
       ListNode pre = reverseKGroup(node, k); //pre node point to the the answer of sub-problem 
       //对当前进行反转
        while (count > 0) {  
            ListNode next = head.next; 
            head.next = pre; 
            pre = head; 
            head = next;
            count = count - 1;
        }
        return pre;
}
