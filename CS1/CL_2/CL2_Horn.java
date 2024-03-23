import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class CL2_Horn {
    static File[] fileNames;
    static char[][] loadedMaze;
    static char[][] loadedMazeTemplate;
    static char[][][] mazes;
    static char[] specialChars;
    static int[][] specialCharCoords;
    static int[] playerCoords;

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

    public static void getPlayerCoords() {
        for(int i=0;i<specialChars.length;i++){
            if(specialChars[i]=='S'){playerCoords=specialCharCoords[i];break;}}
    }
    public static char[][] updateMaze(){
        char[][] updatedMaze = new char[loadedMazeTemplate.length][loadedMazeTemplate[0].length];
        for(int i=0;i<loadedMazeTemplate.length;i++){
            for(int ii=0;ii<loadedMazeTemplate.length;ii++){
                updatedMaze[i][ii]=loadedMazeTemplate[i][ii];}}
        updatedMaze[playerCoords[0]][playerCoords[1]]='P';
        return(updatedMaze);
    }
    
    public static void printmaze(){
        clearconsole();
        String mazeLine = "";
        for(int i=0;i<loadedMaze.length;i++){mazeLine = "";
            for(int ii=0;ii<loadedMaze[i].length;ii++){mazeLine+=loadedMaze[i][ii];}
            System.out.println(mazeLine);}
    }

    public static void clearconsole(){
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
        catch (Exception e) {}
    }

    public static void main(String[] args) {
    fileNames = loadTxtFiles("C:\\Users\\Evan Horn\\GitRepositories\\School-Assignments\\CS1\\CL_2\\CL2_Horn.java");
    mazes = readTxtFiles(fileNames);
    loadFile(0);
    getPlayerCoords();
    loadedMaze=updateMaze();
    System.out.println(Arrays.deepToString(loadedMaze));
    System.out.println(Arrays.deepToString(loadedMazeTemplate));
    
}
}
