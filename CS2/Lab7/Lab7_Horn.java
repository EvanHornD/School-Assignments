package Lab7;

import javax.swing.SwingUtilities;

public class Lab7_Horn {
            public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            MusicWindow menu = new MusicWindow();
            menu.panel.startGameTimer();
        });
    }
}
