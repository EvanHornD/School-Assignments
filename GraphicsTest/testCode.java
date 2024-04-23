package GraphicsTest;

import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class testCode {
    final static Color green = new Color(84,140,78);
    final static Color yellow = new Color(181,159,59);
    final static Color black = new Color(18,18,19);
    final static Color lightGrey = new Color(129,131,132);
    final static Color darkGrey = new Color(58,58,60,255);
    final static Color white = new Color(248,248,248);

    // gets your screen width and height
    public static Dimension getScreenDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (screenSize);
    }

    public static String getRandomAnswer() {
        try {
            Random rng = new Random();
            Scanner scanner = new Scanner(new File("C:\\Users\\Evan Horn\\GitRepositories\\School-Assignments\\CS1\\CL_3\\answers.txt"));
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
                panel.addRectangle(new wordleRectangle(((screenWidth/2)-(5*(wordlength+wordGap)/2))+j*(wordlength+wordGap), (screenHeight/16+48)+((i)*(wordlength+wordGap)), wordlength, wordlength, black,2,darkGrey," ", white, "Franklin Gothic",45),1);
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
        screenHeight-=75 ;

        newFrame frame = new newFrame(screenDimensions,new double[]{horizontalScale,verticalScale});
        newPanel panel = frame.getPanel();

        panel.addRectangle(new wordleRectangle( screenWidth, screenHeight, black), 0);
        panel.addRectangle(new wordleRectangle(-4, -4, screenWidth+8, (screenHeight/16)+8, 1, darkGrey, "WORDLE", white,"Franklin Gothic",50),0);
        createKeyBoard(panel, screenWidth, screenHeight);
        createWordleRows(panel, screenWidth, screenHeight);
        Thread mainGame = new Thread(() -> {
            new wordleGameTest(getRandomAnswer(),panel);
        });
        mainGame.start();
    }
}
