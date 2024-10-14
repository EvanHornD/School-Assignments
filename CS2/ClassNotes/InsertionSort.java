package ClassNotes;

public class InsertionSort {

    public static int[] sort(int[] arr) {
        // insert every item into the array except for the first one
        for (int i = 1; i < arr.length; i++) {
            // the item we are insterting
            int key = arr[i];

            // the index of the largest sorted number
            int j = i-1;

            // repeatedly check if the item at j is greater than the item we are trying to sort
            while(j>=0&&arr[j]>key){

                //slide the item at j forward in the array then decrement j
                arr[j+1]=arr[j];
                j--;
            }

            // insert the item we are trying to sort into its correct spot
            arr[j+1] = key;
        }
        return arr;
    }

    public static void main(String[] args) {
        
    }

}
