package participationProblems;

public class problem6 {

    public static boolean binarySearch(binarySearchTree bst, int key){
        if(bst == null){
            return false;
        }
        if(bst.val == key){
            return true;
        }
        if(bst.val<key){
            return binarySearch(bst.right, key);
        } else {
            return binarySearch(bst.left, key);
        }
    }




    public static void main(String[] args){

    }


}
class binarySearchTree{
    int val;
    binarySearchTree left;
    binarySearchTree right;

    binarySearchTree(int val){
        this.val = val;
    }
}
