package lab11;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
public class lab11 {
    static int[] reverse(int[] a){
        int[] reverseInts = new int[a.length];
        for(int i=a.length; i>0;i--){
            reverseInts[a.length-i] = a[i-1];}
        return(reverseInts);
    }
    static boolean findElementInFile(String s,File f){
        try{
            Scanner scanner = new Scanner(f);
            while(scanner.hasNextLine()){
                if(scanner.nextLine()==s){scanner.close();return(true);}
            }
            scanner.close();
            return(false);
        } catch(Exception FileNotFoundException) {
            System.out.println("couldnt find file");
            return(false);
        }
    }
    public static void main(String arg[]){
        int[] ints = {1,53,34,6,7};
        int[] reverseInts = reverse(ints);
        System.out.println(Arrays.toString(reverseInts));

        System.out.println();
    }
}
