import java.io.File;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class testCode {

    public static int[] getScreenDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int[] dimensions = {(int)screenSize.getWidth(),(int)screenSize.getHeight()};
        return (dimensions);
    }

    public static void clearConsole() {
        // Clearing the console by printing a newline character multiple times
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
        catch (Exception e) {} 
    }

    static JFrame window;
    static int keyIndex = -1;
    static boolean keyState = false;
    public static void createWindow(int[] screenDimensions,int[] gameDimensions) {
            window = new JFrame("mazeGame");
            window.setSize(gameDimensions[0],gameDimensions[1]); // Set size screen width
            window.setLocation(0, screenDimensions[1]-(gameDimensions[1]+40)); // Position at bottom of screen
            window.setFocusable(true);
            window.requestFocus();
            window.setResizable(false);
            window.setUndecorated(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            window.setAlwaysOnTop(true);

            window.addKeyListener(new KeyAdapter() {public void keyPressed(KeyEvent e) {
                if(keyState){keyState = false;}
                else{keyState = true;}
                keyIndex = e.getKeyCode();
            }});

            window.addFocusListener( new FocusListener() {
                public void focusGained(FocusEvent e) {
                    window.setLocation(0, screenDimensions[1]);
                }
                public void focusLost(FocusEvent e) {
                    window.setLocation(0, screenDimensions[1]-(gameDimensions[1]+40));
                }
            });
    }
    
    public static void drawConsole(int[] dimensions){
        clearConsole();
        String screenWidth ="";
        for(int i=0; i<dimensions[0]/7-3;i++){
            if(i==0 ||i==dimensions[0]/7-4){screenWidth+="|";}
            else{screenWidth+=" ";}
        }
        String screenEdge ="";
        for(int i=0; i<dimensions[0]/7-3;i++){
            screenEdge+="-";
        }
        System.out.println(screenEdge);
        for(int i=0; i<dimensions[1]/14-10;i++){
            System.out.println(screenWidth);
        }
        System.out.println(screenEdge);
    }

    public static void main(String[] args) {
        int[] screenDimensions = getScreenDimensions();
        int[] gameDimensions = {screenDimensions[0],50};
        drawConsole(screenDimensions);
        createWindow(screenDimensions,gameDimensions);
        Thread inputThread = new Thread(() -> {
            while(true){
                //System.out.println(keyState);
                if(keyState==true){
                    System.out.println(keyIndex);
                    keyState=false;
                }
                try {
                    Thread.sleep(10); // Adjust delay as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        inputThread.start();
    }
}
