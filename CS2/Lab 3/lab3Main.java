/* CS2401
 * Files needed to complete Lab 3:
 * 	- Node.java
 * 	- LinkedList.java
 *  - Lab3_Lastname.java --- the java file of your program. 
 * 
 */

import java.io.File;
import java.util.Scanner;

public class lab3Main{
    /**
     * TODO: Make a method that takes in a file name and returns a 2D String array
     * containing the value of every row in the "itemList.csv"
     * @return
     */
    // public static String[][] scanItems(){
    //     return;
    // }

    public static void main(String[] args) throws Exception{
        //Read CSV file
      
        // Define your inventory
        InventoryLL inventory = new InventoryLL();
         while(true){
            System.out.println("");
            System.out.println("_______________________________________________");
            System.out.println("__        __   _                ");                           
            System.out.println("\\ \\      / /__| | ___ ___  _ __ ___   ___ ");
            System.out.println(" \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\");
            System.out.println("  \\ V  V /  __/ | (_| (_) | | | | | |  __/");
            System.out.println("   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|");
            System.out.println("_______________________________________________");
            System.out.println("");
            System.out.println("           Welcome to SHOP_NAME              ");
            System.out.println("");
            System.out.println("Please select one of the following options:");
            System.out.println("1) View shop");
            System.out.println("2) View inventory");
            System.out.println("3) Sell item");
            System.out.println("4) Buy item");
            System.out.println("5) View item stats");
            System.out.println("6) Exit");

            // TODO: Create the Start Menu

            // user presses 1

            // user presses 2

            // user presses 3

            // user presses 4

            // user presses 5

            // user presses 6
        }
    }

    /*
     * 
     * 
     * DO NOT CHANGE, DISPLAYS SHOP
     * 
     * 
     * 
     */

    public static void displayShop(String[][] shop) {
    // Define column widths for each column (adjust these values to fit your data)
    int nameWidth = 32;
    int rarityWidth = 17;
    int abilityWidth = 82;
    int hpWidth = 8;
    int costWidth = 7;
    
    System.out.println();
    // Print the top border
    System.out.println("+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" 
                         + "-".repeat(abilityWidth) + "+" + "-".repeat(hpWidth) + "+" + "-".repeat(costWidth) + "+");

    // Print the header row
    System.out.printf("| %-30s | %-15s | %-80s | %-5s | %-5s |%n", 
                      "Name", "Rarity", "Magical Abilities", "HP", "Cost");

    // Print the separator after the header
    System.out.println("+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" 
                         + "-".repeat(abilityWidth) + "+" + "-".repeat(hpWidth) + "+" + "-".repeat(costWidth) + "+");

    // Loop through the 2D array and print each row
    for (int i = 0; i < shop.length; i++) {
        if(shop[i][1] == null){
            i++;
        }
        else {  // Ensure the shop row is not null
            System.out.printf("| %-30s | %-15s | %-80s | %-5s | %-5s |%n", 
                              shop[i][1] ,    // Name
                              shop[i][2] ,    // Rarity
                              shop[i][3] ,    // Magical Abilities
                              shop[i][4] ,    // HP
                              shop[i][5]      // Cost
            );
        }
    }

    // Print the bottom border
    System.out.println("+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" 
                         + "-".repeat(abilityWidth) + "+" + "-".repeat(hpWidth) + "+" + "-".repeat(costWidth) + "+");
    System.out.println();
    }
     /*
     * 
     * 
     * DO NOT CHANGE, DISPLAYS SHOP
     * 
     * 
     * 
     */

}






