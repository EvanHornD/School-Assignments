
import java.util.*;

public class SelectionSort {

    public static int[] Sort(int[] arr){
        // loop over every element except for the last element because it will always be sorted
        for (int i = 0; i < arr.length-1; i++) {

            // The index for the smallest element in the array
            int minElement = i;

            //find the smallest element
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElement]) {
                  
                    // Update the index of the smallest element
                    minElement = j;
                }
            }

            //move the element to its correct position
            int temp = arr[i];
            arr[i] = arr[minElement];
            arr[minElement] = temp; 
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Sort(new int[]{8,6,3,0,2,1,7})));
    }
}
