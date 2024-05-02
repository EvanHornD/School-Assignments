package textScaling;
import java.awt.*;
import javax.swing.*;

public class textPanel extends JPanel {
    Dimension panelSize;
    double[] scale = {1.,1.};
    String text = "test";
    String textFont = "Arial";
    int textSize = 70;
    int textX = 0;
    int textY = 0;

    public Dimension getSize(){
        return this.panelSize;
    }

    public void updateScale(double[] scaleIn){
        this.scale = scaleIn;
    }

    public void setText(String textIn){
        this.text = textIn;
    }

    public void setFont(String textFontIn){
        this.textFont = textFontIn;
    }

    public void setSize(int textSizeIn){
        this.textSize = textSizeIn;
    }

    public void setTextX(int textXIn){
        this.textX = textXIn;
    }

    public void setTextY(int textYIn){
        this.textY = textYIn;
    }

    public void setText(String textIn, String textFontIn, int textSizeIn, int textXIn, int textYIn){
        this.text = textIn;
        this.textFont = textFontIn;
        this.textSize = textSizeIn;
        this.textX = textXIn;
        this.textY = textYIn;
    }

    textPanel(Dimension dimensions){
        this.setPreferredSize(dimensions);
        panelSize=dimensions;
        this.setFocusable(true);
        this.requestFocus();
    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.DARK_GRAY);
        g2D.fillRect(0,0,1920,1080);
        int textFontSize = (int)(textSize);
        Font newTextFont = new Font(textFont, Font.PLAIN, textFontSize);
        g2D.setFont(newTextFont);
        g2D.scale(scale[0],scale[1]);
        g2D.setColor(Color.LIGHT_GRAY);
        g2D.drawString(text, textX, (int)(textY*1/scale[1]));
        g2D.scale(scale[0]*-1,scale[1]*-1);
        g2D.setColor(Color.LIGHT_GRAY);
        g2D.drawString(text, textX+200, textY);
        g2D.dispose();
    }

    public void triggerRepaint() {
        SwingUtilities.invokeLater(() -> repaint());
    }
}
