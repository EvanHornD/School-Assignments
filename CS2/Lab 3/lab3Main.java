/* CS2401
 * Files needed to complete Lab 3:
 * 	- Item.java
 * 	- LinkedList.java
 *  - Lab3_Lastname.java --- the java file of your program. 
 * 
 * 
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;

public class lab3Main{

    static String[] menuItems = {"View shop","View inventory","Exit"};
    static String[] itemAttributes = {"Name", "Rarity", "Magical Abilities", "HP", "Cost"};
    static Map<String, String> rarityColors = Map.of("Common", "\u001b[37m","Uncommon", "\u001b[32m","Rare", "\u001b[34m","Very Rare", "\u001b[35m","Legendary", "\u001b[31m");

    //#region file reading

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
        itemScanner.nextLine();

        String itemLine;

        for (int r = 0; r<itemList.length;r++) {
            itemLine = itemScanner.nextLine();
            String[] itemInfo = new String[numberOfItemIDs];
            String info = "";
            int infoNum = 0;
            for (int c = 0; c <= itemLine.length(); c++) {
                if(c==itemLine.length()||','== itemLine.charAt(c)){
                    itemInfo[infoNum] = info;
                    infoNum++;
                    info = "";
                } else{
                    info+=itemLine.charAt(c);
                }
            }
            itemList[r] = itemInfo;
        }
        itemScanner.close();
        return itemList;
    }

    public static int parseShopItem(String input,String[][] shopItems){
        for (String[] item : shopItems) {
            if(item[1].toLowerCase().trim().equals(input)){
                return Integer.parseInt(item[0])-1;
            }
        }
        return -1;
    }
    //#endregion

    //#region menu displays
    public static String getScrollBar(int totalMenuItems, int menuSize, int menuTopIndex, int menuBottomIndex, int currentIndex){
        double itemsPerBar = totalMenuItems/((menuSize)*1.0);
        double scrollBarTopIndex = Math.round(menuTopIndex/itemsPerBar);
        double scrollBarBottomIndex = Math.round(menuBottomIndex/itemsPerBar);
        double scrollBarIndex = currentIndex-menuTopIndex;
        if(scrollBarIndex+.5>=scrollBarTopIndex && scrollBarIndex+.5<=scrollBarBottomIndex){
            return "\u001b[37;47m \u001b[37;40m";
        }
        return "|";
    }

    public static void displayShop(String[][] shop,int menuCursor, int displayHeight) {
    // Define column widths for each column (adjust these values to fit your data)
    int nameWidth = 32;
    int rarityWidth = 12;
    int abilityWidth = 70;
    int hpWidth = 12;
    int costWidth = 7;

    String shopInterface = "";
    // Print the top border
    shopInterface+="  +" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" + "-".repeat(abilityWidth) + "+" + "-".repeat(hpWidth) + "+" + "-".repeat(costWidth) + "+"+"\n";
    
    // Print the header row
    shopInterface+=String.format("  | %-30s | %-10s | %-68s | %-10s | %-5s |%n", "Name", "Rarity", "Magical Abilities", "HP", "Cost");
    

    // Print the separator after the header
    shopInterface+="--+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" + "-".repeat(abilityWidth) + "+" + "-".repeat(hpWidth) + "+" + "-".repeat(costWidth) + "+"+"\n";
    
    // Set the shop visual boundaries
    if(displayHeight>shop.length){
        displayHeight=shop.length;
    }
    int topIndex = menuCursor-displayHeight/2;
    int bottomIndex = (int)Math.ceil(menuCursor+(displayHeight/2.));
    if(topIndex<0){
        bottomIndex-=topIndex; 
        topIndex=0;
    }
    if(bottomIndex>shop.length){
        topIndex+=shop.length-bottomIndex;
        bottomIndex = shop.length;
    }

    // Loop through the 2D array and print each row
    for (int i = topIndex; i < bottomIndex; i++) {
        if(shop[i][1] == null){
            i++;
        }
        else {  // Ensure the shop row is not null
            if(menuCursor==i){
                String scrollBar = getScrollBar(shop.length, displayHeight+2, topIndex, bottomIndex, i);
                shopInterface+=scrollBar+" +" + "=".repeat(nameWidth) + "+" + "=".repeat(rarityWidth) + "+" + "=".repeat(abilityWidth) + "+" + "=".repeat(hpWidth) + "+" + "=".repeat(costWidth) + "+"+"\n";
                scrollBar = getScrollBar(shop.length, displayHeight+2, topIndex, bottomIndex, i+1);
                shopInterface+=String.format(scrollBar+" | %-35s | %-10s | %-68s | %-10s | %-10s |%n", 
                "--> "+rarityColors.get(shop[i][2])+shop[i][1] ,    // Name
                shop[i][2] ,    // Rarity
                shop[i][3] ,    // Magical Abilities
                shop[i][4] ,    // HP
                shop[i][5] + "\u001b[37m"     // Cost
                );
                scrollBar = getScrollBar(shop.length, displayHeight+2, topIndex, bottomIndex, i+2);
                shopInterface+=scrollBar+" +" + "=".repeat(nameWidth) + "+" + "=".repeat(rarityWidth) + "+" + "=".repeat(abilityWidth) + "+" + "=".repeat(hpWidth) + "+" + "=".repeat(costWidth) + "+"+"\n";
            }else{
                String scrollBar = "";
                if(i>menuCursor){
                    scrollBar = getScrollBar(shop.length, displayHeight+2, topIndex, bottomIndex, i+2);
                } else {
                    scrollBar = getScrollBar(shop.length, displayHeight+2, topIndex, bottomIndex, i);
                }
                shopInterface+=String.format(scrollBar+" | %-35s | %-10s | %-68s | %-10s | %-10s |%n", 
                rarityColors.get(shop[i][2])+shop[i][1] ,    // Name
                shop[i][2] ,    // Rarity
                shop[i][3] ,    // Magical Abilities
                shop[i][4] ,    // HP
                shop[i][5] + "\u001b[37m"      // Cost
                );
            }
        }
    }

    // Print the bottom border
    shopInterface+="--+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" + "-".repeat(abilityWidth) + "+" + "-".repeat(hpWidth) + "+" + "-".repeat(costWidth) + "+"+"\n";
    drawNextFrame(shopInterface);
    }

    public static void printMainMenu(int menuCursor){
        drawNextFrame("");
        System.out.println("");
        System.out.println("_______________________________________________");
        System.out.println("__        __   _                ");                           
        System.out.println("\\ \\      / /__| | ___ ___  _ __ ___   ___ ");
        System.out.println(" \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\");
        System.out.println("  \\ V  V /  __/ | (_| (_) | | | | | |  __/");
        System.out.println("   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|");
        System.out.println("_______________________________________________");
        System.out.println("");
        System.out.println("           Welcome to The Shop              ");
        System.out.println("");
        for (int i=0; i<menuItems.length;i++) {
            if(menuCursor==i){
                System.out.println("--> "+(i+1)+") "+menuItems[i]);
            } else {
                System.out.println("  "+(i+1)+") "+menuItems[i]);
            }
        }
    }

    public static void displayMenu(int menuCursor, String[][] shop, int displayHeight,InventoryLL inventory){
        switch (menuCursor) {
            case 0: 
                displayShop(shop, 0, displayHeight);
            break;

            case 1:  
                inventory.displayItems(0,7);
                if(inventory.length>0){ 
                    System.out.println("Select an item in your inventory to view its stats and potentially sell it");
                }
            break;

            default: break;
        }
    }

    // excutes a console command moving the cursor to the top left position of the console then deleting everything after the cursor
    // \033 is the code that tells the console that the next text it reads will be a code
    // [H tells it to move to the top right
    // [2J tells it to delete everything after the cursor
    // the flush command instantly sends the print command whether or not anything else is in que
    public static void drawNextFrame(String frame){
        System.out.print("\033[H\033[2J" + frame);
        System.out.flush(); 
    }

    // increases and decreases the cursor based on the button that was pressed and wheather or not it would move the cursor out of the bounds of the menu
    public static int moveCursor(int cursor,int input,int menuSize){
        switch (input) {
            case 0: 
                if(cursor-1>=0){
                    return(cursor-1);
                }
                return(cursor);
            case 2:
                if(cursor+1<=menuSize-1){
                    return(cursor+1);
                }
                return(cursor);
            default:return(cursor);}
    }
    //#endregion

    //#region real time key listener
    public static int getInput(String[] inputMap,int input){
        switch (inputMap[input].toLowerCase()) {
            case "w": return(0);
            case "a": return(1);
            case "s": return(2);
            case "d": return(3);
            case "e": return(4);
            case "q": return(5);
            default: return(-1);
        }
    }

    public static int[] getScreenDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int[] dimensions = {(int)screenSize.getWidth(),(int)screenSize.getHeight()};
        return (dimensions);
    }

    public static void applyFrameDelay(int ms){
        try {Thread.sleep(ms);} 
        catch (InterruptedException e) {e.printStackTrace();}
    }

    static JFrame window;
    static int keyIndex = -1;
    public static boolean keyState = false;
    public static void createWindow(int[] screenDimensions,int[] gameDimensions) {
            window = new JFrame("shop");
            window.setSize(0,gameDimensions[1]); // Set size screen width
            window.setLocation(0, screenDimensions[1]-(gameDimensions[1]+40)); // Position at bottom of screen
            window.setFocusable(true);
            window.requestFocus();
            window.setResizable(false);
            window.setUndecorated(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            window.setAlwaysOnTop(true);

            window.addKeyListener(new KeyAdapter() {public void keyPressed(KeyEvent e) {
                keyState = true;
                keyIndex = e.getKeyCode();
            }});

            window.addFocusListener( new FocusListener() {
                public void focusGained(FocusEvent e) {
                    window.setLocation(0, screenDimensions[1]);
                }
                public void focusLost(FocusEvent e) {
                    window.setLocation(0, screenDimensions[1]-(gameDimensions[1]+40));
                }
            });
    }

    public static void closeWindow() {
        window.dispose();
    }

    public static String[] getKeyText(){
        String[] codeToString = new String[227];
        for(int i=0;i<codeToString.length;i++){
            String text = java.awt.event.KeyEvent.getKeyText(i);
            if(!text.contains("Unknown")){codeToString[i]=text;}
            else{codeToString[i]=" ";}
        }
        return(codeToString);
    }

    //#endregion

    public static void main(String[] args) throws Exception{
        //Read CSV file
        String [][] shop = scanItems(new File("School-Assignments\\CS2\\Lab 3\\itemList.csv"));
        int displayHeight = 13;
      
        //The program is run in a thread separate from the JFrame because threads have useful commands that allow for allowing for changing the fps
        //and the program is unable to both detect input and run the main program at the same time
        String[] keyTextCodes = getKeyText();
        int[] screenDimensions = getScreenDimensions();
        int[] gameDimensions = {screenDimensions[0],50};
        createWindow(screenDimensions,gameDimensions);


        //creates the thread
        Thread gameThread = new Thread(() -> {



        //prints the instructions on how the program works
        drawNextFrame("\n".repeat(7)+"""
                                                 ==========PLEASE READ==========
                        The programs input detection is run in an invisible Window which is automatically put into focus,
                        if the Window goes out of focus, you will lose control of the program
                        click on the programs icon on the task bar to be able to continue using the program.
                      
                      ---CONTROLS---
                       W - up
                       S - down
                       E - select
                       Q - back
                      
                        PRESS E TO CONTINUE
                       """);
        boolean b = true;
        while (b) {
            applyFrameDelay(10);
            if(keyState){
                if(getInput(keyTextCodes,keyIndex)==4){
                    b=false;
                }
                keyState=false;
            }
        }

        // Define your inventory
        InventoryLL inventory = new InventoryLL();
        int selectedItem = -1;
        int menuCursor = 0;
        int input = 0;
        String menuState = "Main menu";
        printMainMenu(menuCursor);
        while(true){ 
            applyFrameDelay(10);
            switch (menuState) {
                case "Main menu":
                    if(keyState){
                        keyState=false;
                        input = getInput(keyTextCodes,keyIndex);
                        menuCursor = moveCursor(menuCursor,input,menuItems.length);
                        if(input == 4){
                            menuState = menuItems[menuCursor];
                            displayMenu(menuCursor, shop, displayHeight,inventory);
                            menuCursor=0;

                        }else if(input!=-1){
                            printMainMenu(menuCursor);
                        }
                    }                            
                break;
            
                case "View shop":
                    if(keyState){
                        keyState=false;
                        input = getInput(keyTextCodes,keyIndex);
                        menuCursor = moveCursor(menuCursor,input,shop.length);
                        switch (input) {
                            case 0: displayShop(shop, menuCursor, displayHeight); break;
                            case 2: displayShop(shop, menuCursor, displayHeight); break;
                            case 4: 
                                //System.out.println(menuCursor+Arrays.toString(shop[menuCursor]));
                                inventory.addToInventory(shop[menuCursor]);
                                System.out.println("You purchased the: "+shop[menuCursor][1]); 
                            break;
                            case 5: menuState = "Main menu"; menuCursor=0; printMainMenu(menuCursor); break;
                            default:break;
                        }
                    }   
                break;

                case "View inventory":
                    if(keyState){
                        keyState=false;
                        input = getInput(keyTextCodes,keyIndex);
                        menuCursor = moveCursor(menuCursor,input,inventory.length);
                        switch (input) {
                            case 0: // move up in inventory
                                inventory.displayItems(menuCursor,7);
                                if(inventory.length>0){ 
                                    System.out.println("Select an item in your inventory to view its stats and potentially sell it");
                                }
                                selectedItem = -1; break;
                            case 2: // move up in inventory
                                inventory.displayItems(menuCursor,7); 
                                if(inventory.length>0){ 
                                    System.out.println("Select an item in your inventory to view its stats and potentially sell it");
                                }
                                selectedItem = -1;break;
                            case 4: // select / sell item
                                if(inventory.length>0){ 
                                    if(selectedItem>=0){
                                        String[] soldItem = inventory.removeItem(inventory.getFromInventory(menuCursor).attributes[1]);
                                        if(menuCursor>0){
                                            menuCursor--;
                                        }
                                        inventory.displayItems(menuCursor,7);
                                        System.out.println("You sold the: "+ soldItem[1]);
                                        selectedItem = -1;
                                    }else{
                                        inventory.displayItems(menuCursor,7);
                                        selectedItem = menuCursor;
                                        inventory.getFromInventory(selectedItem).displayAttributes(itemAttributes);
                                        System.out.println("Press E again to sell this item.");
                                        
                                    }
                                } else{
                                    inventory.displayItems(menuCursor,7);
                                    System.out.println("You have nothing to select");
                                }
                            break;
                            case 5: // return to main menu
                                menuState = "Main menu";
                                menuCursor=0;
                                printMainMenu(menuCursor); 
                            break;
                            default:break;
                        }
                    }   
                break;
                default:
                
                break;
            }
            if ("Exit".equals(menuState)) {
                drawNextFrame("");
                closeWindow();
                System.exit(0);
            }
        }
    });
    gameThread.start();
    }
}






