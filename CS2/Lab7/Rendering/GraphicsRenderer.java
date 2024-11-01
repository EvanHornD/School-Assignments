package Lab7.Rendering;

import Lab7.MusicPanel;
import java.awt.Graphics2D;
import javax.swing.*;

public class GraphicsRenderer {
 
    private MusicPanel panel;
 
    public GraphicsRenderer(MusicPanel panel) {
        this.panel = panel;
    }
 
    public void draw(Graphics2D g2d) {
        panel.render(g2d);
    }
 
    public void triggerRepaint() {
        SwingUtilities.invokeLater(panel::repaint);
    }
}
 