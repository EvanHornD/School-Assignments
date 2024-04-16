package luctureAssignments;

import java.util.ArrayList;
import java.util.Scanner;
public class classFile {

    static void println(String A){
        System.out.println(A);
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        println("what is your name");
        String name = input.nextLine();
        while(!name.equals("done")){
            names.add(name);
            println("what is your name");
            name = input.nextLine();
        }
        input.close();
        System.out.println(names);
//        int i = 1;
//        while(i<10){
//            println("My Name is Evan");
//            i++;
//        }
        /*int x = 1985;
        while(x>0){
            if(x%2 == 0){
                println(""+x);
            }
            x--;
        }*/
        /*int i = 0;
        while(i<98765){
            if(i%2 == 0 && i%3 == 0){
                println(""+i);
            }
            i++;
        }*/


    }

}
