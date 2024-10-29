package Lab7.Rendering;

import java.awt.Graphics2D;

public class LabeledShapeEntity extends RenderableEntity {

    private ShapeEntity shape;
    private TextEntity text;

    public LabeledShapeEntity(ShapeEntity shape, String text,int textSize, int layer){
        this.shape = shape;
        int[] coords = shape.getCoords();
        int[] dimensions = shape.getDimensions();
        this.text = new TextEntity(text,textSize,new int[]{coords[0]+dimensions[0]/2,coords[1]+dimensions[1]/2},"Centered");
    }

    @Override
    public void render(Graphics2D g2d){
        shape.render(g2d);
        text.render(g2d);
    }
}
