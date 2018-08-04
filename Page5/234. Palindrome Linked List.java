/**
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
*/

My code://1ms 97%
//受最大回文串的影响，链长度为偶数或者奇数的情况都进行测试，判断还是很繁琐的
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        ListNode temp = null;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            temp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = temp;
        }
        temp = slow.next;
        slow.next = pre;
        return palindrome(slow, temp) || palindrome(slow.next, temp);
    }
    
    private boolean palindrome(ListNode left, ListNode right) {
        while (left != null && right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }
        if(left != null && right == null || left == null && right != null) return false;
        return true;
    }
}

Discuss：//根据最后fast的值来判断回文串的长度为偶数还是奇数
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode reverse = null;//相当于前一个元素
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode t = slow.next;
            slow.next = reverse;
            reverse = slow;
            slow = t;            
        }
        if(fast != null) {//判断链长度是奇是偶
            slow = slow.next;
        }
        while(reverse!=null) {
            if(slow.val != reverse.val) return false;
            slow = slow.next;
            reverse = reverse.next;
        }
        return true;
    }
}
