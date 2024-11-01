package Lab7;
import java.awt.Graphics2D;

import Lab7.Rendering.*;

class Concert extends RenderableEntity {
	private int id = 0;
	private String Artist;
	private int capacity;

	/*
	 * 24-hour format without a
	 * colon, 8:00 am is 800, 5:30 pm
	 * is 1730
	 */
	private int startTime;
	private int endTime;
	static int[] screenDimensions;
	static double screenRatio;
	private int targetY = 0;
	private int lastY = 0;
	private int currentY = 0;
	private LabeledShapeEntity[] concertInfo = new LabeledShapeEntity[4];

	Concert(String artist, int c, int s, int e, int[] Dimensions) {
		this.Artist = artist;
		this.capacity = c;
		this.startTime = s;
		this.endTime = e;
		screenDimensions = Dimensions;
		screenRatio = screenDimensions[0]/(1920.);
		int textSize = (int)(40*screenRatio);
		concertInfo[0] = createEntity(textSize, artist,0, 5);
		concertInfo[1] = createEntity(textSize, c+"",5, 2);
		concertInfo[2] = createEntity(textSize, s+" - "+e,7, 3);
		concertInfo[3] = createEntity(textSize, endTime-startTime+"",10, 2);
	}

	LabeledShapeEntity createEntity(int textSize, String text, int x, int width){
		return new LabeledShapeEntity(new ShapeEntity("Rectangle", new int[]{x*(screenDimensions[0]/12),targetY}, new int[]{width*(screenDimensions[0]/12),screenDimensions[1]/16}), text, textSize, "Left");
	}

	void moveEntities(){
		int changeInY = currentY-lastY;
		for (LabeledShapeEntity entity : concertInfo) {
			entity.moveEntity(0, changeInY);
		}
	}

	public void highlightEntity(int i, boolean isHighlighted){
		if(!isHighlighted){
			concertInfo[i].getShape().setColor(concertInfo[i].getShape().defaultOutline);
			concertInfo[i].getShape().setOutLineColor(concertInfo[i].getShape().highlightedOutLine);
		} else {
			concertInfo[i].getShape().setColor(concertInfo[i].getShape().defaultColor);
			concertInfo[i].getShape().setOutLineColor(concertInfo[i].getShape().defaultOutline);
		}
	}

	public int getLastY(){
		return this.lastY;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getArtist() {
		return Artist;
	}

	public int getDuration() {
        return endTime - startTime;
    }

	public int getID(){
		return id;
	}

	public void setID(int ID){
		id = ID;
		targetY = id*screenDimensions[1]/32;
	}

	@Override
	public void render(Graphics2D g2d) {
		lastY = currentY;
		currentY = currentY+((targetY-currentY)/2);
		moveEntities();
		for (LabeledShapeEntity labeledShapeEntity : concertInfo) {
			labeledShapeEntity.render(g2d);
		}
	}
}