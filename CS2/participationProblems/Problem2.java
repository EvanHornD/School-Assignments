
public class Problem2 {
    public static int pointToIndex(Node head, int index){
        for(int i = 0;i<index;i++){
            if (head.next!=null) {
                head = head.next;
            }else{
                return -1;
            }
        }
        return head.val;
    }

    public static Node getZigZag(Node head){
        Node counter = new Node(head.val,head);
        int length = 1;
        while(counter.next!=null){
            length++;
            counter = counter.next;
        }
        Node ZigZagHead = new Node(pointToIndex(head,length/2));
        Node ZigZagTail = ZigZagHead;
        for(int i = 1;i<length/2;i++){
            int next = pointToIndex(head, length/2+i);
            if (next!=-1) {
                ZigZagTail.next = new Node(next);
                ZigZagTail = ZigZagTail.next;
            }
            next = pointToIndex(head, length/2-i);
            if (next!=-1) {
                ZigZagTail.next = new Node(next);
                ZigZagTail = ZigZagTail.next;
            }
        }
        return ZigZagHead;
    }

    public static void printLL(Node head){
        System.out.println(head.val);
        while(head.next != null){
            head = head.next;
            System.out.println(head.val);
        }
    }
    public static void main(String[] args) {
        int[] values = new int[]{1,2,3,4,5,6,7,8,9,10};
        Node zigZagHead = new Node(values[0]);
        Node zigZagTail = zigZagHead;
        for (int i = 1; i < values.length; i++) {
            zigZagTail.next = new Node(values[i]);
            zigZagTail = zigZagTail.next;
        }
        printLL(getZigZag(zigZagHead));
    }
}

class Node{
    Node next = null;
    int val;

    Node(int val){
        this.next = null;
        this.val = val;
    }
    Node(int val, Node next){
        this.next = next;
        this.val = val;
    }
}
