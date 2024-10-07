import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Lab5_LastName{
    //Task 1
    public static int[] createArr(String filePath){
        return null;
    }

    public static void printArray(int[] arr){

    }

    //Task 2
    public static int linearSearch(int[] arr, int searchVal){
        return null;
    }

    //Task 3
    public static int binarySearch(int[] arr, int searchVal){
        return null;
    }

    //main
    public static void main(String[] args){

    }

/*------------------------DO NOT TOUCH------------------------*/
/*----This method will print the results of your searches---- */
public static void printResults(String name, String searchType, int index){
    System.out.println("----------------------------------------------");
    if(index == -9999){
        System.out.println("The " + searchType + " search failed to locate the value in the " + name + " array... :(");
    }else{
        System.out.println("The " + searchType + " search located the value in the " + name + " array at index " + index + "!");
    }
    System.out.println("----------------------------------------------");
    System.out.println("");
}
}//end class