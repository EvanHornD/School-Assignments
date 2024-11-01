package Lab7.Rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;


public class TextEntity extends RenderableEntity {
    private String text;
    private Font font;
    private int[] coordinates;
    private int[] shapeDimensions;
    private String alignment;

    public TextEntity(String text,int textSize,int[] coordinates, String alignment, int[] dimensions) {
        this.text = text;
        this.font = new Font("Arial", 0, textSize);
        this.coordinates = coordinates;
        this.alignment = alignment;
        this.shapeDimensions = dimensions;
    }

    public int getStringWidth(Graphics2D g2d) {
        FontMetrics metrics = g2d.getFontMetrics(font);
        return metrics.stringWidth(text);
    }

    public int getStringHeight(Graphics2D g2d){
        FontMetrics metrics = g2d.getFontMetrics(font);
        return metrics.getHeight();
    }

    public int getStringSize(Graphics2D g2d){
        FontMetrics metrics = g2d.getFontMetrics(font);
        return metrics.charWidth('W');
    }

    public String getText() {
        return text;
    }

    public Font getFont() {
        return font;
    }

    public int[] getCoords() {
        return coordinates;
    }

    public void moveText(int x, int y){
        coordinates[0] += x;
        coordinates[1] += y;
    }

    public void changeText(String text){
        this.text = text;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setColor(Color.LIGHT_GRAY);
        int width = 0;
        int height = 0;
        int xCentered = 0;
        int yCentered = 0;

        switch (alignment) {
            case "Centered":
            width = getStringWidth(g2d);
            height = getStringHeight(g2d);
            xCentered = (coordinates[0]+shapeDimensions[0]/2-width/2);
            yCentered = (coordinates[1]+(shapeDimensions[1]/2)+height/3);
            g2d.drawString(text, xCentered, yCentered);
            break;

            case "Left":
            height = getStringHeight(g2d);
            yCentered = (coordinates[1]+(shapeDimensions[1]/2)+height/3);
            g2d.drawString(text, coordinates[0]+getStringSize(g2d), yCentered);
            break;
        
            default:
            g2d.drawString(text, coordinates[0], coordinates[1]);
            break;
        }
    }
}

