package Lab7.UI;
import Lab7.Rendering.*;
import java.awt.Graphics2D;

public class Concert extends RenderableEntity {
	static int[] screenDimensions;
	static double screenRatio;

	private int id = 0;
	private String Artist;
	private int capacity;
	private int startTime;
	private int endTime;
	private double targetY = 0;
	private double lastY = 0;
	private double currentY = 0;
	private LabeledShapeEntity[] concertInfo = new LabeledShapeEntity[4];

	public Concert(String artist, int c, int s, int e, int[] Dimensions) {
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

	public Concert(Concert c) {
		this.currentY = c.getCurrentY();
		this.targetY = c.getTargetY();
		this.lastY = c.getLastY();
		this.id = c.getID();
		this.Artist = c.getArtist();
		this.capacity = c.getCapacity();
		this.startTime = c.getStartTime();
		this.endTime = c.getEndTime();
		this.concertInfo = c.getEntities();
	}

	LabeledShapeEntity createEntity(int textSize, String text, int x, int width){
		return new LabeledShapeEntity(new ShapeEntity("Rectangle", new double[]{x*(screenDimensions[0]/12),targetY}, new int[]{width*(screenDimensions[0]/12),screenDimensions[1]/16}), text, textSize, "Left");
	}

	public LabeledShapeEntity[] getEntities(){
		return concertInfo;
	}

	void moveEntities(){
		double changeInY = currentY-lastY;
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

	public double getCurrentY() {
        return currentY;
    }

	public double getTargetY() {
        return targetY;
    }

	public double getLastY(){
		return lastY;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getArtist() {
		return Artist;
	}

	public int getStartTime() {
        return startTime;
    }

	public int getEndTime() {
        return endTime;
    }

	public int getDuration() {
        return endTime - startTime;
    }

	public int getID(){
		return id;
	}

	public void setID(int ID){
		id = ID;
		targetY = (int)(id*screenDimensions[1]/32.);
	}

	public void moveToFinalPos(){
		move(4.);
	}

	public void move(Double speed){
		lastY = currentY;
		currentY = currentY+((targetY-currentY)/(4*(1/speed)));
		if(Math.abs(targetY-currentY)<1){
			currentY = targetY;
		}
		moveEntities();
	}

	@Override
	public void render(Graphics2D g2d) {
		for (LabeledShapeEntity labeledShapeEntity : concertInfo) {
			labeledShapeEntity.render(g2d);
		}
	}

	@Override
	public String toString() {
		
		return String.format("| %-20s | %-7s | %-6s | %-4s |", this.getArtist(),this.getCapacity(),this.getDuration(),this.getID());
	}
}