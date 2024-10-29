package Lab7;

import Lab7.Rendering.*;
import Lab7.userInput.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MusicPanel extends JPanel {

    //#region   Class attributes
    Time gameTimer;
    static KeyBindsManager keyBinds;
    static GraphicsRenderer renderer;

    ArrayList<Renderable> music = new ArrayList<>();
    //#endregion

    MusicPanel(Dimension dimensions){
        this.setPreferredSize(dimensions);
        this.setFocusable(true);
        this.requestFocus();
        keyBinds = new KeyBindsManager(this);
        renderer = new GraphicsRenderer(this);
    }

    public void startGameTimer() {
        // Update game state

        runGameLoop();

        // Trigger the repaint
        renderer.triggerRepaint();
    }

    public static void runGameLoop(){

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
        for (Renderable entity : music) {
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
