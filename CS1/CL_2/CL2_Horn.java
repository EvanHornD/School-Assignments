
/* Evan Horn
[CS1101] Comprehensive Lab 2
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
import java.util.Arrays;
import java.util.Scanner;

public class CL2_Horn {
    static String gameState = "directoryInput";
    static File[] fileNames;
    static char[][] loadedMaze;
    static int[] mazeDimenstions = new int[2];
    static char[][] loadedMazeTemplate;
    static char[][][] mazes;
    static char[] specialChars;
    static int[][] specialCharCoords;
    static int[] playerCoords;
    static int[][] playerMovement = {{-1,0},{0,-1},{1,0},{0,1}};
    static Scanner userInput = new Scanner(System.in);

    //The purpose of this method is to get all of the maze files and add them to an array without the user having to type the name of the file
    public static File[] loadTxtFiles(String FilePath){
        //This line takes the file path and returns the parent directory
        try{
        String directoryName = new File(FilePath).getParent();

        //This creates a file out of the directory path
        File directory = new File(directoryName);
        File[] MazeFiles = {};

        //This checks if the file is a directory
        if (directory.isDirectory()){

            //this creates on array of files that contains the path to every file in the directory
            File[] files = directory.listFiles();
            int ii = 0;

            //This for loop gets the number of txt files in the directory
            for (int i = 0;i<files.length;i++){if (files[i].getName().endsWith(".txt")){ii++;}}

            //This creates an array with a length that matches the number of txt files
            MazeFiles = new File[ii];

            //This loop adds the txt files to the maze files array
            ii = 0;
            for (int i = 0;i<files.length;i++){
                if (files[i].getName().endsWith(".txt")){
                MazeFiles[ii] = files[i];ii++;}}
        };
        return(MazeFiles);}
        catch(Exception e){
            File[] invalidDirectory = {null};
            return(invalidDirectory);
        }
    }

    //reads all of the text files that were created in the loadtext files method and stores them into 1 large 3 dimensional array of mazes
    public static char[][][] createSquareMazes(File[] txtFiles){
        char[][][] mazes = new char[txtFiles.length][][];
        for(int i = 0; i < txtFiles.length; i++){
            try {
            Scanner mazeLengthScanner = new Scanner(txtFiles[i]);
            int ii = 0;
            while(mazeLengthScanner.hasNextLine()){mazeLengthScanner.nextLine();ii++;}
            char[][] mazeLines = new char[ii][];
            mazeLengthScanner.close();
            Scanner mazeScanner = new Scanner(txtFiles[i]);
            ii = 0;
            while(mazeScanner.hasNextLine()){
                String line = mazeScanner.nextLine();
                char[] mazeChars = new char[line.length()];
                for(int iii=0;iii<line.length();iii++){mazeChars[iii] = line.charAt(iii);}
                mazeLines[ii] = mazeChars;
                ii++;
            }
            mazeScanner.close();
            mazes[i] = mazeLines;
            } catch (Exception FileNotFoundException) {
                System.out.println("file:" + txtFiles[i] + "was not found");
            }

        }
        return(mazes);
    }

    //loades a maze into 2 separate arrays, one that will be updated and displayed and another that will be used to store the original maze without any changes made to it
    public static void loadSquareMaze(int mazeNum){
        findStartAndEndPostion(mazeNum);
        loadedMaze = mazes[mazeNum];
        mazeDimenstions[0] = loadedMaze.length;
        mazeDimenstions[1] = loadedMaze[0].length;
        loadedMazeTemplate = mazes[mazeNum];
        
    }

    //loops through all of the characters in the maze template and stores the char and its coordinates into separate arrays for later use
    public static void findStartAndEndPostion(int mazeNum){
        int numOfChars = 0;
        for(int i=0;i<mazes[mazeNum].length;i++){for(int ii=0;ii<mazes[mazeNum][i].length;ii++){
            switch (mazes[mazeNum][i][ii]) {
                case 'S': numOfChars++;break;
                case 'F': numOfChars++; break;
                default: break;
            }}}
        specialChars = new char[numOfChars];
        specialCharCoords = new int[numOfChars][2];
        int charIndex=0;
        for(int i=0;i<mazes[mazeNum].length;i++){for(int ii=0;ii<mazes[mazeNum][i].length;ii++){
            int[]coord = {i,ii};
            switch (mazes[mazeNum][i][ii]) {
                case 'S': specialChars[charIndex]='S';specialCharCoords[charIndex]=coord;charIndex++; break;
                case 'F': specialChars[charIndex]='F';specialCharCoords[charIndex]=coord;charIndex++; break;
                default: break;
            }}}
    }

    //sets the characters starting position in the maze based on the values gained from the find start and end positions method
    public static void setPlayerCoords() {
        for(int i=0;i<specialChars.length;i++){
            if(specialChars[i]=='S'){playerCoords=specialCharCoords[i];break;}}
    }

    //when given a special character it will give you the coordinates that were stored instead of having to traverse the entire mazes array
    public static int[] getCoords(char C){
        for(int i=0;i<specialChars.length;i++){
            if(C==specialChars[i]){return(specialCharCoords[i]);}}
        return(specialCharCoords[0]);
    }

    //will detect the users input and will return a corresponding value
    public static int getInput(){
        switch (userInput.nextLine().toLowerCase()) {
            case "w": return(0);
            case "a": return(1);
            case "s": return(2);
            case "d": return(3);
            case "e": return(4);
            default: return(-1);
        }
    }

    //when given a coordinate it will check if it is a wall or if it is out of bounds
    public static boolean checkPos(int[] pos){
        if((pos[0]<mazeDimenstions[0])&&(pos[0]>=0)&&(pos[1]<mazeDimenstions[1])&&(pos[1]>=0)){
            switch (loadedMaze[pos[0]][pos[1]]) {
                case '#':System.out.println("you cant move through walls"); return(false);
                default:return(true);
            }}
        return(false);
    }

    //takes in an integer that is given by the getInput method and will update the players coordinates 
    public static void movePlayer(int input){
        int[] newPos = new int[2];
        if(input>=0&&input<4){
            for(int i=0;i<playerCoords.length;i++){
                //the player movement array stores the changes on each axis that a specific input would have on the player {{-1,0},{0,-1},{1,0},{0,1}}
                //this applies the changes from the index that the input points to in the player movement array
                newPos[i] = playerCoords[i]+playerMovement[input][i];}}
        if(checkPos(newPos)){
            playerCoords[0]=newPos[0];
            playerCoords[1]=newPos[1];}
    }

    //updates the menu cursor based on the input the player gives
    public static int moveCursor(int cursor,int input){
        switch (input) {
            case 0:if(cursor-1>=0){return(cursor-1);}return(cursor);
            case 2:if(cursor+1<=fileNames.length){return(cursor+1);}return(cursor);
            default:return(cursor);}
    }

    //updates the loaded maze with players coordinates applied to the template.  this has to be done as a for loop to avoid the 2 arrays being assigned to the same memory location
    public static char[][] updateMaze(){
        char[][] updatedMaze = new char[loadedMazeTemplate.length][loadedMazeTemplate[0].length];
        for(int i=0;i<loadedMazeTemplate.length;i++){
            for(int ii=0;ii<loadedMazeTemplate[0].length;ii++){
                updatedMaze[i][ii]=loadedMazeTemplate[i][ii];}}
        updatedMaze[playerCoords[0]][playerCoords[1]]='P';
        return(updatedMaze);
    }
    
    // this method would have cleared the console right before printing out the maze but I would probably be deducted points for having it because I dont know how it works very well
    // public static void clearconsole(){
    //     try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
    //     catch (Exception e) {}
    // }

    //loops throught all of the maze names and prints them in order adding in an arrow if the index of the maze is the same as the cursor
    public static void printMenu(int cursor){
        //clearconsole();
        for(int i=0;i<fileNames.length;i++){
            if(i==cursor){System.out.println("-->"+fileNames[i].getName().replace(".txt",""));}
            else{System.out.println(fileNames[i].getName().replace(".txt",""));}}
        if(cursor==fileNames.length){System.out.println("-->Exit");}
        else{System.out.println("Exit");}
        System.out.println("Use W and S to traverse the files\nPress E to choose a maze");
    }

    //prints the loaded maze
    public static void displayMaze(){
        //clearconsole();
        String mazeLine = "";
        for(int i=0;i<loadedMaze.length;i++){
            mazeLine = "";
            for(int ii=0;ii<loadedMaze[i].length;ii++){mazeLine+=loadedMaze[i][ii];}
            System.out.println(mazeLine);}
    }

    public static void main(String[] args) {
        String lapTop = "C:\\Users\\ehorn\\GitRepositories\\School-Assignments\\CS1\\CL_2\\CL2_Horn.java";
        String deskTop = "C:\\Users\\Evan Horn\\GitRepositories\\School-Assignments\\CS1\\CL_2\\CL2_Horn.java";
        System.out.println("do you want to put in the directory manually or use one of the 2 built in directories\n1. manual\n2. automatic");
        switch(userInput.nextLine()){
            case"1":gameState="directoryInput";break;
            default:
                gameState="mazeSelect";
                System.out.println("which directory do you want to use:\n1. laptop\n2. desktop");
                switch(userInput.nextLine()){
                    case"1":fileNames = loadTxtFiles(lapTop);break;
                    default:fileNames = loadTxtFiles(deskTop);break;
                }
                mazes = createSquareMazes(fileNames);
            break;
        }
        File[] invalidDirectory = {null};
        int menuCursor = 0;
        while(gameState!="Exit"){
            switch (gameState) {
                case "directoryInput":
                    while(gameState=="directoryInput"){
                        System.out.println("Insert the path to the comprehensive lab 2 file EX:\nC:\\Users\\ehorn\\GitRepositories\\School-Assignments\\CS1\\CL_2\\CL2_Horn.java");
                        gameState="mazeSelect";
                        fileNames = loadTxtFiles(userInput.nextLine());
                        mazes = createSquareMazes(fileNames);
                        if(fileNames==invalidDirectory){System.out.println("invalid directory");gameState="maze";}
                    }break;
                case "mazeSelect":
                    printMenu(menuCursor);
                    while(gameState=="mazeSelect"){
                        int input = getInput();
                        menuCursor = moveCursor(menuCursor,input);
                        if(input == 4){
                            if(menuCursor==fileNames.length){gameState="Exit";}
                            else{gameState="runningMaze";}}
                        if(gameState!="Exit"){printMenu(menuCursor);}
                    }break;
                case "runningMaze":
                    loadSquareMaze(menuCursor);
                    setPlayerCoords();
                    loadedMaze=updateMaze();
                    displayMaze();
                    while (gameState=="runningMaze") {
                        int input = getInput();
                        if(input == 4){gameState="mazeSelect";break;}
                        movePlayer(input);
                        loadedMaze=updateMaze();
                        displayMaze();
                        if(Arrays.equals(playerCoords,getCoords('F'))){gameState="mazeSelect";System.out.println("Congrats you beat: " + fileNames[menuCursor].getName().replace(".txt",""));break;}
                    } break;
                default:gameState="Exit";break;
            }}
    }
}
