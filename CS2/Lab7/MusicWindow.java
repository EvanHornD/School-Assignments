package Lab7;

import javax.swing.JFrame;
import java.awt.*;

public class MusicWindow {
        
    private JFrame frame;
    MusicPanel panel;

    public MusicWindow() {
        initialize();
    }

    public static Dimension getScreenDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (screenSize);
    }

    public void initialize(){
        frame = new JFrame();
        this.frame.setTitle("Game");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension screenDimensions = getScreenDimensions();
        int width = (int)screenDimensions.getWidth();
        int height = (int)screenDimensions.getHeight();

        this.frame.setSize(width, height);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);
        this.frame.setVisible(true);

        panel = new MusicPanel(screenDimensions);
        this.frame.add(panel);
    }
}
