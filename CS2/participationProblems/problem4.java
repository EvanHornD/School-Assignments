package participationProblems;

public class problem4 {

    public static String getIncreasingString(String string){
        String lowerString = string.toLowerCase();
        int finalLongest = 1;
        int longest = 1;
        int finalStartingIndex = 0;
        int startingIndex = 0;
        for (int i = 1; i < string.length(); i++) {
            if(lowerString.charAt(i)>lowerString.charAt(i-1)){
                longest++;
                if (longest>finalLongest) {
                    finalLongest=longest;
                    finalStartingIndex=startingIndex;
                }
            }else{
                longest = 1;
                startingIndex = i;
            }
        }
        String substring = string.substring(finalStartingIndex, finalStartingIndex+finalLongest);
        return substring;
    }
    
    public static void main(String[] args) {
        System.out.println(getIncreasingString("zabfag"));
    }

    public static int countOdds(Node node){
        if (node==null) {
            return 0;
        }
        return node.data%2+countOdds(node.next);
    }

    public static int countEvenStrings(Node node){
        if (node==null) {
            return 0;
        }
        return (node.data2.length()+1)%2+countOdds(node.next);
    }
}
class Node{
    int data;
    String data2;
    Node next;

    public Node() {
    }
    public Node(int i){
        data=i;
    }
    public Node(String s){
        data2=s;
    }
}