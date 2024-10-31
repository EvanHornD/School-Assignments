package Lab7.Rendering;

import java.awt.Graphics2D;

public class LabeledShapeEntity extends RenderableEntity {

    private ShapeEntity shape;
    private TextEntity text;

    public LabeledShapeEntity(ShapeEntity shape, String text,int textSize, String textAlignment){
        this.shape = shape;
        int[] coords = shape.getCoords();
        int[] dimensions = shape.getDimensions();
        this.text = new TextEntity(text,textSize,coords,textAlignment,dimensions);
    }

    public void moveEntity(int x, int y){
        text.moveText(x, y);
        shape.moveShape(x, y);
    }

    @Override
    public void render(Graphics2D g2d){
        shape.render(g2d);
        text.render(g2d);
    }
}
