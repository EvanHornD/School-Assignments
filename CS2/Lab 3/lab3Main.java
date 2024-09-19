/* CS2401
 * Files needed to complete Lab 3:
 * 	- Node.java
 * 	- LinkedList.java
 *  - Lab3_Lastname.java --- the java file of your program. 
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class lab3Main{

    static String[] menuItems = {"View shop","View inventory","Sell item","Buy item","View item stats","Exit"};
    /**
     * TODO: Make a method that takes in a file name and returns a 2D String array
     * containing the value of every row in the "itemList.csv"
     * @return
     */
    public static String[][] scanItems(File file) throws FileNotFoundException{
        Scanner itemScanner = new Scanner(file);
        String itemIDs = itemScanner.nextLine();

        int numberOfItemIDs = 1;
        for (int i = 0; i < itemIDs.length(); i++) {
            if (itemIDs.charAt(i)==',') {
                numberOfItemIDs++;
            }
        }

        int numberOfItems = 0;
        while(itemScanner.hasNextLine()){
            itemScanner.nextLine();
            numberOfItems++;
        }
        String[][] itemList = new String[numberOfItems][numberOfItemIDs];

        itemScanner.close();
        itemScanner = new Scanner(file);

        String itemLine;

        String[] itemInfo = new String[numberOfItemIDs];
        String info = "";
        for (String[] item : itemList) {
            itemLine = itemScanner.nextLine();

            for (int i = 0; i < itemLine.length(); i++) {
                
            }
        }

        itemScanner.close();
        return null;
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

    clearconsole();
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

    public static void printMenu(int menuCursor){
        clearconsole();
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
        System.out.println("enter W and S to traverse the menu, enter E to select the menu item:");
        for (int i=0; i<menuItems.length;i++) {
            if(menuCursor==i){
                System.out.println("--> "+(i+1)+") "+menuItems[i]);
            } else {
                System.out.println("  "+(i+1)+") "+menuItems[i]);
            }
        }
    }

    public static void clearconsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush(); 
    }

    public static int getInput(Scanner userInput){
        switch (userInput.nextLine().toLowerCase()) {
            case "w": return(0);
            case "s": return(1);
            case "e": return(2);
            default: return(-1);
        }
    }

    public static int moveCursor(int cursor,int input){
        switch (input) {
            case 0: 
                if(cursor-1>=0){
                    return(cursor-1);
                }
                return(cursor);
            case 1: 
                if(cursor+1<=5){
                    return(cursor+1);
                }    
                return(cursor);
            default: return(cursor);
        }
    }

    public static void main(String[] args) throws Exception{
        //Read CSV file
      
        // Define your inventory
        InventoryLL inventory = new InventoryLL();

        Scanner userInputScanner = new Scanner(System.in);
        int menuCursor = 0;
        int input = 0;
        String menuState = "Main menu";
        printMenu(menuCursor);
        while(true){ 
            switch (menuState) {
                case "Main menu":
                    printMenu(menuCursor);
                    input = getInput(userInputScanner);
                    menuCursor = moveCursor(menuCursor,input);
                    if(input == 2){
                        menuState = menuItems[menuCursor];
                    }                                   
                break;
            
                case "View shop":
                    System.out.println(menuState);
                    input = getInput(userInputScanner);
                    menuState = "Main menu";
                break;

                case "View inventory":
                    System.out.println(menuState);
                    input = getInput(userInputScanner);
                    menuState = "Main menu";
                break;

                case "Sell item":
                    System.out.println(menuState);
                    input = getInput(userInputScanner);
                    menuState = "Main menu";
                break;

                case "Buy item":
                    System.out.println(menuState);
                    input = getInput(userInputScanner);
                    menuState = "Main menu";
                break;

                case "View item stats":
                    System.out.println(menuState);
                    input = getInput(userInputScanner);
                    menuState = "Main menu";
                break;

                default:
                
                break;
            }
            printMenu(menuCursor);
            if ("Exit".equals(menuState)) {
                break;
            }
        }
    }
}






