package GraphicsTest;

import java.awt.*;
import javax.swing.*;

public class testCode {
    final Color green = new Color(84,140,78);
    final Color yellow = new Color(181,159,59);
    final Color black = new Color(18,18,19);
    final Color lightGrey = new Color(129,131,132);
    final Color darkGrey = new Color(58,58,60,255);
    final Color white = new Color(248,248,248);

    // gets your screen width and height
    public static Dimension getScreenDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (screenSize);
    }

    
    public static void createKeyBoard(){}

    public static void main(String[] args) {
        Dimension screenDimensions = getScreenDimensions();
        new newFrame(screenDimensions);
 
        Thread mainGame = new Thread(() -> {
            String state="running";
            while(!(state.equals("end"))){
                try {
                    
                    
                    
                    
                Thread.sleep(10);} 
                catch (InterruptedException e) {e.printStackTrace();}
            }
            System.exit(0);
        });
        mainGame.start();
    }
}
