package GraphicsTest;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class newPanel extends JPanel implements KeyListener{
    int[] keyCodes = {};
    Dimension panelSize;
    wordleRectangle[][] rectangles = {};
    String lastKeyPressed = "";

    public String getLastKeyPressed(){
        return this.lastKeyPressed;
    }

    public void resetLastKeyPressed(){
        this.lastKeyPressed="";
    }

    public wordleRectangle[][] getRectangles() {
        return this.rectangles;
    }

    public void updateRectangleText(int row,int collumn,String character){
        rectangles[1][row*5+collumn].setText(character);
    }

    public void updateRectangleColor(int row,int collumn,String character){
        rectangles[1][row*5+collumn].setText(character);
    }

    newPanel(){
        Dimension dimensions =new Dimension(0,0);
        this.setPreferredSize(dimensions);
        panelSize=dimensions;
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        keyCodes = createKeyCodesArray();
    }

    newPanel(Dimension dimensions){
        this.setPreferredSize(dimensions);
        panelSize=dimensions;
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        keyCodes = createKeyCodesArray();
    }

    public void drawRectangleArray(Graphics2D graphicsPen,wordleRectangle[][] rectangles){
        for (int i = 0; i < rectangles.length; i++) {
            for (int ii = 0; ii < rectangles[i].length; ii++) {
                Color rectColor = rectangles[i][ii].getFillColor();
                int rectX = rectangles[i][ii].getX();
                int rectY = rectangles[i][ii].getY();
                int rectWidth = rectangles[i][ii].getWidth();
                int rectHeight = rectangles[i][ii].getHeight();
                if(!(rectColor==Color.WHITE)){
                    graphicsPen.setColor(rectColor);
                    graphicsPen.fillRect(rectX,rectY,rectWidth,rectHeight);
                }
                String text = rectangles[i][ii].getText();
                if(!text.equals("")){
                    Font textFont = rectangles[i][ii].getTextFont();
                    int textWidth = graphicsPen.getFontMetrics(textFont).stringWidth(text);
                    int textheight = graphicsPen.getFontMetrics(textFont).getHeight();
                    graphicsPen.setFont(textFont);
                    graphicsPen.setColor(rectangles[i][ii].getTextColor());
                    graphicsPen.drawString(text,rectX+((rectWidth-textWidth)/2),rectY+((rectHeight+(3*(textheight))/4)/2));
                }
                int rectOutLineThickness = rectangles[i][ii].getOutLineThickness();
                if(!(rectOutLineThickness==0)){
                    Color rectOutLineColor = rectangles[i][ii].getOutLineColor();
                    graphicsPen.setColor(rectOutLineColor);
                    graphicsPen.setStroke(new BasicStroke(rectOutLineThickness,1,0));
                    graphicsPen.drawRect(rectX,rectY,rectWidth,rectHeight);
                }
            }
        }
    }

    public void addRectangle(wordleRectangle rect,int layer){
        if(this.rectangles.length-1<layer){
            this.addGraphicsLayers(layer-(this.rectangles.length-1));
        }
        wordleRectangle[][] new2DRectangleArray = new wordleRectangle[this.rectangles.length][];
        for (int i = 0; i < this.rectangles.length; i++) {
            int arrayLength = this.rectangles[i].length;
            wordleRectangle[] newRectangleArray;
            if(i==layer){
                newRectangleArray = new wordleRectangle[arrayLength+1];
                for (int j = 0; j < arrayLength; j++) {
                    newRectangleArray[j] = this.rectangles[i][j]; 
                }
                newRectangleArray[arrayLength] = rect;
            }
            else{
                newRectangleArray = this.rectangles[i];
            }
            new2DRectangleArray[i] = newRectangleArray;
        }
        this.rectangles = new2DRectangleArray;
    }

    public void addGraphicsLayers(int numberOfLayers){
        wordleRectangle[][] new2DRectangleArray = new wordleRectangle[this.rectangles.length+numberOfLayers][];
        for (int i = 0; i < new2DRectangleArray.length; i++) {
            if(i<rectangles.length){
                wordleRectangle[] newRectangleArray = this.rectangles[i];
                new2DRectangleArray[i] = newRectangleArray;
            }else{
                wordleRectangle[] newRectangleArray = {};
                new2DRectangleArray[i] = newRectangleArray;
            }
        }
        this.rectangles = new2DRectangleArray;
    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        drawRectangleArray(g2D, rectangles);
    }

    public void triggerRepaint() {
        SwingUtilities.invokeLater(() -> repaint());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //keyTyped = Invoked when a key is typed. Uses KeyChar, char output
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //keyPressed = Invoked when a physical key is pressed down. Uses KeyCode, int output
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //keyReleased = called whenever a button is released
        if(lastKeyPressed.equals("")){
            for (int i : keyCodes) {
                if(i==e.getKeyCode()){
                    lastKeyPressed = java.awt.event.KeyEvent.getKeyText(e.getKeyCode());
                }
            }
        }
    }

    public int[] createKeyCodesArray(){
        int[] keyCodes = new int[28];
        for (int i = 0; i < keyCodes.length; i++) {
            if(i==0){keyCodes[i]=10;}
            else if(i==1){keyCodes[i]=27;}
            else if(i==2){keyCodes[i]=8;}
            else{keyCodes[i] = 62+i;}
        }
        return keyCodes;
    }
}
