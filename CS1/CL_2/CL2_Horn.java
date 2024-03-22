import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class CL2_Horn {
    static File[] fileNames;
    static char[][] loadedMaze;
    static char[][] loadedMazeTemplate;
    static char[][][] mazes;
    static char[] specialChars;
    static int[][] charCoords;
    static int[] playerCoords;

    public static File[] loadTxtFiles(String FilePath){
        //This line takes the file path and returns the parent directory
        String directoryName = new File(FilePath).getParent();
        //This creates a file out of the directory path
        File directory = new File(directoryName);
        File[] MazeFiles = {};
        //This checks if the file is a directory then loops over all of the files in the directory checking if they are .txt files and then returns the files that are a text file as an array of Files
        if (directory.isDirectory()){
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
    public static void findSpecialChars(int mazeNum){
        String memoryCell1 = "";
        String memoryCell2 = "";
        String memoryCell3 = "";
        for(int i=0;i<mazes[mazeNum].length;i++){for(int ii=0;ii<mazes[mazeNum][i].length;ii++){
            switch (mazes[mazeNum][i][ii]) {
                case 'S': memoryCell1+='S';memoryCell2+=(i+" ");memoryCell3+=(ii+" "); break;
                case 'F': memoryCell1+='F';memoryCell2+=(i+" ");memoryCell3+=(ii+" "); break;
                default: break;
            }
        
            }}
    }
    
    public static void loadFile(int mazeNum){
        loadedMaze = mazes[mazeNum];
        loadedMazeTemplate = mazes[mazeNum];
    }
    public static void printmaze(){}
    
    public static void main(String[] args) {
    fileNames = loadTxtFiles("C:\\Users\\ehorn\\GitRepositories\\School-Assignments\\CS1\\CL_2\\CL2_Horn.java");
    mazes = readTxtFiles(fileNames);
    System.out.println(Arrays.toString(fileNames));
    System.out.println(Arrays.deepToString(mazes));
}
}
