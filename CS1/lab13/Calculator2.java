//Evan H

import java.util.Scanner;
import java.util.Random;

public class Calculator2 {
    
    // repeatedly multiplies base by it self each time the counter goes up until the counter is equal to the exponent
    public static int powerLoop(int base, int exponent){
        int result=1;
        for(int i=0;i<exponent;i++){
            result*=base;
        }
        return result;
    }

    // repeatedly returns the base multiplied by the result of this method, each call of the method reduces the exponent by one until it equals 0 then it returns 1
    public static int powerRecursive(int base, int exponent){
        if(exponent==0){
            return 1;
        }
        return base*powerRecursive(base, exponent-1);
    }

    // repeatedly adds the number to the result each time the counter goes up until the counter is equal to the second number
    public static int multiplyLoop(int number1, int number2){
        int result=0;
        for(int i=0;i<number2;i++){
            result+=number1;
        }
        return result;
    }

    // repeatedly returns the first number plus the result of this method, each call of the method reduces number2 by one until it equals 0 then it returns 0
    public static int multiplyRecursive(int number1, int number2){
        if(number2==0){
            return 0;
        }
        return number1+multiplyRecursive(number1, number2-1);
    }

    // repeatedly subtracts the divisor from the dividend while increasing a counter each time until the dividend is less than the divisor and returns the counter
    public static int divideLoop(int dividend, int divisor){
        int numerator=dividend;
        int result=0;
        while(numerator>=divisor){
            numerator-=divisor;
            result++;
        }
        return result;
    }

    // repeatedly returns the 1 plus the result of this method, each call of the method reduces dividend by the divisor until it is less than the divisor then it returns 0
    public static int divideRecursive(int dividend, int divisor){
        if(dividend<divisor){
            return 0;
        }
        return 1+divideRecursive(dividend-divisor, divisor);
    }

    // this is the code that will ask the user for the numbers they want to be the input for their operation
    public static int[] askForNumbers(String operation,String[] factorNames,Scanner input){
        int[] operationNumbers = new int[2];
        System.out.println("Enter the numbers you want to run your chosen operation with: "+operation+"\nFirst enter the "+factorNames[0]);
        operationNumbers[0] = getInput(input);
        if(operationNumbers[0]<0){
            System.out.println("There was an error in your input, it cannot be negative. Try again.");return(new int[0]);}
        System.out.println("Enter the "+factorNames[1]);
        operationNumbers[1] = getInput(input);
        if(operationNumbers[1]<0){
            System.out.println("There was an error in your input, it cannot be negative. Try again.");return(new int[0]);}
        if((operationNumbers[1]==0)&&((operation.equals("divideWithLoop"))||operation.equals("divideWithRecursive"))){
            System.out.println("Error cannot divide by zero Try again");return(new int[0]);}
        return(operationNumbers);
    }

    // this code randomly generates 2 number that will be used to test each of the operations
    public static int[] getRandomNumbers(){
        Random rng = new Random();
        int[] randomNumbers = {(rng.nextInt(999))+1,(rng.nextInt(99))+1};
        if(randomNumbers[1]>randomNumbers[0]){
            randomNumbers[1] = rng.nextInt(randomNumbers[0])+1;
        }
        return randomNumbers;
    }

    // this code gets the user input and if it cant be parsed as an integer it is returned as a large negative number
    public static int getInput(Scanner input){
        try {return(Integer.parseInt(input.nextLine()));
        } catch (Exception e) {return(Integer.MIN_VALUE);}
    }

    // this is used to check if the used input is valid for traversing the menu
    public static int getMenuInput(Scanner input){
        switch (input.nextLine().toLowerCase()) {
            case "w": return(0);
            case "s": return(1);
            case "e": return(2);
            default: return(-1);
        }
    }

    // this changes the index of the cursor on the menu to reflect the users input
    public static int moveCursor(int cursor,int input,int numOperations){
        switch (input) {
            case 0:if(cursor-1>=0){return(cursor-1);}return(cursor);
            case 1:if(cursor+1<numOperations){return(cursor+1);}return(cursor);
            default:return(cursor);}
    }

    // this will print the menu with the option your cursor is hovering in a different color and with an arrow
    public static void printOperationSelect(int cursor,String[] operations){
        for(int i=0;i<operations.length;i++){
            if(i==cursor){System.out.println("--> "+operations[i]);}
            // this "\u001b[38;5;8m" is used to change the color to grey and is changed back to white with "\u001b[0m" this is called an ANSI color code it is cool
            else{System.out.println("\u001b[38;5;8m"+operations[i]+"\u001b[0m");}}
        System.out.println("Use the keys W and S to move up and down in the menu.\nPress E when the operation you want to do is selected");
    }

    public static void main(String[] args) {
        // initialization of the text that is used in the various print methods along side other important variables
        String operationState ="done";
        String[] operationNames = {"powerWithLoop","powerWithRecursive","multiplyWithLoop","multiplyWithRecursive","divideWithLoop","divideWithRecursive","runTestsWithRandomInts"};
        String[][] factorNames = {{"base","exponent"},{"first number","second number"},{"dividend","divisor"}};
        Scanner userInput = new Scanner(System.in);
        int menuCursor = 0;

        // this is the main loop that allows the user to enter multiple different operations in one session 
        while(true){
            // this is the code that allows the user to traverse the menu
            printOperationSelect(menuCursor,operationNames);
            int menuInput = getMenuInput(userInput);
            menuCursor = moveCursor(menuCursor,menuInput,operationNames.length);

            // this checks if the user input e and then runs the operation they were hovered over
            if(menuInput == 2){
                operationState=operationNames[menuCursor];

                // this loop exists just incase the user made any error in their inputs so they can retry inputing their operation
                while(!operationState.equals("done")){

                    // this prints the result of each operation tested with random numbers
                    if(operationState.equals("runTestsWithRandomInts")){
                        int[] operationInput = getRandomNumbers();
                        System.out.println(operationInput[0]/4+"^"+operationInput[1]/10+" With A Loop equals: "+powerLoop(operationInput[0]/4, operationInput[1]/10));
                        System.out.println(operationInput[0]/4+"^"+operationInput[1]/10+" With Recursion equals: "+powerRecursive(operationInput[0]/4, operationInput[1]/10));
                        System.out.println(operationInput[0]*2+"*"+operationInput[1]*10+" With A Loop equals: "+multiplyLoop(operationInput[0]*2, operationInput[1]*10));
                        System.out.println(operationInput[0]*2+"*"+operationInput[1]*10+" With Recursion equals: "+multiplyRecursive(operationInput[0]*2, operationInput[1]*10));
                        System.out.println(operationInput[0]*10+"/"+operationInput[1]+" With A Loop equals: "+divideLoop(operationInput[0]*10, operationInput[1]));
                        System.out.println(operationInput[0]*10+"/"+operationInput[1]+" With Recursion equals: "+divideRecursive(operationInput[0]*10, operationInput[1]));
                        operationState="done";
                    }
                    else{
                        // each one of these cases is for one of the operations the user selected which the result is printed
                        int[] operationInput = askForNumbers(operationState, factorNames[menuCursor/2], userInput);
                        if(operationInput.length>0){
                            switch (operationState) {
                                case "powerWithLoop": 
                                    System.out.println(operationInput[0]+"^"+operationInput[1]+" With A Loop equals: "+powerLoop(operationInput[0], operationInput[1]));
                                    operationState="done";
                                    break;
                                case "powerWithRecursive": 
                                    System.out.println(operationInput[0]+"^"+operationInput[1]+" With Recursion equals: "+powerRecursive(operationInput[0], operationInput[1]));
                                    operationState="done";
                                    break;
                                case "multiplyWithLoop": 
                                    System.out.println(operationInput[0]+"*"+operationInput[1]+" With A Loop equals: "+multiplyLoop(operationInput[0], operationInput[1]));
                                    operationState="done";
                                    break;
                                case "multiplyWithRecursive": 
                                    System.out.println(operationInput[0]+"*"+operationInput[1]+" With Recursion equals: "+multiplyRecursive(operationInput[0], operationInput[1]));
                                    operationState="done";
                                    break;
                                case "divideWithLoop": 
                                    System.out.println(operationInput[0]+"/"+operationInput[1]+" With A Loop equals: "+divideLoop(operationInput[0], operationInput[1]));
                                    operationState="done";
                                    break;
                                case "divideWithRecursive": 
                                    System.out.println(operationInput[0]+"/"+operationInput[1]+" With Recursion equals: "+divideRecursive(operationInput[0], operationInput[1]));
                                    operationState="done";
                                    break;
                                default:operationState="done";break;
                            }
                        }
                    }
                }
                // these lines exist so that the user can see the output before returning back to the menu
                System.out.println("--Press anything to return to operation select--");
                userInput.nextLine();
            }    
        }    
    }
}