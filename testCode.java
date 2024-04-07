import java.io.File;
import java.util.Arrays;
import java.awt.*;

public class testCode {
    public static int[] getScreenDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int[] dimensions = {(int)screenSize.getWidth()/7,(int)screenSize.getHeight()/14};
        return (dimensions);
    }

    public static void clearConsole() {
        // Clearing the console by printing a newline character multiple times
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        String screenWidth ="";
        int[] dimensions = getScreenDimensions();
        for(int i=0; i<dimensions[0]-3;i++){
            if(i==0 ||i==dimensions[0]-4){screenWidth+="|";}
            else{screenWidth+=" ";}
        }
        clearConsole();
        String screenEdge ="";
        for(int i=0; i<dimensions[0]-3;i++){
            screenEdge+="-";
        }
        System.out.println(screenEdge);
        for(int i=0; i<dimensions[1]-10;i++){
            System.out.println(screenWidth);
        }
        System.out.println(screenEdge);
    }
}
