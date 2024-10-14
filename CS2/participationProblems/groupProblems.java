public class groupProblems {
    public static int oneDigit(int val){
        if(val<10){
            return val;
        }
        String str = ""+val;
        char[] ch = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }
        int value = 0;
        for (int i = 0; i < ch.length; i++) {
            value+=(ch[i]-48);
        }
        return oneDigit(value);
    }

    public static boolean hasThreeAndFive(int val){
        String value = ""+val;
        if(value.contains("3")&&value.contains("5")){
            return true;
        }
        return false;
    }
    

    public static Node getThreeAndFive(Node head){
        Node threeAndFiveHead = null;
        Node threeAndFileTail = null;
        while (head.next != null){
            if(hasThreeAndFive(head.val)){
                if (threeAndFiveHead == null) {
                    threeAndFiveHead = new Node(head.val);
                    threeAndFileTail = threeAndFiveHead;
                } else {
                    threeAndFileTail.next = new Node(head.val);
                    threeAndFileTail = threeAndFileTail.next;
                }
            }
            head=head.next;
        }
        return threeAndFiveHead;
    }

    public static void printLL(Node head){
        System.out.println(head.val);
        while(head.next != null){
            head = head.next;
            System.out.println(head.val);
        }
    }
    public static void main(String[] args) {
        //System.out.println(oneDigit(777));
        int[] values = new int[]{100,200,305,35,244435,1};
        Node threesAndFivesHead = new Node(values[0]);
        Node threesAndFivesTail = threesAndFivesHead;
        for (int i = 1; i < values.length; i++) {
            threesAndFivesTail.next = new Node(values[i]);
            threesAndFivesTail = threesAndFivesTail.next;
        }
        printLL(getThreeAndFive(threesAndFivesHead));
    }
}

class Node{
    Node next = null;
    int val;
    public Node(int val){
        this.val = val;
    }
}