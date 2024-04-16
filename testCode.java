import java.awt.*;
import javax.swing.*;

public class testCode {

    // gets your screen width and height
    public static int[] getScreenDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int[] dimensions = {(int)screenSize.getWidth(),(int)screenSize.getHeight()};
        return (dimensions);
    }

    // initializing the content of the window that is used else where
    static JFrame window;
    public static void createWindow(int[] screenDimensions) {
        // initializing all of the variables
        window = new JFrame("mazeGame");
        window.setSize(screenDimensions[0],screenDimensions[1]-40);
        window.setLocation(0, 0);
        window.setFocusable(true);
        window.requestFocus();
        window.setResizable(false);
        window.setUndecorated(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setAlwaysOnTop(false);

    }

    public static void closeWindow() {
        window.dispose();
    }

    public static void main(String[] args) {
        int[] screenDimensions = getScreenDimensions();
        createWindow(screenDimensions);
 
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
