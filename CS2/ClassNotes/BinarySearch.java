package ClassNotes;

public class BinarySearch {

    /*
     * The binary search algorithm run in O(log_3(n))
     */

    // arr is the sorted array that will be search through
    // value is the item we are searching for
    public static int binarySearch(int[] arr, int value){

        // the left and right variables represent the bounds we are searching in
        int left = 0;
        int right = arr.length;

        // we loop until the bounds are no longer  on their respective sides
        while (left<=right){

            //the middle value is the center between the left and right boundaries
            int middle = left + (right - left) / 2;

            // check if the value is in the middle of the array
            if(arr[middle]==value){

                // return the index it is present at
                return middle;
            }

            // check if the value is less than the middle value
            if(arr[middle]>value){

                // move the right boundary to the left of the middle value
                right = middle-1;
            } else {// if the value is greater than the middle value

                // move the left boundary to the right of the middle value
                left = middle+1;
            }
        }
        // if the value isnt in the array return -1
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1,2,3,4,5,6,7,8,9}, 6));
    }
}
