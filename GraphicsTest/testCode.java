package GraphicsTest;

import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.util.Random;

public class testCode {
    final static Color green = new Color(84,140,78);
    final static Color yellow = new Color(181,159,59);
    final static Color black = new Color(18,18,19);
    final static Color lightGrey = new Color(129,131,132);
    final static Color darkGrey = new Color(58,58,60,255);
    final static Color white = new Color(248,248,248);

    static File answersFile = new File("C:\\Users\\ehorn\\GitRepositories\\School-Assignments\\GraphicsTest\\answers.txt");
    static File dictionaryFile = new File("C:\\Users\\ehorn\\GitRepositories\\School-Assignments\\GraphicsTest\\dictionary.txt");



    // gets your screen width and height
    public static Dimension getScreenDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (screenSize);
    }

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

    public static String getRandomAnswer(File answers) {
        try {
            Random rng = new Random();
            Scanner scanner = new Scanner(answers);
            for (int i = 0; i < rng.nextInt(2314); i++) {
            scanner.next();
            }
            String nextWord = scanner.next();
            scanner.close();
            return nextWord;
    
        } catch (Exception e) {
            System.out.println("Input/File not found!");
        }
        return null;
      }

    
    public static void createKeyBoard(newPanel panel, int screenWidth, int screenHeight){
        String[][] keyBoardStrings = {{"ENTER","Z","X","C","V","B","N","M","BACK"},{"A","S","D","F","G","H","J","K","L"},{"Q","W","E","R","T","Y","U","I","O","P"}}; 
        int keyWidth = 45;
        int keyHeight = 60;
        int keyGap = 15; 
        for (int i = 0; i < keyBoardStrings.length; i++) {
            int keyRowLength = keyBoardStrings[i].length;
            for (int j = 0; j < keyBoardStrings[i].length; j++) { 
                if(i==0&&j==0){
                    panel.addRectangle(new wordleRectangle((screenWidth/2)-(10*(keyWidth+keyGap)/2)+j*(keyWidth+keyGap), screenHeight-(keyHeight+45), keyWidth+(keyWidth+keyGap)/2, keyHeight, lightGrey, keyBoardStrings[i][j], white, "Franklin Gothic", 20),2);
                }else if(i==0&&j==8){
                    panel.addRectangle(new wordleRectangle((screenWidth/2)-(keyRowLength*(keyWidth+keyGap)/2)+j*(keyWidth+keyGap), screenHeight-(keyHeight+45), keyWidth+(keyWidth+keyGap)/2, keyHeight, lightGrey, keyBoardStrings[i][j], white,  "Franklin Gothic", 20),2);
                } else{
                panel.addRectangle(new wordleRectangle((screenWidth/2)-(keyRowLength*(keyWidth+keyGap)/2)+j*(keyWidth+keyGap), screenHeight-(((i+1)*(keyHeight+keyGap))+30), keyWidth, keyHeight, lightGrey, keyBoardStrings[i][j], white,  "Franklin Gothic", 30),2);
                }
            }
        } 
    }

    public static void createWordleRows(newPanel panel, int screenWidth, int screenHeight){
        int wordlength = 90;
        int wordGap = 15;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                panel.addRectangle(new wordleRectangle(((screenWidth/2)-(5*(wordlength+wordGap)/2))+j*(wordlength+wordGap), (screenHeight/16+48)+((i)*(wordlength+wordGap)), wordlength, wordlength, black,2,darkGrey,"", white, "Franklin Gothic",45),1);
            }
        }
    }

    public static void main(String[] args) {
        Dimension screenDimensions = getScreenDimensions();
        int width = (int)screenDimensions.getWidth();
        int height = (int)screenDimensions.getHeight();
        double horizontalScale = width/1920.;
        double verticalScale = height/1080.;

        int screenWidth = 1920;
        int screenHeight = 1080;
        screenHeight-=60;

        newFrame frame = new newFrame(width, height-30, new double[]{horizontalScale,verticalScale});
        newPanel panel = frame.getPanel();

        panel.addRectangle(new wordleRectangle( screenWidth, screenHeight, black), 0);
        panel.addRectangle(new wordleRectangle(-4, -4, screenWidth+8, (screenHeight/16)+8, 1, darkGrey, "WORDLE", white,"Franklin Gothic",50),0);
        createKeyBoard(panel, screenWidth, screenHeight);
        createWordleRows(panel, screenWidth, screenHeight);


        try {
            File[] filesInActiveDirectory = getFilesInPath(new File(System.getProperty("user.dir")));
            File directory = new File("C:\\Users\\ehorn\\GitRepositories\\School-Assignments\\GraphicsTest");
            //this loop checks every file in the active directory array to see if it matches the CL2_Horn file name
            for(int i=0;i<filesInActiveDirectory.length;i++){
                if(filesInActiveDirectory[i].getName().equals("testCode.java")){
    
                    // sets the folder the CL2_horn.java file is in to be the active directory
                    directory = new File(filesInActiveDirectory[i].getParent());
                }
            }
            File[] files = directory.listFiles();
            for (int i = 0;i<files.length;i++){
                if((files[i].getName().equals("answers.txt"))){
                    answersFile=files[i];
                    System.out.println("found answers");
                }
                else if ((files[i].getName().equals("dictionary.txt"))){
                    dictionaryFile=files[i];
                    System.out.println("found dictionary");
                }
            }
        } catch (Exception e) {
            System.out.println("couldn't find file");
        }

        Thread mainGame = new Thread(() -> {
            new wordleGameTest(getRandomAnswer(answersFile),dictionaryFile,panel);
        });
        mainGame.start();
    }
}
