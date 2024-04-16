package lab5;

//Evan Horn
//CS1101
//2/6/2024

import java.util.Scanner;

public class calculator {

    // this is the println method I made so I dont have to type System.out.println every time
    static void println(String string){
        System.out.println(string);
    }

    public static void main(String[] args){
        String state = "calculator";
        Scanner input = new Scanner(System.in);
        while(state == "calculator"){
            println("what arethmetic operation do you want to do (+,-,*,/)");
            char operation = input.nextLine().charAt(0);
            // stores the operator the user input by only using the first char they input
            println("what is the first number you want in your operation");
            Double num1 = input.nextDouble();
            println("what is the second number you want in your operation");
            Double num2 = input.nextDouble();
            // stores the numbers the user inputs as a doubles
            Double result = 0.;
            switch(operation) {
                case '+':
                    result = num1+num2;
                    break;
                case '-':
                    result = num1-num2;
                    break;
                case '*':
                    result = num1*num2;
                    break;
                case '/':
                    result = num1/num2;
                    break;
                // each case stores the result of an operation in the result variable that was initialized
                default:
                    println("invalid operator");

                // if the user input an invalid operator then it tells the user
            }
            println(""+result);
            println("");
            println("Do you want to do another calcuation? Yes/No");
            input.nextLine();
            String userInput = input.nextLine();
            
            switch(userInput.toLowerCase()){
                case "no":
                    state = "end";
                    break;
            }
        }
        input.close();
    }

}
