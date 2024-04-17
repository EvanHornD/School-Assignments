package GraphicsTest;

import java.awt.*;
import javax.swing.*;

public class newFrame extends JFrame {
    
    newPanel panel;
    newFrame(){
        this.setTitle("");
        this.setLocation(0, 0);
        this.setFocusable(true);
        this.requestFocus();
        this.setResizable(true);
        this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        panel = new newPanel();
        this.add(panel);
        this.pack();
        
    }

}
