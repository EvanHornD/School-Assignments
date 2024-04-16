package lab4;

import java.util.Scanner;

public class math {
    static int diff21(int x){
        if(x<=21){
            return(Math.abs(x-21));
        }
        return(2*Math.abs(x-21));
    };
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(diff21(userInput.nextInt()));
        userInput.close();
    }
//Evan Horn
//Dang Pham
//Eugenio Fernandez
//Ariana Woocay
//Diego Guerrero
//Jesus Pasillas
//Derek Villagrana
//Thiaxi Melendez
}
