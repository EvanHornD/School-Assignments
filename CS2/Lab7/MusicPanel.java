package Lab7;

import Lab7.Rendering.*;
import Lab7.userInput.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MusicPanel extends JPanel {

    //#region   Class attributes
    static final String[] sortingTypes = new String[]{"Selection","Insertion","Merge"};
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
        Map<String, Integer> keyActions = keyBinds.getKeyActions();
        Map<String, Integer> keyFrames = keyBinds.getKeyFrames();

        runHUDCollisions();
        if(keyActions.get("Down")==1&& keyFrames.get("Down")==1){
            Concert concert = concerts[0];
            concert.setID(concert.getID()+1);
        }
        if(keyActions.get("Up")==1&& keyFrames.get("Up")==1){
            Concert concert = concerts[0];
            concert.setID(concert.getID()-1);
        }
        if(collision>-1){
            if(keyActions.get("Input")==1&& keyFrames.get("Input")==1){
                switch (collision) {
                    case 0:
                        switch(sortingTypes[sortingType]){
                            case "Selection":
                                Lab7_SortingAlgorithms.SelectionSortByArtist(concerts);
                            break;

                            case "Insertion":
                            Lab7_SortingAlgorithms.InsertionSortByArtist(concerts);
                            break;
                        }
                    break;
                    case 1:
                        switch(sortingTypes[sortingType]){
                            case "Selection":
                                Lab7_SortingAlgorithms.SelectionSortByCapacity(concerts);
                            break;

                            case "Insertion":
                            Lab7_SortingAlgorithms.InsertionSortByCapacity(concerts);
                            break;

                            case "Merge":
                            Lab7_SortingAlgorithms.MergeSortByCapacity(concerts);
                            break;
                        }
                    break;
                    case 3:
                        switch(sortingTypes[sortingType]){
                            case "Selection":
                                Lab7_SortingAlgorithms.SelectionSortByDuration(concerts);
                            break;

                            case "Insertion":
                            Lab7_SortingAlgorithms.InsertionSortByDuration(concerts);
                            break;

                            case "Merge":
                            Lab7_SortingAlgorithms.MergeSortByDuration(concerts);
                            break;
                        }
                    break;

                    case 4:
                        if(sortingType >= sortingTypes.length-1){
                            sortingType = 0;
                        } else {
                            sortingType++;
                        }
                        header[4].changeText("Sort Type: "+sortingTypes[sortingType]);
                    break;

                    case 5:
                        createConcertArray();
                    break;

                    default:
                    break;
                }
            }
        }

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
        header[0] = new HUDElement(new ShapeEntity("Rectangle", new int[]{0*(gridX),1}, new int[]{5*(gridX),gridY}), "Artist", (int)(40*(screenRatio)), "Centered");
        header[1] = new HUDElement(new ShapeEntity("Rectangle", new int[]{5*(gridX),1}, new int[]{2*(gridX),gridY}), "Capacity", (int)(40*(screenRatio)), "Centered");
        header[2] = new HUDElement(new ShapeEntity("Rectangle", new int[]{7*(gridX),1}, new int[]{3*(gridX),gridY}), "StartTime-EndTime", (int)(40*(screenRatio)), "Centered");
        header[3] = new HUDElement(new ShapeEntity("Rectangle", new int[]{10*(gridX),1}, new int[]{2*(gridX),gridY}), "Duration", (int)(40*(screenRatio)), "Centered");
        header[4] = new HUDElement(new ShapeEntity("Rectangle", new int[]{panelWidth/4,panelHeight-panelHeight/7}, new int[]{panelWidth/2,panelHeight/16}), "Sort Type: "+sortingTypes[sortingType], (int)(60*(screenRatio)), "Centered");
        header[5] = new HUDElement(new ShapeEntity("Rectangle", new int[]{panelWidth/3,panelHeight-2*panelHeight/7}, new int[]{panelWidth/3,panelHeight/16}), "Reset", (int)(60*(screenRatio)), "Centered");
    }

    // -------------
    // Check for mouse collisions with HUD elements and highlight them accordingly
    // -------------

    static void runHUDCollisions(){
        int[] mouseCoords = keyBinds.mouseCoords;
        for (int i = 0; i<header.length;i++) {
            if(header[i].isWithinBounds(mouseCoords)){
                if(collision != i){
                    if(collision>-1&&collision<4){
                        for (int j = 0; j < concerts.length; j++) {
                            concerts[j].highlightEntity(collision, true);
                        }
                        header[collision].highlightEntity(true);
                    }
                    collision = i;
                    if(collision<4){
                        for (int j = 0; j < collisions[sortingType].length; j++) {
                            if(collisions[sortingType][j]==collision){
                                for (int k = 0; k < concerts.length; k++) {
                                    concerts[k].highlightEntity(i, false);
                                }
                                header[i].highlightEntity(false);
                            }
                        }
                    } else{
                        header[collision].highlightEntity(false);
                    }
                }
                return;
            }
        }
        if(collision !=-1&&collision<4){
            for (int i = 0; i < concerts.length; i++) {
                concerts[i].highlightEntity(collision, true);
            }
            header[collision].highlightEntity(true);
        }
        if(collision >3){
            header[collision].highlightEntity(true);
        }
        collision = -1;
    }

    // -------------
    // calls each entities render method
    // -------------

    public void render(Graphics2D g2d) {
        backGround.render(g2d);
        for (int i = 0; i < concerts.length;i++) {
            concerts[i].render(g2d);
        }
        for (int i = 0; i < header.length; i++) {
            header[i].render(g2d);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        renderer.draw(g2d);
    }
}
