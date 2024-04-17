package GraphicsTest;

import java.awt.*;
import javax.swing.*;

public class testCode {

    // gets your screen width and height
    public static int[] getScreenDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int[] dimensions = {(int)screenSize.getWidth(),(int)screenSize.getHeight()};
        return (dimensions);
    }

    public static void main(String[] args) {
        int[] screenDimensions = getScreenDimensions();
        new newFrame();
 
        Thread mainGame = new Thread(() -> {while(true){
                try {Thread.sleep(10);} 
                catch (InterruptedException e) {e.printStackTrace();}
            }
            //closeWindow();
            //System.exit(0);
        });
        mainGame.start();
    }
}
