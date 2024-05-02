package textScaling;
import java.awt.*;

public class textStretchTest {
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

    public static void main(String[] args) {
        Dimension screenDimensions = getScreenDimensions();
        int width = (int)screenDimensions.getWidth();
        int height = (int)screenDimensions.getHeight();
        textFrame frame = new textFrame(width, height-30);
        textPanel panel = frame.getPanel();
        double textVerticalScale = 1.;

        panel.setText("TEST","Franklin Gothic",100,100,200);
        Thread mainGame = new Thread(() -> {
            double counter = 0;
            boolean increasing = true;
            try {
                while(true){
                    panel.updateScale(new double[]{1.,textVerticalScale*Math.cos(counter)});
                    if(counter>=3.14){
                        increasing = false;
                    }
                    if(counter<0.1){
                        increasing = true;
                    }
                    if(increasing){
                        counter+=.01;
                    }else{
                        counter-=.01;
                    }
                    panel.triggerRepaint();
                    Thread.sleep(10);
                }
            } catch (Exception e) {
            }
        });
        mainGame.start();
    }
}
