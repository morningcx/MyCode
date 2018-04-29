/**
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/

My code://时间复杂度较高
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    ListNode[] lists;
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        if(lists.length==1) return lists[0];
        this.lists=lists;
        return calculate(lists[0],1);
    }
    ListNode calculate(ListNode list1,int n){//递归，传递前两个排序好的数组给下一个数组再进行
        ListNode list2=lists[n];
        ListNode result=new ListNode(-1);
        ListNode head=result;
        while(list1!=null&&list2!=null){
            if(list1.val<list2.val){
                result.next=list1;
                list1=list1.next;
            }
            else{
                result.next=list2;
                list2=list2.next;
            }
            result=result.next;
        }
        if(list1==null) result.next=list2;
        else result.next=list1;
        if(n==lists.length-1)
            return head.next;
        return calculate(head.next,++n);
}
}

Discuss:
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return mL(lists, 0, lists.length - 1);
    }
    //尽量合并的数组链表长度差不多，例如数组有6个链表，每个链表1个元素
    //按照我的方法，遍历次数:2+3+4+5+6=20，以下方法:2+2+3+3+6=15
    private ListNode mL(ListNode[] lists, int l, int r) {
        if (r < l) return null;
        if (r == l) return lists[r];
        
        int mid = (l + r) / 2;//二分时间复杂度降低
        ListNode a = mL(lists, l, mid), b = mL(lists, mid + 1, r);
        ListNode dmHead = new ListNode(0), cur = dmHead;
        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        cur.next = (a != null) ? a : b;
        
        return dmHead.next;
    }
}
