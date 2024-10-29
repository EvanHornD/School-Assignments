package Lab7.Rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;


public class TextEntity extends RenderableEntity {
    private String text;
    private Font font;
    private int[] coordinates;
    private String alignment;

    public TextEntity(String text, int layer) {
        this.text = text;
        this.font = new Font("Arial", 0, 20);
        this.coordinates = new int[]{100, 100};
        this.alignment = "Left";
    }

    public TextEntity(String text,int[] coordinates, int layer, String alignment) {
        this.text = text;
        this.font = new Font("Arial", 0, 20);
        this.coordinates = coordinates;
        this.alignment = alignment;
    }

    public TextEntity(String text,int textSize,int[] coordinates, String alignment) {
        this.text = text;
        this.font = new Font("Arial", 0, textSize);
        this.coordinates = coordinates;
        this.alignment = alignment;
    }

    public TextEntity(String text,int[] coordinates, String alignment) {
        this.text = text;
        this.font = new Font("Arial", 0, 20);
        this.coordinates = coordinates;
        this.alignment = alignment;
    }

    public int getStringWidth(Graphics2D g2d) {
        FontMetrics metrics = g2d.getFontMetrics(font);
        return metrics.stringWidth(text);
    }

    public int getStringHeight(Graphics2D g2d){
        FontMetrics metrics = g2d.getFontMetrics(font);
        return metrics.getHeight();
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

    @Override
    public void render(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setColor(Color.LIGHT_GRAY);

        switch (alignment) {
            case "Centered":
            int width = getStringWidth(g2d);
            int height = getStringHeight(g2d);
            int xCentered = (coordinates[0]-width/2);
            int yCentered = (coordinates[1]+height/2);
            g2d.drawString(text, xCentered, yCentered);
            break;
        
            default:
            g2d.drawString(text, coordinates[0], coordinates[1]);
            break;
        }
    }
}

