package Lab7;

import Lab7.Rendering.*;
import Lab7.userInput.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

import Lab7.gameTimer;

public class MusicPanel extends JPanel {

    //#region   Class attributes
    gameTimer gameTimer;
    static KeyBindsManager keyBinds;
    static GraphicsRenderer renderer;

    ShapeEntity backGround = new ShapeEntity(new Color(24,24,24));
    static ArrayList<Concert> concerts = new ArrayList<>();
    //#endregion

    MusicPanel(Dimension dimensions){
        this.setPreferredSize(dimensions);
        this.setFocusable(true);
        this.requestFocus();
        keyBinds = new KeyBindsManager(this);
        renderer = new GraphicsRenderer(this);
        gameTimer = new gameTimer();
        concerts.add(new Concert(1,"test",2000,100,400,new int[]{(int)dimensions.getWidth(),(int)dimensions.getHeight()}));
        startGameTimer();
    }

    public void startGameTimer() {
        gameTimer.start(deltaTime -> {
            // Update game state
            runGameLoop(deltaTime);

            // Trigger the repaint
            renderer.triggerRepaint();
        });
    }

    public static void runGameLoop(double dt){
        Map<String, Integer> keyActions = keyBinds.getKeyActions();
        Map<String, Integer> keyFrames = keyBinds.getKeyFrames();
        if(keyActions.get("Down")==1&& keyFrames.get("Down")==1){
            Concert concert = concerts.get(0);
            concert.setID(concert.getID()+1);
        }
        if(keyActions.get("Up")==1&& keyFrames.get("Up")==1){
            Concert concert = concerts.get(0);
            concert.setID(concert.getID()-1);
        }

        //updates all of the scenes entities making use of which keys are being pressed and for how long
        Object[][] Actions = keyBinds.getInformation();
        for (Object[] Action : Actions) {
            Integer state = (Integer)Action[1];
            if(state>0){
                String action = (String)Action[0];
                Integer framesPressed = (Integer)Action[2];
                switch (state) {
                    case 1 -> {
                        if (framesPressed==0) {
                            System.out.println(action+" Pressed");
                        } else {
                            System.out.println(framesPressed);
                        }
                    }
                    case 2 -> System.out.println(action+" Released");
                }
            }
        }
        keyBinds.updateFrameInformation();
    }

    public void render(Graphics2D g2d) {
        backGround.render(g2d);
        for (Renderable entity : concerts) {
            entity.render(g2d);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        renderer.draw(g2d);
    }
}
