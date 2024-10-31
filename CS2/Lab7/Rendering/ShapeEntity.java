package Lab7.Rendering;
import java.awt.Color;
import java.awt.Graphics2D;

public class ShapeEntity extends RenderableEntity {
    public Color defaultColor = new Color(32,32,32);
    public Color defaultOutline = new Color(42,42,42);
    private String shape;
    private int[] coordinates;
    private int[] dimensions;
    private Color color;
    private Color outLineColor;

    public ShapeEntity(String shape, int[] dimensions, int layer) {
        this.shape = shape;
        this.coordinates = new int[]{0, 0};
        this.dimensions = dimensions;
        this.color = defaultColor;
        this.outLineColor = defaultOutline;
    }

    public ShapeEntity(String shape, int[] coordinates, int[] dimensions, Color color, int layer) {
        this.shape = shape;
        this.coordinates = coordinates;
        this.dimensions = dimensions;
        this.color = color;
        this.outLineColor = defaultOutline;
    }

    public ShapeEntity(String shape, int[] coordinates, int[] dimensions) {
        this.shape = shape;
        this.coordinates = coordinates;
        this.dimensions = dimensions;
        this.color = defaultColor;
        this.outLineColor = defaultOutline;
    }

    public ShapeEntity(String shape, int[] coordinates, int[] dimensions, Color color) {
        this.shape = shape;
        this.coordinates = coordinates;
        this.dimensions = dimensions;
        this.color = color;
        this.outLineColor = defaultOutline;
    }
    public ShapeEntity(Color color) {
        this.shape = "Rectangle";
        this.coordinates = new int[]{0,0};
        this.dimensions = new int[]{20000,20000};
        this.color = color;
        this.outLineColor = color;
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

    public void moveShape(int x, int y){
        coordinates[0] += x;
        coordinates[1] += y;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(color);
        switch (shape) {
            case "Rectangle":
                g2d.fillRect(coordinates[0], coordinates[1], dimensions[0], dimensions[1]);
                g2d.setColor(outLineColor);
                g2d.drawRect(coordinates[0], coordinates[1], dimensions[0], dimensions[1]);
                break;
            default:
                break;
        }
    }
}

