package Lab7.Rendering;
import java.awt.Color;
import java.awt.Graphics2D;

public class ShapeEntity extends RenderableEntity {
    public Color defaultColor = new Color(31,31,31);
    private String shape;
    private int[] coordinates;
    private int[] dimensions;
    private Color color;

    public ShapeEntity(String shape, int[] dimensions, int layer) {

        this.shape = shape;
        this.coordinates = new int[]{0, 0};
        this.dimensions = dimensions;
        this.color = defaultColor;
    }

    public ShapeEntity(String shape, int[] coordinates, int[] dimensions, Color color, int layer) {
        this.shape = shape;
        this.coordinates = coordinates;
        this.dimensions = dimensions;
        this.color = color;
    }

    public ShapeEntity(String shape, int[] coordinates, int[] dimensions) {
        this.shape = shape;
        this.coordinates = coordinates;
        this.dimensions = dimensions;
        this.color = defaultColor;
    }

    public ShapeEntity(String shape, int[] coordinates, int[] dimensions, Color color) {
        this.shape = shape;
        this.coordinates = coordinates;
        this.dimensions = dimensions;
        this.color = color;
    }

    public String getShape() {
        return shape;
    }

    public int[] getCoords() {
        return coordinates;
    }

    public int[] getDimensions() {
        return dimensions;
    }

    public Color getColor() {
        return color;
    }

    public void setCoords(int[] coordinates){
        this.coordinates = coordinates;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(color);
        switch (shape) {
            case "Rectangle":
                g2d.fillRect(coordinates[0], coordinates[1], dimensions[0], dimensions[1]);
                break;
            default:
                break;
        }
    }
}

