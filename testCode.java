import java.io.File;
import java.util.Arrays;

public class testCode {
    public static void clearConsole() {
        // Clearing the console by printing a newline character multiple times
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static File[] loadFiles(){
        // FilePath EX: C:\\Users\\User\\School-Assignments\\testCode.java
        try{
            String workingDirectory = System.getProperty("user.dir");
            File directory = new File(workingDirectory);
            File[] files = {};
            if (directory.isDirectory()){
                files = directory.listFiles();
                // does some work
            }
            return files;
        }
        catch(Exception e){
            System.out.println(e);
            File[] invalidDirectory = {};
            return(invalidDirectory);
        }
    }
    public static void main(String[] args) {
        File[] files = loadFiles();
        System.out.println(Arrays.toString(files));
    }
}
