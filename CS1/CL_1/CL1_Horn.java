package CL_1;

/* Evan Horn
 [CS1101] Comprehensive Lab 1
 This work is to be done individually. It is not permitted to.
 share, reproduce, or alter any part of this assignment for any
 purpose. Students are not permitted to share code, upload
 this assignment online in any form, or view/receive/
 modifying code written by anyone else. This assignment is part.
 of an academic course at The University of Texas at El Paso and
 a grade will be assigned for the work produced individually by
 the student.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CL1_Horn {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("food.txt");
        Scanner food = new Scanner(myFile);
        Scanner input = new Scanner(System.in);
        int numberOfItems = 0;
        double totalPrice = 0.00;
        String menuState = "mainMenu";
        String userInput = "";
        boolean isNumber = true;
        System.out.println("");
        System.out.println("WELCOME TO UTEP EATS!");
        while(menuState!="exit"){
            switch (menuState){
                case "mainMenu":
                    while (menuState == "mainMenu") {

                        System.out.println("");
                        System.out.println("----------Menu-----------");
                        System.out.println("Choose option 1-5:");
                        System.out.println("1. Add a food");
                        System.out.println("2. View cart");
                        System.out.println("3. Clear cart");
                        System.out.println("4. Checkout");
                        System.out.println("5. Exit");
                        System.out.println("--------------------------");
                        userInput = input.nextLine();

                        switch (userInput)/*checks the user input and changes the menu state accordingly*/{
                            case "1": menuState = "orderingFood"; break;
                            case "2": menuState = "viewingOrder"; break;
                            case "3": menuState = "clearingOrder"; break;
                            case "4": menuState = "checkingOut"; break;  
                            case "5": menuState = "exit"; System.out.println("Cart abandoned. Goodbye!"); break;
                            default:
                            System.out.println("");
                            System.out.println("Try again");
                        }
                    }
                    break;
                case "orderingFood":
                    while(menuState == "orderingFood"){
                        
                        food = new Scanner(myFile);
                        System.out.println("");

                        while(food.hasNextLine())/*print out the list of items that can be ordered*/{
                            System.out.println(food.nextLine());
                        }

                        System.out.println("");
                        System.out.println("Please Type name of the food you want to add:");
                        String userItem = input.nextLine();

                        boolean hasFood = false;
                        double foodPrice = 0;

                        food = new Scanner(myFile);
                        while(food.hasNextLine())/*check every line in the file to see if it contains the item the user input*/{

                            String foodLine = food.nextLine();

                            if(foodLine.contains(userItem))/*when it detects for the users item then it gets the price of the item*/{

                                hasFood = true;
                                int i = foodLine.indexOf(" ");
                                String priceString = "";

                                while(i+1<foodLine.length()){
                                    i++;
                                    if (foodLine.charAt(i)!=('.'))
                                        priceString = priceString + foodLine.charAt(i);
                                }

                                foodPrice = Integer.parseInt(priceString)*.01;
                                System.out.println("");
                            }
                        }
                        if(hasFood)/*asks the user how many items they want*/{

                                System.out.println("How many of those items to you want");
                                userInput = input.nextLine();
                                int items = Integer.parseInt(userInput);

                                if(items>=0)/*changes the total cost and number of items to reflect the purchase then returns the state to the main menu*/{
                                    numberOfItems+=items;
                                    totalPrice+=(items*foodPrice);
                                    System.out.println("");
                                    System.out.println("----------Cart------------");
                                    System.out.println("Number of Items: " + numberOfItems);
                                    System.out.println("Total: $" + totalPrice);
                                    System.out.println("--------------------------");
                                    menuState = "mainMenu";
                                }else{System.out.println("");System.out.println("Error Try Again");}
                        }
                        else{System.out.println("");System.out.println("Error Try Again");}
                    }
                    break;
                case "viewingOrder"://prints the cart then returns to the menu

                    System.out.println("");
                    System.out.println("----------Cart-----------");
                    System.out.println("Number of Items: " + numberOfItems);
                    System.out.println("Total: $" + totalPrice);
                    System.out.println("--------------------------");
                    menuState = "mainMenu";
                    break;

                case "clearingOrder":

                    System.out.println("");
                    System.out.println("Are you sure you would like to clear the cart");

                    while (menuState=="clearingOrder") {//if the user choses to cancel the order the items and price are reset

                        userInput = input.nextLine().toLowerCase();
                        switch (userInput) {

                            case "yes":
                            totalPrice = 0.00; numberOfItems = 0; 
                            System.out.println("");
                            System.out.println("Your cart is now Empty");
                            System.out.println("----------Cart------------");
                            System.out.println("Number of Items: " + numberOfItems);
                            System.out.println("Total: $" + totalPrice);
                            System.out.println("--------------------------");  
                            menuState = "mainMenu"; break;

                            case "no": menuState = "mainMenu"; break;

                            default: System.out.println(""); System.out.println("invalid response, type 'yes' or 'no' "); break;
                        }
                    }

                    menuState = "mainMenu";
                    break;

                case "checkingOut":

                    while(menuState=="checkingOut"){//variables are assigned and it asks for pickup or delivery

                        double tax = totalPrice*.0725;
                        int delivery = 0;
                        double tip = 0;
                        
                        System.out.println("");
                        System.out.println("Please choose an option below:");
                        System.out.println("1. Pickup      2. Delivery");
                        userInput = input.nextLine();

                        switch (userInput) {

                            case "1": //The user chose to pickup thier food and is being asked for the credit card

                                System.out.println("");
                                System.out.println("Number of Items: " + numberOfItems);
                                System.out.println("OrderPrice: $" + totalPrice);
                                System.out.println("Taxes: $" + tax);
                                System.out.println("Total: $" + (totalPrice+tax));
                                System.out.println("");
                                System.out.println("Please enter your 16 digit card number to complete the order");
                                userInput = input.nextLine();

                                isNumber = true;
                                for(int i=0;i<userInput.length();i++){//checks if the user input is a number
                                    if(userInput.charAt(i)<'0'||userInput.charAt(i)>'9'){isNumber = false;}
                                }

                                if(userInput.length() == 16&&isNumber){ // checks if the credit card is valid then prints recipt then ends the program
                                    System.out.println("");
                                    System.out.println("Success! Thank you for shopping with Miner Eats");
                                    System.out.println("----------Recipt----------");
                                    System.out.println("Number of Items: " + numberOfItems);
                                    System.out.println("OrderPrice: $" + totalPrice);
                                    System.out.println("Taxes: $" + tax);
                                    System.out.println("Total: $" + (totalPrice+tax));
                                    System.out.println("---------------------------");
                                    menuState = "exit";

                                }else{System.out.println("");System.out.println("Error: enter a 16 digit number");}
                                // tells the user they didnt put in a valid card number then it restarts the checking out process
                                break;

                            case "2"://if The user chose delivery and is informed that costs 5 dollars then asks for the address

                                delivery = 5;
                                System.out.println("");
                                System.out.println("There is a delivery fee of 5$");
                                System.out.println("");
                                System.out.println("Please enter your address");
                                userInput = input.nextLine();
                                String address = userInput;
                                //prints all useful information about the order
                                System.out.println("");
                                System.out.println("Number of Items: " + numberOfItems);
                                System.out.println("OrderPrice: $" + totalPrice);
                                System.out.println("Taxes: $" + tax);
                                System.out.println("Delivery fee: $" + delivery);
                                System.out.println("Total: $" + (totalPrice+tax+delivery));
                                System.out.println("Adress: " + address);
                                System.out.println("");
                                System.out.println("Would you like to enter a tip");
                                System.out.println("1.Yes       2.No");
                                userInput = input.nextLine();

                                switch (userInput){
                                    case "1"://the user was asked for a tip and the tip percentage variable was changed if they chose to tip
                                        System.out.println("");
                                        System.out.println("what tip percentage would you like to give");
                                        userInput = input.nextLine();
                                        isNumber = true;
                                        for(int i=0;i<userInput.length();i++){//checks if the user input is a number
                                            if(userInput.charAt(i)<'0'||userInput.charAt(i)>'9'){isNumber = false;}
                                        }
                                        tip = Integer.parseInt(userInput);
                                        if(isNumber){tip = totalPrice*(tip/100);}                                    
                                        break;
                                    case "2": isNumber=true; break;
                                }
                                if(isNumber){
                                    
                                    System.out.println("");
                                    System.out.println("Please enter your 16 digit card number to complete the order");
                                    userInput = input.nextLine();

                                    isNumber = true;
                                    for(int i=0;i<userInput.length();i++){//checks if the user input is a number
                                        if(userInput.charAt(i)<'0'||userInput.charAt(i)>'9'){isNumber = false;}
                                    }

                                    if(userInput.length() == 16&&isNumber){//if the user printed a valid credit card they get a recipt that includes all expenses
                                        System.out.println("");
                                        System.out.println("Success! Thank you for shopping with Miner Eats");
                                        System.out.println("----------Recipt----------");
                                        System.out.println("Number of Items: " + numberOfItems);
                                        System.out.println("OrderPrice: $" + totalPrice);
                                        System.out.println("Taxes: $" + tax);
                                        System.out.println("Delivery fee: $" + delivery);
                                        System.out.println("tip: $" + tip);
                                        System.out.println("Total: $" + (totalPrice+tax+delivery+tip));
                                        System.out.println("Address: " + address);
                                        System.out.println("---------------------------");
                                        menuState = "exit";
                                    }
                                    else
                                    {System.out.println("");System.out.println("Error: enter a 16 digit number");}
                                    break;
                                } else {System.out.println("");System.out.println("Error: enter an Integer");}

                        }
                    }
                    break;
            }
        }
        System.out.println("Ending program");
        food.close();food.close();
        input.close();
    }
}
