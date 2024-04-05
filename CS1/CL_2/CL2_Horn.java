
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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CL2_Horn {
    //#region   variable initialization
    static String gameState = "directoryInput";
    static File[] fileNames;
    static File scoresFile;
    static char[][] loadedMaze;
    static int[] mazeDimenstions = new int[2];
    static char[][] loadedMazeTemplate;
    static char[][][] mazes;
    static char[] specialChars;
    static int[][] specialCharCoords;
    static int[] playerCoords;
    static int[][] playerMovement = {{-1,0},{0,-1},{1,0},{0,1}};
    static Scanner userInput = new Scanner(System.in);
    static int moveCount = 0;
    static String[] bestMoveCounts;
    //#endregion


    //#region   file editing and loading
    //because arrays have a set length a method is needed to add the contents of 2 arrays into 1 array
    //this is done by creating a new array with the length being set to the length of both arrays conbined and then adding all of the objects from both arrays 1 by one into the new array
    public static File[] addFileArrays(File[] arr1,File[] arr2){
        File[] newArr = new File[arr1.length+arr2.length];
        int arrIndex = 0;
            for(int i=0;i<arr1.length;i++){newArr[arrIndex]=arr1[i];arrIndex++;}
            for(int i=0;i<arr2.length;i++){newArr[arrIndex]=arr2[i];arrIndex++;}
        return newArr;
    }

    //because arrays have a set length a method is needed to add a new object to an array
    //this is done by creating a new array with the length being set to the length of the original array + 1 and then adding all of the objects plus 1 new one into the new array
    public static File[] addFileToArray(File[] arr1,File file){
        File[] newArr = new File[arr1.length+1];
            for(int i=0;i<arr1.length;i++){newArr[i]=arr1[i];}
            newArr[arr1.length] = file;
        return newArr;
    }

    //this method uses recursion which we recently learned, to loop over every file and folder in the directory returning the files
    //I only added this method into the game because I wanted to learn recursion please dont remove points because I wasnt supposed to do this, the game can be played without this ever being called
    public static File[] getFilesInPath(File dir){
        //creates an array that contains every file and folder in the directory
        File[] files = dir.listFiles();
        File[] newFiles = new File[0];
            for(int i=0;i<files.length;i++){
                //checks if a file in the directory is a folder and if it is it recursively calls the get files in path on the folder
                if(files[i].isDirectory()&&!(files[i].getName().equals(".git"))){
                    //this adds the 2 arrays of files into 1
                    newFiles = addFileArrays(newFiles,getFilesInPath(files[i]));
                }else{
                    //this adds the file into the array
                    newFiles = addFileToArray(newFiles, files[i]);
                }
            }
        return(newFiles);
    }

    //The purpose of this method is to get all of the maze files and add them to an array without the user having to type the name of the file
    public static File[] loadTxtFiles(String FolderPath,boolean automatic){
        try{
            //this initializes the directory to be the manually enter path to the folder
            File directory = new File(FolderPath);

            //this checks if the file loading is set the automatic
            if(automatic){

                //this runs the getFilesInPath method on the active directory which creates an array that contains every file in the directory
                File[] filesInActiveDirectory = getFilesInPath(new File(System.getProperty("user.dir")));

                //this loop checks every file in the active directory array to see if it matches the CL2_Horn file name
                for(int i=0;i<filesInActiveDirectory.length;i++){
                    if(filesInActiveDirectory[i].getName().equals("CL2_Horn.java")){

                        // sets the folder the CL2_horn.java file is in to be the active directory
                        directory = new File(filesInActiveDirectory[i].getParent());
                    }
                }
            }
            File[] MazeFiles = {};
            if (directory.isDirectory()){

                //this creates on array of files that contains the path to every file in the directory
                File[] files = directory.listFiles();
                int ii = 0;

                //This for loop gets the number of txt files in the directory
                for (int i = 0;i<files.length;i++){if (files[i].getName().endsWith(".txt")){ii++;}}

                //This creates an array with a length that matches the number of txt files
                MazeFiles = new File[ii-1];

                //This loop adds the txt files to the maze files array and properly initializes the high scores file
                ii = 0;
                for (int i = 0;i<files.length;i++){
                    if((files[i].getName().equals("highScores.txt"))){
                        scoresFile=files[i];}
                    else if (files[i].getName().endsWith(".txt")){
                        MazeFiles[ii] = files[i];ii++;}}
                };
                return(MazeFiles);}
        catch(Exception e){
            System.out.println(e);
            return(new File[0]);
        }
    }

    public static void addLinesToFile(File file,int numLines){
        try{
        Scanner fileScanner = new Scanner(file);
        String fileString = "";
        while(fileScanner.hasNextLine()){
            fileString +=(fileScanner.nextLine()+"\n");}
        for(int i=0;i<numLines;i++){
            if(i==numLines-1){fileString +="0";}
            else{fileString +=("0"+"\n");}}
        FileWriter fileUpdater = new FileWriter(file);
        fileUpdater.write(fileString);
        fileUpdater.close();
        fileScanner.close();
        }catch(Exception e){System.out.println(e);}
    }

    public static void loadHighScores(){
        try{
            //gets the list of high scores from the highScores txt file
            Scanner numScoresLen = new Scanner(scoresFile);
            int i = 0;
            while(numScoresLen.hasNextLine()){numScoresLen.nextLine();i++;}
            if(i<fileNames.length){addLinesToFile(scoresFile, (fileNames.length)-i);}
            numScoresLen.close();
            Scanner numHighScores = new Scanner(scoresFile);
            String[] highScores = new String[fileNames.length];
            i = 0;
            while(numHighScores.hasNextLine()){highScores[i]=(numHighScores.nextLine());i++;}
            numHighScores.close();
            bestMoveCounts = highScores;
        }catch(FileNotFoundException e){System.out.println(e);}
    }

    //updates the array of highscores
    public static void addHighScore(int mazeIndex, int Score){
        if(Integer.parseInt(bestMoveCounts[mazeIndex])<Score){
            bestMoveCounts[mazeIndex] =""+Score;
        }
    }

    public static void SaveHighScores(){
        try{
            Scanner highScoreScanner = new Scanner(scoresFile);
            String highScores = "";

            for(int i=0;i<bestMoveCounts.length;i++){
                if(i==bestMoveCounts.length-1){highScores+=""+bestMoveCounts[i];}
                else{highScores+=bestMoveCounts[i]+"\n";}}
            FileWriter scoresUpdater = new FileWriter(scoresFile);
            scoresUpdater.write(highScores);
            scoresUpdater.close();
            highScoreScanner.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //reads all of the text files that were created in the loadtext files method and stores them into 1 large 3 dimensional array of mazes
    public static char[][][] createSquareMazes(File[] txtFiles){
        char[][][] mazes = new char[txtFiles.length][][];
        for(int i = 0; i < txtFiles.length; i++){
            try {
                //gets the number of lines in a text file
            Scanner mazeLengthScanner = new Scanner(txtFiles[i]);
            int ii = 0;
            while(mazeLengthScanner.hasNextLine()){mazeLengthScanner.nextLine();ii++;}
            char[][] mazeLines = new char[ii][];
            mazeLengthScanner.close();
            Scanner mazeScanner = new Scanner(txtFiles[i]);

            //adds those lines to the 3 dimensional array character by character
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
            } catch (FileNotFoundException e) { 
                System.out.println(e);
                System.out.println("file:" + txtFiles[i] + "was not found");
            }
        }
        return(mazes);
    }
    //#endregion


    //#region   maze loading and updating
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
        for(int i=0;i<mazes[mazeNum].length;i++){
            for(int ii=0;ii<mazes[mazeNum][i].length;ii++){
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

    //updates the loaded maze with players coordinates applied to the template.  this has to be done as a for loop to avoid the 2 arrays being assigned to the same memory location
    public static char[][] updateMaze(){
        char[][] updatedMaze = new char[loadedMazeTemplate.length][loadedMazeTemplate[0].length];
        for(int i=0;i<loadedMazeTemplate.length;i++){
            for(int ii=0;ii<loadedMazeTemplate[0].length;ii++){
                updatedMaze[i][ii]=loadedMazeTemplate[i][ii];}}
        updatedMaze[playerCoords[0]][playerCoords[1]]='P';
        return(updatedMaze);
    }
    //#endregion


    //#region   character movement and collision
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
            moveCount++;
            for(int i=0;i<playerCoords.length;i++){
                //the player movement array stores the changes on each axis that a specific input would have on the player {{-1,0},{0,-1},{1,0},{0,1}}
                //this applies the changes from the index that the input points to in the player movement array
                newPos[i] = playerCoords[i]+playerMovement[input][i];}
        if(checkPos(newPos)){
            playerCoords[0]=newPos[0];
            playerCoords[1]=newPos[1];}}
    }
    //#endregion


    //#region   menu manipulation
    //updates the menu cursor based on the input the player gives
    public static int moveCursor(int cursor,int input){
        switch (input) {
            case 0:if(cursor-1>=0){return(cursor-1);}return(cursor);
            case 2:if(cursor+1<=fileNames.length){return(cursor+1);}return(cursor);
            default:return(cursor);}
    }
    //#endregion


    //#region   printing
    //loops throught all of the maze names and prints them in order adding in an arrow if the index of the maze is the same as the cursor
    public static void printMenu(int cursor){

        for(int i=0;i<fileNames.length;i++){
            if(i==cursor){System.out.println("-->"+fileNames[i].getName().replace(".txt","")+" HighScore: "+ bestMoveCounts[i]);}
            else{System.out.println(fileNames[i].getName().replace(".txt",""));}}
        if(cursor==fileNames.length){System.out.println("-->Exit");}
        else{System.out.println("Exit");}
        System.out.println("Use W and S to traverse the files\nPress E to choose a maze");
    }

    //prints the loaded maze
    public static void displayMaze(){
        String mazeLine = "";
        for(int i=0;i<loadedMaze.length;i++){
            mazeLine = "";
            for(int ii=0;ii<loadedMaze[i].length;ii++){mazeLine+=loadedMaze[i][ii];}
            System.out.println(mazeLine);}
        System.out.println("your current move count is: "+ moveCount);
    }
    //#endregion
    
    
    public static void main(String[] args) {
        gameState="directoryInput";
        int menuCursor = 0;
        while(gameState!="Exit"){
            switch (gameState) {
                case "directoryInput":
                    while(gameState=="directoryInput"){
                        System.out.println("do you want to put in the directory manually or automatically detect the directory which the CL2_Horn.java file is in\n1. manual\n2. automatic");
                        switch(userInput.nextLine()){
                            case"1":
                                System.out.println("Type the path to the comprehensive lab 2 folder \nEX:  C:\\Users\\ehorn\\School-Assignments\\CS1\\CL_2");
                                gameState="mazeSelect";
                                fileNames = loadTxtFiles(userInput.nextLine(),false);
                                mazes = createSquareMazes(fileNames);
                                if(fileNames.length==0){System.out.println("invalid directory");gameState="directoryInput";}
                                else{loadHighScores();} 
                            break;
                            default:
                                gameState="mazeSelect";
                                fileNames = loadTxtFiles("",true);
                                mazes = createSquareMazes(fileNames);
                                if(fileNames.length==0){System.out.println("invalid directory");gameState="directoryInput";}
                                else{loadHighScores();} 
                            break;
                        }
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
                    moveCount = 0;
                    loadSquareMaze(menuCursor);
                    setPlayerCoords();
                    loadedMaze=updateMaze();
                    displayMaze();
                    while (gameState=="runningMaze") {
                        int input = getInput();
                        if(input == 4){gameState="mazeSelect"; break;}
                        movePlayer(input);
                        loadedMaze=updateMaze();
                        displayMaze();
                        if(Arrays.equals(playerCoords,getCoords('F'))){
                            System.out.println("Congrats you beat: " + fileNames[menuCursor].getName().replace(".txt","")+"\nPress E to return to the list of mazes");
                            addHighScore(menuCursor, moveCount);
                            SaveHighScores();
                        }
                    } break;
                default:gameState="Exit";break;
            }}
    }
}
