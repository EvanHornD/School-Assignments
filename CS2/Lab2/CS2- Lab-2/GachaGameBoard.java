import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GachaGameBoard {
    public static GachaHero currentGachaHero;
    public static GachaVillain currentGachaVillain;

    // TODO:
    // Here Students will have to Scan the Heros csv file 
    // and assign the information into a 1D array of Objects called herosArray
    // Then return that heroArray 
    public static GachaHero[] scanHero(String filename){
        try {
            Scanner fileReader = new Scanner(new File(filename));
            int numOfHeroes = -1;
            while(fileReader.hasNextLine()){
                fileReader.nextLine();
                numOfHeroes++;
            }
            GachaHero[] Heroes = new GachaHero[numOfHeroes];
            
            fileReader.close();
            fileReader = new Scanner(new File(filename));
            fileReader.nextLine();
            for (int i = 0; i < Heroes.length; i++) {
                String fileLine = fileReader.nextLine();
                String heroData = "";
                String dataArray[] = new String[8];
                int dataType = 0;
                for (int j = 0; j <= fileLine.length(); j++) {
                    if(j==fileLine.length()||fileLine.charAt(j)==','){
                        dataArray[dataType]=heroData;
                        dataType++;
                        heroData="";
                    }else{
                        heroData+=fileLine.charAt(j);
                    }
                }
                Heroes[i]= new GachaHero(dataArray[0],dataArray[1],Integer.parseInt(dataArray[2]),Integer.parseInt(dataArray[3]),Integer.parseInt(dataArray[4]),Integer.parseInt(dataArray[5]),Integer.parseInt(dataArray[6]),Integer.parseInt(dataArray[7]));
            }
            fileReader.close();
            return Heroes;
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("could not find file");
            return new GachaHero[0];
            // TODO: handle exception
        }

    }

    // TODO:
    // Here Students will have to Scan the Villains csv file 
    // and assign the information into a 1D array of Objects called villainsArray
    // Then return that villainarray
    public static GachaVillain[] scanVillain(String filename){
        try {
            Scanner fileReader = new Scanner(new File(filename));
            int numOfVillains = -1;
            while(fileReader.hasNextLine()){
                fileReader.nextLine();
                numOfVillains++;
            }
            GachaVillain[] Villains = new GachaVillain[numOfVillains];
            
            fileReader.close();
            fileReader = new Scanner(new File(filename));
            fileReader.nextLine();
            for (int i = 0; i < Villains.length; i++) {
                String fileLine = fileReader.nextLine();
                String villainData = "";
                String dataArray[] = new String[6];
                int dataType = 0;
                for (int j = 0; j <= fileLine.length(); j++) {
                    if(j==fileLine.length()||fileLine.charAt(j)==','){
                        dataArray[dataType]=villainData;
                        dataType++;
                        villainData="";
                    }else{
                        villainData+=fileLine.charAt(j);
                    }
                }
                Villains[i]= new GachaVillain(dataArray[0],dataArray[1],Integer.parseInt(dataArray[2]),Integer.parseInt(dataArray[3]),Integer.parseInt(dataArray[4]),Integer.parseInt(dataArray[5]));
            }
            fileReader.close();
            return Villains;
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("could not find file");
            return new GachaVillain[0];
            // TODO: handle exception
        }
    }

    // TODO: 
    // This method will simulate a battle sequence 
    // between the current hero and the current villain
    // a boolean array representing their lives is created at the start 
    // [herosLife, VillainsLife] at the start both should be alive
    // true mean they are alive
    // false means they are dead
    public static boolean[] attackSequence(){
        return new boolean[0];
    }



/**
*---------------------DO NOT TOUCH CODE----------------------------------------
*/
    public static void makeGame(GachaHero[] gachaHeroArray, GachaVillain[] gachaVillainArray, GachaGame gachaPoolHero, GachaGame gachaPoolVillain) {
        JFrame frame = new JFrame("CS2 Gacha Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        JPanel startScreen = new JPanel();
        startScreen.setLayout(new GridBagLayout());

        JButton buttonDrawHero = new JButton("Draw Hero");
        startScreen.add(buttonDrawHero);

        mainPanel.add(startScreen, "startScreen");

        buttonDrawHero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeButtonBehaviorDrawHero(gachaHeroArray, gachaPoolHero);

                JPanel imageTextScreen = createImageTextScreen(cardLayout, mainPanel, gachaPoolVillain, gachaVillainArray);
                mainPanel.add(imageTextScreen, "imageTextScreen");

                cardLayout.show(mainPanel, "imageTextScreen");
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static JPanel createImageTextScreen(CardLayout cardLayout, JPanel mainPanel, GachaGame gachaPoolVillain, GachaVillain[] gachaVillainArray) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ImageIcon image = new ImageIcon("hero-sprite.png");
        JLabel imageLabel = new JLabel(image);

        JLabel textLabel = new JLabel("<html>YOU HAVE DRAWN " + currentGachaHero.getName() + " He is a " + currentGachaHero.getRarity() +
            " Hero with the following stats " +
            " HP: " + currentGachaHero.getHp() + " ATTACK: " + currentGachaHero.getAttack() + " DEFENSE: " + currentGachaHero.getDefense() +
            " SPEED: " + currentGachaHero.getSpeed() + " LUCK: " + currentGachaHero.getLuck() + "</html>", SwingConstants.CENTER);
        textLabel.setFont(new Font("Serif", Font.BOLD, 24));

        JButton battleButton = new JButton("BATTLE");
        battleButton.setFont(new Font("Serif", Font.BOLD, 20));

        battleButton.addActionListener(e -> {
            JPanel battleMenu = createBattleMenu(gachaPoolVillain, gachaVillainArray);
            mainPanel.add(battleMenu, "battleMenu");
            cardLayout.show(mainPanel, "battleMenu");
        });

        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(textLabel, BorderLayout.NORTH);
        panel.add(battleButton, BorderLayout.SOUTH);

        return panel;
    }

    public static void executeButtonBehaviorDrawHero(GachaHero[] gachaHeroArray, GachaGame gachaPool) {
        System.out.println("Button was clicked! - Drawing hero");
        currentGachaHero = gachaHeroArray[gachaPool.singleDraw()];
        currentGachaHero.printGachaHero();
    }

    public static JPanel createBattleMenu(GachaGame gachaPoolVillain, GachaVillain[] gachaVillainArray) {
        if (currentGachaVillain == null || currentGachaVillain.getHp() <= 0) {
            System.out.println("Battle was clicked! - Drawing Villain");
            currentGachaVillain = gachaVillainArray[gachaPoolVillain.singleDraw()];
            currentGachaVillain.printGachaVillain();
        }

        JPanel battleMenu = new JPanel();
        battleMenu.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JButton attackButton = new JButton("Attack");
        JButton defendButton = new JButton("Defend");
        JButton itemButton = new JButton("Use Draw");
        JButton runButton = new JButton("Run");

        attackButton.addActionListener(e -> {
            boolean[] outcome = attackSequence();
            if (!outcome[0]) {
                System.err.println("The hero is dead");
                JPanel endingScreen = createEndingScreen();
                JPanel parentPanel = (JPanel) battleMenu.getParent();
                parentPanel.add(endingScreen, "endingScreen");
                ((CardLayout) parentPanel.getLayout()).show(parentPanel, "endingScreen");
            } else {
                System.err.println("Refresh the page");
                refreshBattleMenu((CardLayout) battleMenu.getParent().getLayout(), (JPanel) battleMenu.getParent(), gachaPoolVillain, gachaVillainArray);
            }
        });

        defendButton.addActionListener(e -> System.out.println("You chose to defend!"));
        itemButton.addActionListener(e -> System.out.println("You used an Draw!"));
        runButton.addActionListener(e -> System.out.println("You ran away!"));

        buttonPanel.add(attackButton);
        buttonPanel.add(defendButton);
        buttonPanel.add(itemButton);
        buttonPanel.add(runButton);

        battleMenu.add(buttonPanel, BorderLayout.SOUTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        ImageIcon heroImage = new ImageIcon("hero-sprite.png");
        JLabel heroImageLabel = new JLabel(heroImage);

        JLabel heroHPLabel = new JLabel(currentGachaHero.getName() + " HP: " + currentGachaHero.getHp());
        heroHPLabel.setFont(new Font("Serif", Font.BOLD, 18));

        leftPanel.add(heroImageLabel, BorderLayout.CENTER);
        leftPanel.add(heroHPLabel, BorderLayout.SOUTH);

        battleMenu.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        ImageIcon villainImage = new ImageIcon("villain-sprite.png");
        JLabel villainImageLabel = new JLabel(villainImage);

        JLabel villainHPLabel = new JLabel(currentGachaVillain.getName() + " HP: " + currentGachaVillain.getHp());
        villainHPLabel.setFont(new Font("Serif", Font.BOLD, 18));

        rightPanel.add(villainImageLabel, BorderLayout.CENTER);
        rightPanel.add(villainHPLabel, BorderLayout.SOUTH);

        battleMenu.add(rightPanel, BorderLayout.EAST);

        return battleMenu;
    }

    // Method to refresh the battle menu
    public static void refreshBattleMenu(CardLayout cardLayout, JPanel mainPanel, GachaGame gachaPoolVillain, GachaVillain[] gachaVillainArray) {
        JPanel battleMenu = createBattleMenu(gachaPoolVillain, gachaVillainArray);
        Component[] components = mainPanel.getComponents(); 
        for (Component component : components) {
            if (component instanceof JPanel && ((JPanel) component).getName() != null && ((JPanel) component).getName().equals("battleMenu")) { 
                mainPanel.remove(component); break; 
            } 
        }
        mainPanel.add(battleMenu, "battleMenu");
        cardLayout.show(mainPanel, "battleMenu");
    }
    

    // Method to create the ending screen
    public static JPanel createEndingScreen() {
        JPanel endingScreen = new JPanel();
        endingScreen.add(new JLabel("The hero is dead. Game Over."));
        return endingScreen;
    }

    
    /**
    *---- END OF DO NOT TOUCH CODE -------------------------------------------------------------------------------
    */

    public static void main(String[] args) {
        //Get the files name Here

        // GachaHero and GachaVillain arrays (Task 3 - 4) will be declared here
        GachaHero[] heroes = scanHero("CS2\\Lab2\\CS2- Lab-2\\lab2-herodataset - Sheet1.csv");
        GachaVillain[] villains = scanVillain("CS2\\Lab2\\CS2- Lab-2\\lab2-villaindataset - Sheet1.csv");
        System.out.println("----------------\n  Gacha Heroes  \n----------------");
        for (GachaHero gachaHero : heroes) {
            gachaHero.printGachaHero();
        }
        System.out.println("------------------\n  Gacha Villains  \n------------------");
        for (GachaVillain gachaVillain : villains) {
            gachaVillain.printGachaVillain();
        }
        //Students may uncomment these line at Task 5
        /**
            GachaGame gachaPoolHero = new GachaGame();
            GachaGame gachaPoolVillain = new GachaGame();
            makeGame(gachaHeroArray, gachaVillainArray, gachaPoolHero, gachaPoolVillain);
         */


    }
}
