import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;


public class CL2_Extra {
    //#region   variable initialization
    static String gameState = "mazeSelect";
    static File[] fileNames;
    static File scoresFile;
    static char[][] loadedMaze;
    static boolean[][] loadedMazeBitMap;
    static int[] mazeDimenstions = new int[2];
    static char[][] loadedMazeTemplate;
    static char[][][] mazes;
    static char[] specialChars;
    static int[][] specialCharCoords;
    static int[] playerCoords;
    static int[][] playerMovement = {{-1,0},{0,-1},{1,0},{0,1}};
    static Scanner userInput = new Scanner(System.in);
    static String[] bestMoveCounts;
    static int moveCount = 0;
    //#endregion


    //#region   file reading and writing

    public static File[] addFileArrays(File[] arr1,File[] arr2){
        File[] newArr = new File[arr1.length+arr2.length];
        int arrIndex = 0;
            for(int i=0;i<arr1.length;i++){newArr[arrIndex]=arr1[i];arrIndex++;}
            for(int i=0;i<arr2.length;i++){newArr[arrIndex]=arr2[i];arrIndex++;}
        return newArr;
    }

    public static File[] addFileToArray(File[] arr1,File file){
        File[] newArr = new File[arr1.length+1];
            for(int i=0;i<arr1.length;i++){newArr[i]=arr1[i];}
            newArr[arr1.length] = file;
        return newArr;
    }

    //this method uses recursion to loop over every file and folder in the directory returning the files
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

    //This gets all of the maze files and add them to an array without the user having to type the name of the file
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
                    if(filesInActiveDirectory[i].getName().equals("CL2_Extra.java")){

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

        public static char[][][] readTxtFiles(File[] txtFiles){
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
                System.out.println(scoresFile);
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

    //#endregion


    //#region   maze initialization
    public static void loadMaze(int mazeNum){
        findSpecialChars(mazeNum);
        loadedMaze = mazes[mazeNum];
        mazeDimenstions[0] = loadedMaze.length;
        mazeDimenstions[1] = loadedMaze[0].length;
        loadedMazeTemplate = mazes[mazeNum];
    }

    public static void findSpecialChars(int mazeNum){
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

    public static void setPlayerCoords() {
        for(int i=0;i<specialChars.length;i++){
            if(specialChars[i]=='S'){playerCoords=specialCharCoords[i];break;}}
    }

    public static int[] getCoords(char C){
        for(int i=0;i<specialChars.length;i++){
            if(C==specialChars[i]){return(specialCharCoords[i]);}}
        return(specialCharCoords[0]);
    }
    //#endregion


    //#region   maze collisions and movement
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

    public static boolean checkPos(int[] pos){
        if((pos[0]<mazeDimenstions[0])&&(pos[0]>=0)&&(pos[1]<mazeDimenstions[1])&&(pos[1]>=0)){
            switch (loadedMaze[pos[0]][pos[1]]) {
                case '#':return(false);
                default:return(true);
            }}
        return(false);
    }

    public static void movePlayer(int input){
        int[] newPos = new int[2];
        if(input>=0&&input<4){
            moveCount++;
            for(int i=0;i<playerCoords.length;i++){
                newPos[i] = playerCoords[i]+playerMovement[input][i];}
        if(checkPos(newPos)){
            playerCoords[0]=newPos[0];
            playerCoords[1]=newPos[1];
            for(int i=0;i<playerMovement.length;i++){
                int[] playerVision = {newPos[0]+playerMovement[i][0],newPos[1]+playerMovement[i][1]};
                if(checkPos(playerVision)){
                revealTile(playerVision);}}
            revealTile(playerCoords);
        }}
    }
    
    public static char[][] updateMaze(){
        char[][] updatedMaze = new char[loadedMazeTemplate.length][loadedMazeTemplate[0].length];
        for(int i=0;i<loadedMazeTemplate.length;i++){
            for(int ii=0;ii<loadedMazeTemplate[0].length;ii++){
                updatedMaze[i][ii]=' ';}}
        for(int i=0;i<loadedMazeTemplate.length;i++){
            for(int ii=0;ii<loadedMazeTemplate[0].length;ii++){
                updatedMaze[i][ii]=loadedMazeTemplate[i][ii];}}
        updatedMaze[playerCoords[0]][playerCoords[1]]='P';
        return(updatedMaze);
    }
    //#endregion


    //#region   menu traversal
    public static int moveCursor(int cursor,int input){
        switch (input) {
            case 0:if(cursor-1>=0){return(cursor-1);}return(cursor);
            case 2:if(cursor+1<=fileNames.length){return(cursor+1);}return(cursor);
            default:return(cursor);}
    }
    //#endregion


    //#region   bitmap manipulation
    public static boolean[][] createBitMap(){
        boolean[][] Bitmap = new boolean[loadedMaze.length][loadedMaze[0].length];
        for(int i=0;i<Bitmap.length;i++){
            for(int ii=0;ii<Bitmap[0].length;ii++){Bitmap[i][ii]=false;}}
        return(Bitmap);
    }

    public static void revealMaze(){
        for(int i=0;i<loadedMazeBitMap.length;i++){
            for(int ii=0;ii<loadedMazeBitMap[0].length;ii++){
                int[] tile = {i,ii};
                switch(loadedMaze[i][ii]){
                    case '.':revealTile(tile);break;
                    case 'S':revealTile(tile);break;
                    case 'F':revealTile(tile);break;
                    default:break;
                }
        }} 
    }

    public static void revealTile(int[] tile){
        loadedMazeBitMap[tile[0]][tile[1]] = true;
        for(int i=0;i<playerMovement.length;i++){
            if(((tile[0]+playerMovement[i][0]<mazeDimenstions[0])&&(tile[0]+playerMovement[i][0]>=0)&&(tile[1]+playerMovement[i][1]<mazeDimenstions[1])&&(tile[1]+playerMovement[i][1]>=0))){
            loadedMazeBitMap[tile[0]+playerMovement[i][0]][tile[1]+playerMovement[i][1]]=true;
        }}
    }
    //#endregion
   
    
    //#region   printing and clearing lines
    public static void clearconsole(){
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
        catch (Exception e) {}
    }

    public static void printMenu(int cursor){
        clearconsole();
        for(int i=0;i<fileNames.length;i++){
            if(i==cursor){System.out.println("-->"+fileNames[i].getName().replace(".txt","")+" HighScore: "+ bestMoveCounts[i]);}
            else{System.out.println("\u001b[38;5;8m"+fileNames[i].getName().replace(".txt","")+"\u001b[0m");}}
        if(cursor==5){System.out.println("-->Exit");}
        else{System.out.println("\u001b[38;5;8m"+"Exit"+"\u001b[0m");}
    }

    public static void printmaze(boolean mazeComplete){
        clearconsole();
        String mazeLine = "";
        for(int i=0;i<loadedMaze.length;i++){
            mazeLine = "";
            for(int ii=0;ii<loadedMaze[i].length;ii++){
                if(loadedMazeBitMap[i][ii]){mazeLine+=loadedMaze[i][ii];}
                else{mazeLine+=' ';}}
            if(mazeLine.trim()!=""){System.out.println(mazeLine);}}
        if(mazeComplete){System.out.println("Congrats you completed the maze\nPress  -E-  to exit the maze");}
    }
    //#endregion


    public static void main(String[] args) {
        gameState = "directoryInput";
        int menuCursor = 0;
        clearconsole();
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
                            mazes = readTxtFiles(fileNames);
                            if(fileNames.length==0){System.out.println("invalid directory");gameState="directoryInput";}
                            else{loadHighScores();} 
                        break;
                        default:
                            gameState="mazeSelect";
                            fileNames = loadTxtFiles("",true);
                            mazes = readTxtFiles(fileNames);
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
                    boolean mazeComplete = false;
                    loadMaze(menuCursor);
                    setPlayerCoords();
                    loadedMaze=updateMaze();
                    loadedMazeBitMap=createBitMap();
                    revealTile(playerCoords);
                    printmaze(mazeComplete);
                    while (gameState=="runningMaze") {
                        int input = getInput();
                        if(input == 4){gameState="mazeSelect";break;}
                        movePlayer(input);
                        loadedMaze=updateMaze();
                        if(Arrays.equals(playerCoords,getCoords('F'))){
                            mazeComplete=true;revealMaze();
                            addHighScore(menuCursor, moveCount);
                            SaveHighScores();}
                        printmaze(mazeComplete);
                    } break;
            }
        }
    }
}
