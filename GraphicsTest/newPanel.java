package GraphicsTest;

import java.awt.*;
import javax.swing.*;

public class newPanel extends JPanel{
    final Color green = new Color(84,140,78);
    final Color yellow = new Color(181,159,59);
    final Color black = new Color(18,18,19);
    final Color lightGrey = new Color(129,131,132);
    final Color darkGrey = new Color(58,58,60,255);
    final Color white = new Color(248,248,248);

    newPanel(){
        this.setPreferredSize(new Dimension(500,500));
    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(black);
        g2D.fillRect(0,0,500,500);
        g2D.setColor(white);
        Font wordleFont = new Font("Helvetica",0,75);
        g2D.setFont(wordleFont);
        g2D.drawString("WORDLE",100,100);
        g2D.setColor(darkGrey);
        g2D.fillRect(50,120,400,330);
    }
    
}
