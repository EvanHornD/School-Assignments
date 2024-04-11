import java.util.Scanner;

public class Calculator2 {

    public static void askForNumbers(int operationNum){
        
    }

    public static int getInput(Scanner input){
        try {return(Integer.parseInt(input.nextLine()));
        } catch (Exception e) {return(Integer.MIN_VALUE);}
    }


    public static int getMenuInput(Scanner input){
        switch (input.nextLine().toLowerCase()) {
            case "w": return(0);
            case "s": return(1);
            case "e": return(2);
            default: return(-1);
        }
    }

    public static int moveCursor(int cursor,int input,int numOperations){
        switch (input) {
            case 0:if(cursor-1>=0){return(cursor-1);}return(cursor);
            case 1:if(cursor+1<numOperations){return(cursor+1);}return(cursor);
            default:return(cursor);}
    }

    public static void printOperationSelect(int cursor,String[] operations){
        for(int i=0;i<operations.length;i++){
            if(i==cursor){System.out.println("--> "+operations[i]);}
            else{System.out.println("\u001b[38;5;8m"+operations[i]+"\u001b[0m");}}
        System.out.println("Use the keys W and S to move up and down in the menu.\nPress E when the operation you want to do is selected");
    }

    public static void main(String[] args) {
        String operationState ="done";
        String[] operationNames = {"powerLoop","powerRecursive","multiplyLoop","multiplyRecursive","divideLoop","divideRecursive"};
        Scanner userInput = new Scanner(System.in);
        int menuCursor = 0;
        while(true){
            printOperationSelect(menuCursor,operationNames);
            int input = getMenuInput(userInput);
            menuCursor = moveCursor(menuCursor,input,operationNames.length);
            if(input == 2){
                operationState="working";
                while(!operationState.equals("done")){

                }

            }    
        }    
    }
}