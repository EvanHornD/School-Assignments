import java.io.File;
import java.util.Arrays;


public class CL2_Horn {
    static String[] fileNames = {"maze1.txt","maze1.txt","maze1.txt"};
    static char[][] loadedMaze;
    static char[][][] mazes;

    public static File[] readFiles(String FilePath){
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
                MazeFiles[ii] = new File(files[i].getName());ii++;}}
        };
        return(MazeFiles);
    }
    public static void loadFile(){}
    public static void printmaze(){}
    public static void readFile(){}
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(readFiles("C:\\Users\\Evan Horn\\GitRepositories\\School-Assignments\\CS1\\CL_2\\CL2_Horn.java")));
    }
}
