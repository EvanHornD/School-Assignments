package textScaling;

import java.awt.*;

import javax.swing.*;

public class textFrame extends JFrame {
    textPanel panel;

    public void setStartingValues(){
        this.setTitle("");
        this.setLocation(0, 0);
        this.setResizable(true);
        this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    textFrame(int width, int height){
        this.setPreferredSize(new Dimension(width,height));
        setStartingValues();
        panel = new textPanel(new Dimension(width,height));
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    public textPanel getPanel(){
        return(panel);
    }
}
