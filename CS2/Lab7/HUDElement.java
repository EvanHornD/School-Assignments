package Lab7;

import java.awt.Graphics2D;

import Lab7.Rendering.*;

class HUDElement extends LabeledShapeEntity {

    public HUDElement(ShapeEntity shape, String text, int textSize, String textAlignment) {
        super(shape, text, textSize, textAlignment);
    }

    boolean isWithinBounds(int[] coords) {
        int[] shapeCoords = getShape().getCoords();       
        int[] dimensions = getShape().getDimensions(); 

        int x = shapeCoords[0];
        int y = shapeCoords[1];
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
