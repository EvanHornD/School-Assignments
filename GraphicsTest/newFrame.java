package GraphicsTest;

import java.awt.*;

import javax.swing.*;

public class newFrame extends JFrame {
    newPanel panel;

    public void setStartingValues(){
        this.setTitle("");
        this.setLocation(0, 0);
        this.setResizable(true);
        this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    newFrame(){
        this.setPreferredSize(new Dimension(0,0));
        setStartingValues();
        panel = new newPanel();
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    newFrame(int width, int height,double[] scale){
        this.setPreferredSize(new Dimension(width,height));
        setStartingValues();
        panel = new newPanel(new Dimension(width,height),scale);
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    public newPanel getPanel(){
        return(panel);
    }
}
