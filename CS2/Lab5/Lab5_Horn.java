import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;


public class Lab5_Horn{
    // I have explained this same code a million times in comments I shouldnt have to here
    public static int[] createArr(String filePath){
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            int arrLengthCounter = 0;
            while(scanner.hasNextLine()){
                scanner.nextLine();
                arrLengthCounter++;
            }
            int[] arr = new int[arrLengthCounter];

            scanner.close();
            scanner = new Scanner(file);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(scanner.nextLine());
            }
            scanner.close();

            return arr;
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file: " + filePath);
            return new int[]{};
        }
    }

    //print
    public static void printArray(int[] arr){
        System.out.println(Arrays.toString(arr));
    }

    //itterate over the entire array chacking if the values match the search value
    public static int linearSearch(int[] arr, int searchVal){
        for (int i = 0; i < arr.length; i++) {
            if(searchVal==arr[i]){
                return i;
            }
        }
        return -1;
    }

    // arr is the sorted array that will be search through
    // value is the item we are searching for
    public static int binarySearch(int[] arr, int value){

        // the left and right variables represent the bounds we are searching in
        int left = 0;
        int right = arr.length-1;

        // we loop until the bounds are no longer  on their respective sides
        while (left<=right){

            //the middle value is the center between the left and right boundaries
            int middle = left+(right-left)/2;

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

    //main
    public static void main(String[] args){
        // declare the paths to the files we will be using and their names
        String[] filePaths = new String[]{"CS2\\Lab5\\smallArray.txt","CS2\\Lab5\\mediumArray.txt","CS2\\Lab5\\largeArray.txt"};
        String[] fileNames = new String[]{"Small","Medium","Large"};

        //loop over each of them checking if they are empty, meaning we couldnt fine the files and then printing the result of the 2 searches
        for (int i = 0; i < filePaths.length; i++){
            String filePath = filePaths[i];
            int[] array = createArr(filePath);
            if(array.length>0){
                printArray(array);
                printResults(fileNames[i], "linear", linearSearch(array, 6));
                printResults(fileNames[i], "binary", binarySearch(array, 6));
            } else {
                System.out.println("array " + filePath + " is empty");
            }
        }
    }

/*------------------------DO NOT TOUCH------------------------*/
/*----This method will print the results of your searches---- */
public static void printResults(String name, String searchType, int index){
    System.out.println("----------------------------------------------");
    if(index == -1){
        System.out.println("The " + searchType + " search failed to locate the value in the " + name + " array... :(");
    }else{
        System.out.println("The " + searchType + " search located the value in the " + name + " array at index " + index + "!");
    }
    System.out.println("----------------------------------------------");
    System.out.println("");
}
}//end class