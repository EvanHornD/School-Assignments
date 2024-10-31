package Lab7;

import javax.swing.SwingUtilities;

public class Lab7 {
            public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            MusicWindow game = new MusicWindow();
            game.panel.startGameTimer();
        });
    }
}
