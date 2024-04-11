import java.util.Scanner;

public class Calculator {
    static String[] operations = {"powerLoop","powerRecursive","multiplyLoop","multiplyRecursive","divideLoop","divideRecursive"};

    public static int getInput(Scanner input){
        switch (input.nextLine().toLowerCase()) {
            case "w": return(0);
            case "a": return(1);
            case "s": return(2);
            case "d": return(3);
            case "e": return(4);
            default: return(-1);
        }
    }

    public static int moveCursor(int cursor,int input){
        switch (input) {
            case 0:if(cursor-1>=0){return(cursor-1);}return(cursor);
            case 2:if(cursor+1<operations.length){return(cursor+1);}return(cursor);
            default:return(cursor);}
    }

    public static void printOperationSelect(int cursor){
        for(int i=0;i<operations.length;i++){
            if(i==cursor){System.out.println("-->"+operations[i]);}
            else{System.out.println("\u001b[38;5;8m"+operations[i]+"\u001b[0m");}}
        System.out.println("Use the keys W and S to move up and down in the menu.\n Press E when the operation you want to do is selected");
    }
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int menuCursor = 0;
        while(true){
            printOperationSelect(menuCursor);
            int input = getInput(userInput);
            menuCursor = moveCursor(menuCursor,input);
            if(input == 4){
            
            }    
        }    
    }
}