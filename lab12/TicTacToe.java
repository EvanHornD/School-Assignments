//Evan Horn CS1 Lab

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    // out puts an empty board of the specified size
    public static char[][] resetBoard(int size){
        char[][] resetBoard = new char[size][size];
        for(int i=0;i<size;i++){
            for(int ii=0;ii<size;ii++){resetBoard[i][ii]=' ';}}
        return(resetBoard);
    }

    //prints the board along side all of the added separators and tile indexing
    public static void printBoard(char[][] chars){
        String board = "   ";
        //loops over the width of the board and adds the proper character that represents that collumn to the string
        char charIndex = 'A';
        for(int i=0;i<chars.length;i++){
            if(i<chars.length-1){board+=(" "+charIndex+"  ");}
            else{board+=(" "+charIndex+" \n");}
            charIndex+=1;
        }
        //loops over each row in the 2d arrray
        for(int i=0;i<chars.length;i++){
            //adds the reparator of each row to the string
            if(i>0){
                board+="   ---";
                for(int ii=0;ii<chars.length;ii++){
                    if(ii<chars.length-1){board+="+---";}
                    else{board+="\n";}}}
            
            // adds the number that represents the specific row to the beginning of the line
            if(i>8){board+=" "+(i+1);}
            else{board+=" "+(i+1)+" ";}
            
            //loops over the charcters in a row and adds them along side the proper separator to the string
            for(int ii=0;ii<chars.length;ii++){
                if(ii<chars.length-1){board+=" "+chars[i][ii]+" |";}
                else{board+=" "+chars[i][ii]+" \n";}
            }}
        // prints the string that this method has constructed
        System.out.println(board);
    }

    // turns the string that the player inputs into an array of 2 coordinates if it is able to
    public static int[] parseCoords(String coordinate,int size){
        int[] coords = new int[2];
        try{
            String temp = "";
            // adds all of the characters past the first one to temp string so it can be run through a parse int method
            for(int i=1;i<coordinate.length();i++){
                temp+=coordinate.charAt(i);
            }
            coords[1] = coordinate.charAt(0)-65;
            coords[0] = Integer.parseInt(temp)-1;
            return(coords);
        } catch(Exception e){
            coords[0] =-1;
            coords[1] =-1;
            return(coords);
        }
    }

    //checks if the coordinates are within the bounds of the board and are on an empty space
    public static boolean checkCoords(char[][] board,int[] coords,int size){
        if((coords[0]>=0)&&(coords[0]<size)&&(coords[1]>=0)&&(coords[1]<size)){
            if(board[coords[0]][coords[1]]==' '){
                return true;}}
        return false;
    }

    //adds a character to the board at a specific coordinate and returns the updated board
    public static char[][] addToBoard(char[][] board,int[] coords,char character){
        board[coords[0]][coords[1]]=character;
        return(board);
    }

    //checks if the board has any winning lines with a specific character and returns a value that represents the row collumn or diagonal that won the game
    public static int checkWinCondition(char[][] board,char character,int size){
        int winType = 1;
        // checks the x axis for a win
        for(int i=0;i<size;i++){
            int counter = 0;
            for(int ii=0;ii<size;ii++){
                if(board[i][ii]==character){counter++;}}
            if(counter==size){return(winType);}
            winType++;
        }
        // checks the y axis for a win
        for(int i=0;i<size;i++){
            int counter = 0;
            for(int ii=0;ii<size;ii++){
                if(board[ii][i]==character){counter++;}}
            if(counter==size){return(winType);}
            winType++;
        }
        // checks top left to bottom right diagonal for a win
        int counter = 0;
        for(int i=0;i<size;i++){
            if(board[i][i]==character){counter++;}}
        if(counter==size){return(winType);}
        winType++;
        // checks top right to bottom left diagonal for a win
        counter = 0;
        for(int i=0;i<size;i++){
            if(board[i][size-1-i]==character){counter++;}}
        if(counter==size){return(winType);}
        return(0);
    }

    //checks if the board has any empty spaces
    public static boolean checkIfGameOver(char[][] board,int size){
        for(int i=0;i<size;i++){
            for(int ii=0;ii<size;ii++){
                if(board[i][ii]==' '){return(false);}}}
        return(true);
    }

    //replaces the row collumn or diagonal that the player won on with a line that matches it and returns an updated board
    public static char[][] addWiningLine(char[][] board,int winType,int size){
        char[][] winningBoard = board;
        if(winType>0){
            // replaces the diagonals if either of them won
            if(winType>size*2){
                if(winType==(size*2)+1){
                    for(int i=0;i<size;i++){
                        winningBoard[i][i]=92;}
                }else{
                    for(int i=0;i<size;i++){
                        winningBoard[i][size-1-i]='/';}
                }
            //replaces the collumn if it won
            }else if(winType>size){
                for(int i=0;i<size;i++){
                    winningBoard[i][winType-size-1]='|';}
            //replaces the row if it won
            }else{
                for(int i=0;i<size;i++){
                    winningBoard[winType-1][i]='-';}
            }
        }
        return(winningBoard);
    }

    //clears the console by running the cls command but I dont know exactly how that happens so I commented it out anyways
    public static void clearconsole(){
         try {new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();} catch(Exception e){}
    }

    public static void main(String[] args) {
        String gameState = "choosingAI";
        int boardSize = 3;
        boolean AI = false;
        char[][] currentBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
        Scanner userInput = new Scanner(System.in);
        Random rng = new Random();
        while(!(gameState=="exit")){
            clearconsole();
            switch (gameState) {
                // this game state allows the player to choose whether or not they want to play vs an AI
                case "choosingAI":
                while(gameState=="choosingAI"){
                    System.out.println("Do you want to face an AI.\nenter Y for yes and N for no");
                    try{
                        if(userInput.nextLine().toLowerCase().charAt(0)=='y'){AI=true;System.out.println("AI has been enabled.");}
                        else{AI=false;System.out.println("AI has been disabled.");}
                        gameState="choosingSize";
                    }
                    catch(Exception e){
                        System.out.println(e);
                        System.out.println("\nThere was an error in your input.\nTry again.");
                    }
                }
                break;
                //this game state allows the player to choose the size of the board they want to play on
                case "choosingSize":
                    while(gameState=="choosingSize"){
                        System.out.println("Enter the board size you want.\nThe size limit 62");
                        // the size limit is 62 because the collum indexes start at 65 on the ASCII indexing chart and the last readable value is 126
                        try{
                            //takes the player input and updates the board and game state accordingly
                            boardSize = Integer.parseInt(userInput.nextLine());
                                if(boardSize<63){
                                currentBoard = resetBoard(boardSize);
                                gameState = "playingGame";
                            } else{ System.out.println("Board size too large.\nTry again.");}
                        }
                        catch(Exception e){
                            System.out.println(e);
                            System.out.println("\nThere was an error in your input.\nTry again.");
                        }
                    }
                break;
                
                //this game state is where the user actually plays tic tac toe
                case "playingGame":
                    char player = 'X';
                    printBoard(currentBoard);
                    while(gameState=="playingGame"){
                        try{
                            String playerCoords;
                            int[] coordinate = {-1,-1};
                            // checks if the player is playing vs AI
                            if(player=='X'||!AI){
                                System.out.println("Input Q to close the program and E to return to board selection.\nEnter the coordinate you want to put an "+player+" on.\nThe format for coordinates is letternumber EX: A5, G12");
                                playerCoords = userInput.nextLine();
                                //checks if the player wants to exit the game
                                if(playerCoords.toLowerCase().equals("q")){
                                    gameState="exit";
                                }else if(playerCoords.toLowerCase().equals("e")){
                                    gameState="choosingAI";
                                } else{
                                    coordinate = parseCoords(playerCoords, boardSize);
                                    clearconsole();
                                }
                            }else{
                                //repeatedly rolls random coordinates until one of them is valid if the player is playing AI
                                while(!checkCoords(currentBoard, coordinate, boardSize)){
                                    coordinate[0]=rng.nextInt(boardSize);
                                    coordinate[1]=rng.nextInt(boardSize);
                                }
                                clearconsole();
                            }
                            
                            //checks if the players coordinates are valid
                            if(checkCoords(currentBoard, coordinate, boardSize)){
                                currentBoard = addToBoard(currentBoard, coordinate, player);
                                int winType = checkWinCondition(currentBoard, player, boardSize);
                                //checks if the coordinates ended in a tie or a victory and runs the methods tied to each
                                if(winType>0){
                                    printBoard(addWiningLine(currentBoard, winType, boardSize));
                                    System.out.println(player+" won the game!\nInput anything to return to board selection");
                                    gameState = "choosingAI";
                                    userInput.nextLine();
                                } else if(checkIfGameOver(currentBoard, boardSize)){
                                    printBoard(currentBoard);
                                    System.out.println("There was a Tie!\nInput anything to return to board selection");
                                    gameState = "choosingAI";
                                    userInput.nextLine();
                                } else {
                                    printBoard(currentBoard);
                                    if(player=='X'){
                                        player='O';
                                    }else{player='X';}
                                }
                            } else{
                                if(gameState=="playingGame"){printBoard(currentBoard);System.out.println("Invalid Coordinates.\nTry again.");}
                            }
                            
                        }
                        catch(Exception e){
                            System.out.println(e);
                            printBoard(currentBoard);
                            System.out.println("\nThere was an error in your input.\nTry again.");
                        }
                    }
                break;
                default:
                    gameState="exit";
                break;
            }
        }
        userInput.close();
    }
    
}
