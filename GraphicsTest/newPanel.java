package GraphicsTest;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class newPanel extends JPanel implements KeyListener{
    int[] keyCodes = {};
    Dimension panelSize;
    wordleRectangle[][] rectangles = {};
    String lastKeyPressed = "";
    double[] scale = {1.,1.};

    public void getkeyCodes(){
        System.out.println(Arrays.toString(keyCodes));
    }

    public String getLastKeyPressed(){
        return this.lastKeyPressed;
    }

    public Dimension getSize(){
        return this.panelSize;
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

    newPanel(Dimension dimensions,double[] scaleIn){
        this.scale = scaleIn;
        this.setPreferredSize(dimensions);
        panelSize=dimensions;
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        keyCodes = createKeyCodesArray();
    }

    public void drawRectangleArray(Graphics g,wordleRectangle[][] rectangles){
        Graphics2D graphicsPen = (Graphics2D) g;
        for (int i = 0; i < rectangles.length; i++) {
            for (int ii = 0; ii < rectangles[i].length; ii++) {
                wordleRectangle curRect = rectangles[i][ii];
                Color rectColor = curRect.getFillColor();
                int rectX, rectY, rectWidth, rectHeight;
                if(curRect.getAnimation()==null){
                    rectX = (int)(curRect.getX()*scale[0]);
                    rectY = (int)(curRect.getY()*scale[1]);
                } else {
                    int animXOffset = curRect.getAnimation().getXOffset();
                    int animYOffset = curRect.getAnimation().getYOffset();
                    double animXScale = curRect.getAnimation().getXScale();
                    double animYScale = curRect.getAnimation().getYScale();
                    rectX = (int)(((curRect.getX()+animXOffset-((animXScale-1)/2*curRect.getWidth()))*1/animXScale)*scale[0]);
                    rectY = (int)(((curRect.getY()+animYOffset-((animYScale-1)/2*curRect.getHeight()))*1/animYScale)*scale[1]);
                    graphicsPen = (Graphics2D) g.create();
                    graphicsPen.scale(animXScale, animYScale);
                }
                rectWidth = (int)(curRect.getWidth()*scale[0]);
                rectHeight = (int)(curRect.getHeight()*scale[1]);
                int textSize = (int)(curRect.getTextSize()*scale[1]);
                String text = curRect.getText();
                String textFont = curRect.getTextFont();
                Font newTextFont = new Font(textFont,0,textSize);
                if(rectWidth==0&&rectHeight==0&&!(text.equals(""))){
                    rectWidth = graphicsPen.getFontMetrics(newTextFont).stringWidth(text)+(int)(40*scale[0]);
                    rectHeight = graphicsPen.getFontMetrics(newTextFont).getHeight()+(int)(40*scale[1]);
                    rectX-=(rectWidth/2);
                    rectY-=(rectHeight/2);
                }
                if(!(rectColor==Color.WHITE)){
                    graphicsPen.setColor(rectColor);
                    graphicsPen.fillRect(rectX,rectY,rectWidth,rectHeight);
                }
                if(!text.equals("")){
                    int textWidth = graphicsPen.getFontMetrics(newTextFont).stringWidth(text);
                    int textheight = graphicsPen.getFontMetrics(newTextFont).getHeight();
                    graphicsPen.setFont(newTextFont);
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
                if(curRect.getAnimation()!=null){
                    curRect.getAnimation().increaseFrameCounter();
                    if(curRect.getAnimation().getAnimationType()==""){
                        curRect.removeAnimation();
                    }
                    graphicsPen = (Graphics2D) g.create();
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
        drawRectangleArray(g, rectangles);
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
        int[] keyCodes = new int[29];
        for (int i = 0; i < keyCodes.length; i++) {
            if(i==0){keyCodes[i]=10;}
            else if(i==1){keyCodes[i]=27;}
            else if(i==2){keyCodes[i]=8;}
            else{keyCodes[i] = 62+i;}
        }
        return keyCodes;
    }
}
