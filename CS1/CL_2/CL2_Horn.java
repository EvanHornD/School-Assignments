import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class CL2_Horn {
    static String gameState = "mazeSelect";
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
            MazeFiles = new File[ii];

            //This loop adds the txt files to the maze files array
            ii = 0;
            for (int i = 0;i<files.length;i++){
                if (files[i].getName().endsWith(".txt")){
                MazeFiles[ii] = files[i];ii++;}}
        };
        return(MazeFiles);
    }

    public static char[][][] readTxtFiles(File[] txtFiles){
        char[][][] mazes = new char[txtFiles.length][][];
        for(int i = 0; i < txtFiles.length; i++){
            try {
            Scanner mazeScanner = new Scanner(txtFiles[i]);
            int ii = 0;
            while(mazeScanner.hasNextLine()){mazeScanner.nextLine();ii++;}
            char[][] mazeLines = new char[ii][];
            mazeScanner = new Scanner(txtFiles[i]);
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
                newPos[i] = playerCoords[i]+playerMovement[input][i];}}
        if(checkPos(newPos)){
            playerCoords[0]=newPos[0];
            playerCoords[1]=newPos[1];}
    }

    public static int moveCursor(int cursor,int input){
        switch (input) {
            case 0:if(cursor-1>=0){return(cursor-1);}return(cursor);
            case 2:if(cursor+1<=fileNames.length){return(cursor+1);}return(cursor);
            default:return(cursor);}
    }

    public static char[][] updateMaze(){
        char[][] updatedMaze = new char[loadedMazeTemplate.length][loadedMazeTemplate[0].length];
        for(int i=0;i<loadedMazeTemplate.length;i++){
            for(int ii=0;ii<loadedMazeTemplate[0].length;ii++){
                updatedMaze[i][ii]=loadedMazeTemplate[i][ii];}}
        updatedMaze[playerCoords[0]][playerCoords[1]]='P';
        return(updatedMaze);
    }
    
    // public static void clearconsole(){
    //     try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
    //     catch (Exception e) {}
    // }

    public static void printMenu(int cursor){
        //clearconsole();
        for(int i=0;i<fileNames.length;i++){
            if(i==cursor){System.out.println("-->"+fileNames[i].getName().replace(".txt",""));}
            else{System.out.println(fileNames[i].getName().replace(".txt",""));}}
        if(cursor==5){System.out.println("-->Exit");}
        else{System.out.println("Exit");}
    }

    public static void printmaze(){
        //clearconsole();
        String mazeLine = "";
        for(int i=0;i<loadedMaze.length;i++){
            mazeLine = "";
            for(int ii=0;ii<loadedMaze[i].length;ii++){mazeLine+=loadedMaze[i][ii];}
            System.out.println(mazeLine);}
    }

    public static void main(String[] args) {
        fileNames = loadTxtFiles("C:\\Users\\Evan Horn\\GitRepositories\\School-Assignments\\CS1\\CL_2\\CL2_Horn.java");
        mazes = readTxtFiles(fileNames);
        int menuCursor = 0;
        while(gameState!="Exit"){
            switch (gameState) {
                case "mazeSelect":
                    printMenu(menuCursor);
                    while(gameState=="mazeSelect"){
                        int input = getInput();
                        menuCursor = moveCursor(menuCursor,input);
                        printMenu(menuCursor);
                        if(input == 4){
                            if(menuCursor==fileNames.length){gameState="Exit";}
                            else{gameState="runningMaze";}}
                    }break;
                case "runningMaze":
                    loadFile(menuCursor);
                    setPlayerCoords();
                    loadedMaze=updateMaze();
                    printmaze();
                    while (gameState=="runningMaze") {
                        int input = getInput();
                        if(input == 4){gameState="mazeSelect";break;}
                        movePlayer(input);
                        loadedMaze=updateMaze();
                        printmaze();
                        if(Arrays.equals(playerCoords,getCoords('F'))){gameState="mazeSelect";break;}
                        // System.out.println(Arrays.toString(playerCoords));
                        // System.out.println(Arrays.toString(getCoords('F')));
                    } break;
            }}
    }
}
