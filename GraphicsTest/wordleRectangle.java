package GraphicsTest;
import java.awt.*;

public class wordleRectangle {
    Color fillColor = Color.WHITE;
    int xCoordinate = 0;
    int yCoordinate = 0;
    int width = 0;
    int height = 0;
    String text = "";
    Color textColor = Color.WHITE;
    Font textFont = new Font("ariel",0,0);

    public Color getFillColor() {
        return fillColor;
    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Font getTextFont() {
        return textFont;
    }

    
    wordleRectangle(){}

    wordleRectangle(Dimension dimensions, Color color){
        this.width = (int)dimensions.getWidth();
        this.height = (int)dimensions.getHeight();
        fillColor = color;
    }

}
