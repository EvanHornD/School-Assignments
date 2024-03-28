import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class CL2_Extra {
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

    // The purpose of this method is to
    public static File[] loadTxtFiles(String FilePath){
        //This line takes the file path and returns the parent directory
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
            MazeFiles = new File[ii-1];

            //This loop adds the txt files to the maze files array
            ii = 0;
            for (int i = 0;i<files.length;i++){
                if((files[i].getName().equals("highScores.txt"))){
                    scoresFile=files[i];}
                else if (files[i].getName().endsWith(".txt")){
                    MazeFiles[ii] = files[i];ii++;}}
        };
        return(MazeFiles);
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

    public static void loadFile(int mazeNum){
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

    public static int moveCursor(int cursor,int input){
        switch (input) {
            case 0:if(cursor-1>=0){return(cursor-1);}return(cursor);
            case 2:if(cursor+1<=fileNames.length){return(cursor+1);}return(cursor);
            default:return(cursor);}
    }

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
    
    public static void clearconsole(){
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
        catch (Exception e) {}
    }

    public static void printMenu(int cursor){
        clearconsole();
        for(int i=0;i<fileNames.length;i++){
            if(i==cursor){System.out.println("-->"+fileNames[i].getName().replace(".txt",""));}
            else{System.out.println(fileNames[i].getName().replace(".txt",""));}}
        if(cursor==5){System.out.println("-->Exit");}
        else{System.out.println("Exit");}
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

    public static void main(String[] args) {
        String lapTop = "C:\\Users\\ehorn\\GitRepositories\\School-Assignments\\CS1\\CL_2\\CL2_Horn.java";
        String deskTop = "C:\\Users\\Evan Horn\\GitRepositories\\School-Assignments\\CS1\\CL_2\\CL2_Horn.java";
        gameState = "directoryInput";
        int menuCursor = 0;
        clearconsole();
        while(gameState!="Exit"){
            switch (gameState) {
                case "directoryInput":
                    while(gameState=="directoryInput"){
                        System.out.println("do you want to put in the directory manually or use one of the 2 built in directories\n1. manual\n2. automatic");
                        switch(userInput.nextLine()){
                            case"1":
                            clearconsole();
                            System.out.println("Insert the path to the comprehensive lab 2 file EX:\nC:\\Users\\ehorn\\GitRepositories\\School-Assignments\\CS1\\CL_2\\CL2_Horn.java");
                            gameState="mazeSelect";
                            fileNames = loadTxtFiles(userInput.nextLine());
                            mazes = readTxtFiles(fileNames);
                            clearconsole();
                            if(fileNames.length==0){System.out.println("invalid directory");gameState="directoryInput";}
                            break;
                            default:
                                clearconsole();
                                gameState="mazeSelect";
                                System.out.println("which directory do you want to use:\n1. laptop\n2. desktop");
                                switch(userInput.nextLine()){
                                    case"1":fileNames = loadTxtFiles(lapTop);break;
                                    default:fileNames = loadTxtFiles(deskTop);break;
                                }
                            mazes = readTxtFiles(fileNames);
                            clearconsole();
                            if(fileNames.length==0){System.out.println("invalid directory");gameState="directoryInput";}
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
                    loadFile(menuCursor);
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
                        if(Arrays.equals(playerCoords,getCoords('F'))){mazeComplete=true;revealMaze();}
                        printmaze(mazeComplete);
                    } break;
            }}
    }
}
