package Lab7.UI;

import Lab7.Rendering.*;
import java.awt.Graphics2D;

public class HUDElement extends LabeledShapeEntity {

    public HUDElement(ShapeEntity shape, String text, int textSize, String textAlignment) {
        super(shape, text, textSize, textAlignment);
    }

    public boolean isWithinBounds(int[] coords) {
        double[] shapeCoords = getShape().getCoords();       
        int[] dimensions = getShape().getDimensions(); 

        double x = shapeCoords[0];
        double y = shapeCoords[1];
        int width = dimensions[0];
        int height = dimensions[1];

        return (coords[0] >= x && coords[0] <= x + width && coords[1] >= y && coords[1] <= y + height);
    }

	public void highlightEntity(boolean isHighlighted){
		if(!isHighlighted){
			getShape().setColor(getShape().defaultOutline);
            getShape().setOutLineColor(getShape().highlightedOutLine);
		} else {
			getShape().setColor(getShape().defaultColor);
            getShape().setOutLineColor(getShape().defaultOutline);
		}
	}

    public void changeText(String text){
        getText().changeText(text);
    }

    @Override
	public void render(Graphics2D g2d) {
        super.render(g2d);
	}
}
