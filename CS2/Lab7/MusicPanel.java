package Lab7;

import Lab7.Rendering.*;
import Lab7.UI.*;
import Lab7.userInput.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MusicPanel extends JPanel {

    //#region   Class attributes
    static final String[] sortingTypes = new String[]{"Selection","Insertion","Merge"};
    static final String[] sortingCategories = new String[]{"Artist","Capacity","Null","Duration","Null","Null"};
    static final int[][] collisions = new int[][]{{0,1,3},{0,1,3},{1,3}};
    gameTimer gameTimer;
    static KeyBindsManager keyBinds;
    static GraphicsRenderer renderer;
    static Concert[] concerts = new Concert[10];
    static HUDElement[] header = new HUDElement[6];
    static int collision = -1;
    static int sortingType = 0;
    static int panelWidth;
    static int panelHeight;
    static double screenRatio;
    ShapeEntity backGround = new ShapeEntity(new Color(24,24,24));
    //#endregion

    @SuppressWarnings("OverridableMethodCallInConstructor")
    MusicPanel(Dimension dimensions){
        this.setPreferredSize(dimensions);
        this.setFocusable(true);
        this.requestFocus();
        keyBinds = new KeyBindsManager(this);
        renderer = new GraphicsRenderer(this);
        gameTimer = new gameTimer();
        panelWidth = (int)dimensions.getWidth();
        panelHeight = (int)dimensions.getHeight();
        screenRatio = panelWidth/(1920.);
        createConcertArray();
        createHud();
        startGameTimer();
    }

    // -------------
    // Start Game
    // -------------

    public void startGameTimer() {
        gameTimer.start(deltaTime -> {
            // Update game state
            runGameLoop(deltaTime);
            keyBinds.updateFrameInformation();

            // Trigger the repaint
            renderer.triggerRepaint();
        });
    }

    // -------------
    // Run Game Logic
    // -------------
    public static void runGameLoop(double dt){
        runButtonCollisions();
        Lab7_SortingAlgorithms.runSortingAlgorithms(concerts);
    }

    // -------------
    // Checks for collisions and then activates code based on the button pressed
    // -------------
    public static void runButtonCollisions(){
        // check collisions
        runHUDCollisions();
        // if there wasnt a collision then exit the method
        if(collision==-1){
            return;
        }
        Map<String, Integer> keyActions = keyBinds.getKeyActions();
        Map<String, Integer> keyFrames = keyBinds.getKeyFrames();

        // check if the user first frame of a mouse input is happening
        if(keyActions.get("Input")!=1|| keyFrames.get("Input")!=1){
            return;
        }

        // run collisions on extra menu buttons EX: the reset button is case 5
        switch (collision) {
            case 4:
                if(sortingType >= sortingTypes.length-1){
                    sortingType = 0;
                } else {
                    sortingType++;
                }
                header[4].changeText("Sort Type: "+sortingTypes[sortingType]);
            return;

            case 5:
                Lab7_SortingAlgorithms.finishSortingAlgorithms(concerts);
                createConcertArray();
            return;
        }

        // check if the collision is with an inactive sorting button
        if("Null".equals(sortingCategories[collision])){
            return;
        }

        //handle the case of trying to merge sort by artist
        if(sortingType==3&&collision==0){
            return;
        }

        //set the sorting categorys
        Lab7_SortingAlgorithms.setSortingType(sortingTypes[sortingType]+sortingCategories[collision], concerts);
    }

    // -------------
    // Create Concerts
    // -------------

    static void createConcertArray() {
        Random rand = new Random();
        String[] artists = {"Beyoncé", "Coldplay", "Drake", "Taylor Swift", "Kendrick Lamar", "Adele", "The Weeknd", 
        "Billie Eilish", "Post Malone", "Bruno Mars", "Sabrina Carpenter", "Chappell Roan", "Noah Khan"};
        for (int i = 0; i < 10; i++) {
            int capacity = rand.nextInt(100,1000) + 1;
            int startTime = rand.nextInt(2300);
            int endTime = startTime + rand.nextInt(2400 - startTime) + 1;
            String artist = artists[rand.nextInt(artists.length)];
            concerts[i] = new Concert(artist,capacity,startTime,endTime,new int[]{panelWidth,panelHeight});
            concerts[i].setID(i+1);
        }
    }

    // -------------
    // Create each HUD element
    // -------------

    static void createHud(){
        int gridX = panelWidth/12;
        int gridY = panelHeight/16;
        header[0] = new HUDElement(new ShapeEntity("Rectangle", new double[]{0*(gridX),1}, new int[]{5*(gridX),gridY}), "Artist", (int)(40*(screenRatio)), "Centered");
        header[1] = new HUDElement(new ShapeEntity("Rectangle", new double[]{5*(gridX),1}, new int[]{2*(gridX),gridY}), "Capacity", (int)(40*(screenRatio)), "Centered");
        header[2] = new HUDElement(new ShapeEntity("Rectangle", new double[]{7*(gridX),1}, new int[]{3*(gridX),gridY}), "StartTime-EndTime", (int)(40*(screenRatio)), "Centered");
        header[3] = new HUDElement(new ShapeEntity("Rectangle", new double[]{10*(gridX),1}, new int[]{2*(gridX),gridY}), "Duration", (int)(40*(screenRatio)), "Centered");
        header[4] = new HUDElement(new ShapeEntity("Rectangle", new double[]{panelWidth/4,panelHeight-4*panelHeight/21}, new int[]{panelWidth/2,panelHeight/16}), "Sort Type: "+sortingTypes[sortingType], (int)(60*(screenRatio)), "Centered");
        header[5] = new HUDElement(new ShapeEntity("Rectangle", new double[]{panelWidth/3,panelHeight-2*panelHeight/7}, new int[]{panelWidth/3,panelHeight/16}), "Reset", (int)(60*(screenRatio)), "Centered");
    }

    // -------------
    // Check for mouse collisions with HUD elements and highlight them accordingly
    // -------------

    static void runHUDCollisions(){
        // get the mouse coords
        int[] mouseCoords = keyBinds.mouseCoords;
        
        // check for collisions on every element
        for (int i = 0; i<header.length;i++) {

            // if the mouse collided with an element then exit the method
            if(checkCollision(i, mouseCoords)){
                return;
            }
        }
        // check if the mouse's last collision was with nothing
        if(collision == -1){
            return;
        }

        // if the last collision was with one of the header UI elements unhighlight it
        if(collision<4){
            highlightCollumn(true);
        }
        // if the last collision was with one of the other buttons unhighlight it
        if(collision>3){
            header[collision].highlightEntity(true);
        }
        // set the collision to be with nothing
        collision = -1;
    }

    public static boolean checkCollision(int i, int[] mouseCoords){
        // check if the mouse is hovering the button
        if(!header[i].isWithinBounds(mouseCoords)){
            return false;
        }

        // check if the mouse is hovering the same button it was before
        if(collision == i){
            return true;
        }

        // unhighlight the previous collumn
        if(collision>-1&&collision<4){
            highlightCollumn(true);
        }

        // update the collision variable
        collision = i;

        // highlight the UI element if it isn't a header element
        if(collision>4){
            header[collision].highlightEntity(false);
            return true;
        }

        // highlight the new collumn if it is a valid collumn for the current sorting type
        for (int j = 0; j < collisions[sortingType].length; j++) {
            if(collisions[sortingType][j]==collision){
                highlightCollumn(false);
            }
        }
        return true;
    }

    public static void highlightCollumn(boolean isHighlighted){
        // highlight each of the concerts cooresponding element
        for (Concert concert : concerts) {
            concert.highlightEntity(collision, isHighlighted);
        }
        // highlight the collumn header
        header[collision].highlightEntity(isHighlighted);
    }

    // -------------
    // calls each entities render method
    // -------------

    public void render(Graphics2D g2d) {
        backGround.render(g2d);
        for (Concert concert : concerts) {
            concert.move(1.);
            concert.render(g2d);
        }
        for (HUDElement element : header) {
            element.render(g2d);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        renderer.draw(g2d);
    }
}
