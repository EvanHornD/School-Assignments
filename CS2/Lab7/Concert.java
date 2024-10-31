package Lab7;

import java.awt.Graphics2D;

import Lab7.Rendering.*;

class Concert extends RenderableEntity {
	private static int id;
	String Artist;
	private int capacity;

	/*
	 * 24-hour format without a
	 * colon, 8:00 am is 800, 5:30 pm
	 * is 1730
	 */
	private int startTime;
	private int endTime;
	int orderInList;
	static int[] screenDimensions;
	private static int targetY;
	private static int lastY;
	private static int currentY;
	static LabeledShapeEntity[] concertInfo = new LabeledShapeEntity[4];

	Concert(int i, String artist, int c, int s, int e, int[] Dimensions) {
		id = i;
		Artist = artist;
		capacity = c;
		startTime = s;
		endTime = e;
		screenDimensions = Dimensions;
		targetY = id*screenDimensions[1]/16;
		currentY = targetY;
		int textSize = 40*(screenDimensions[0]/(1920));
		concertInfo[0] = createEntity(textSize, artist,0, 5);
		concertInfo[1] = createEntity(textSize, c+"",5, 3);
		concertInfo[2] = createEntity(textSize, s+" - "+e,8, 2);
		concertInfo[3] = createEntity(textSize, endTime-startTime+"",10, 2);
	}

	static LabeledShapeEntity createEntity(int textSize, String text, int x, int width){
		return new LabeledShapeEntity(new ShapeEntity("Rectangle", new int[]{x*(screenDimensions[0]/12),targetY}, new int[]{width*(screenDimensions[0]/12),screenDimensions[1]/16}), text, textSize, "Left");
	}

	static void moveEntities(){
		int changeInY = currentY-lastY;
		for (LabeledShapeEntity entity : concertInfo) {
			entity.moveEntity(0, changeInY);
		}
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