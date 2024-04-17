package GraphicsTest;

import java.awt.*;
import javax.swing.*;

public class newPanel extends JPanel{

    newPanel(){
        this.setPreferredSize(new Dimension(500,500));
    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawLine(0,0,500,500);
    }
    
}
