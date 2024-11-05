package Lab7;

import javax.swing.SwingUtilities;

// this file is for launching the project
// if you want to see the sorting algorthims they are in the Lab7_SortingAlgorithms
//
// if the project is giving you an error when running the file then you need to open each of the separate class files individually for them to load properly
//
// to use earch of the different sorting algorithms you need to click on the header that you want to sort by, and change the sorting type by clicking on the button

public class Lab7_Horn {
            public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            MusicWindow menu = new MusicWindow();
            menu.panel.startGameTimer();
        });
    }
}
