package participationProblems;
public class problem5 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode head = null;
        ListNode tail = null;
        int nextNum = 0;
        int carry = 0;
        while(l1!=null||l2!=null){
            carry = nextNum/10;
            if(l1!=null&&l2!=null){
                nextNum = l1.val+l2.val+carry;
            } else if (l1!=null){
                nextNum = l1.val+carry;
            } else {
                nextNum = l2.val+carry;
            }
            if(head == null){
                head = new ListNode(nextNum%10);
                tail = head;
            } else {
                tail.next = new ListNode (nextNum%10);
                tail = tail.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        carry = nextNum/10;
        if(carry>0){
            tail.next = new ListNode(carry);
            tail = tail.next;
        }
        return head;
    }

    public static ListNode createList(int[] nums){
        ListNode node1 = new ListNode(nums[0]);
        ListNode tail1 = node1;
        for (int i = 1; i < nums.length; i++) {
            tail1.next = new ListNode(nums[i]);
            tail1 = tail1.next;
        }
        return node1;
    }
    public static void printList(ListNode node){
        while(node!=null){
            System.out.print(node.val+", ");
            node = node.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        ListNode l1 = createList(new int[]{0,5,5});
        ListNode l2 = createList(new int[]{0,5,5});
        ListNode added = addTwoNumbers(l1,l2);
        printList(added);
    }
}

class ListNode {
    int val;
    ListNode next;    
    ListNode(int x) {
        val = x;
        next = null;
    }
}
