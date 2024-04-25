package GraphicsTest;

import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class wordleGameTest {
    final static Color green = new Color(84,140,78);
    final static Color yellow = new Color(181,159,59);
    final static Color black = new Color(18,18,19);
    final static Color lightGrey = new Color(129,131,132);
    final static Color darkGrey = new Color(58,58,60,255);
    final static Color white = new Color(248,248,248);

    private static String answer;
    private static wordleRectangle[][] rectangles;
    private static newPanel wordlePanel;
    private static String currentGuess = "";
    private static int numberOfGuesses = 0;
    private static String keyPressed;
    private static String state="running";

    public String getAnswer() {
        return answer;
    }

    public int getNumberGuessesSoFar() {
        return numberOfGuesses;
    }

    wordleGameTest(String answerIn,newPanel panel){
        answer = answerIn;
        numberOfGuesses = 0;
        runWordleGame(panel);

    }

    public static void checkForWinOrLoseCondition(){
        if(currentGuess.toLowerCase().equals(answer)){
            state = "gameWon";
        } else if(numberOfGuesses==6){
            state = "gameLost";
        }
    }

    public static void makeGuess(){
        String lowerCaseGuess = currentGuess.toLowerCase();
        if(checkGuessInDictionary(lowerCaseGuess)){
            for (int i = 0; i < lowerCaseGuess.length(); i++) {
                char currentChar = lowerCaseGuess.charAt(i);
                if(currentChar==answer.charAt(i)){
                    rectangles[1][numberOfGuesses*5+(i)].setFillColor(green);
                    rectangles[1][numberOfGuesses*5+(i)].setOutLineColor(green);
                } else if(answer.contains(""+currentChar)){
                    rectangles[1][numberOfGuesses*5+(i)].setFillColor(yellow);
                    rectangles[1][numberOfGuesses*5+(i)].setOutLineColor(yellow);
                } else {
                    rectangles[1][numberOfGuesses*5+(i)].setFillColor(darkGrey);
                    rectangles[1][numberOfGuesses*5+(i)].setOutLineColor(darkGrey);
                }
            }
            if(numberOfGuesses<6){
                numberOfGuesses++;
                checkForWinOrLoseCondition();
                currentGuess="";
            }
        }
    }

    public static void addInputToWordleGuess(String input){
        if(currentGuess.length()<5&&numberOfGuesses<7){
            currentGuess+=input;
            rectangles[1][numberOfGuesses*5+(currentGuess.length()-1)].setText(input);
        }
    }

    public static void removeFromWordleGuess(){
        if(currentGuess.length()>0&&numberOfGuesses<7){
            String newGuess = "";
            for (int i = 0; i < currentGuess.length()-1; i++) {
                newGuess+=currentGuess.charAt(i);
            }
            currentGuess=newGuess;
            rectangles[1][numberOfGuesses*5+(currentGuess.length())].setText("");
        }
    }

    public static void checkInput(String input){
        if(!input.equals("")){
            if(input.equals("Escape")){
                state = "end";
            }else if(input.equals("Enter")){
                makeGuess();
            }else if(input.equals("Backspace")){
                removeFromWordleGuess();
            }else{
                addInputToWordleGuess(input);
            }
            wordlePanel.resetLastKeyPressed();
        }
    }

    public static boolean checkGuessInDictionary(String guess) {
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Evan Horn\\GitRepositories\\School-Assignments\\GraphicsTest\\dictionary.txt"));
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().equals(guess.toLowerCase())) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found!");
        }
         return false;
    }

    public static void runWordleGame(newPanel panel){
        System.out.println(answer);
        wordlePanel = panel;
        rectangles = wordlePanel.getRectangles();
        while(!(state.equals("end"))){
            try {
                keyPressed = wordlePanel.getLastKeyPressed();
                if(state.equals("running")){
                    checkInput(keyPressed);
                    wordlePanel.triggerRepaint();
                } else if(state.equals("gameWon")){
                    Dimension panelSize = panel.getSize();
                    panel.addRectangle(new wordleRectangle((int)panelSize.getWidth()/2, ((int)panelSize.getHeight()/2), lightGrey, "You Won!!", white, new Font("Franklin Gothic",0,50)),3);
                    wordlePanel.triggerRepaint();
                    state = "gameOver";
                } else if(state.equals("gameLost")){
                    Dimension panelSize = panel.getSize();
                    panel.addRectangle(new wordleRectangle((int)panelSize.getWidth()/2, ((int)panelSize.getHeight()/2), lightGrey, "You Lost :'( the answer was: "+answer.toUpperCase(), white, new Font("Franklin Gothic",0,50)),3);
                    wordlePanel.triggerRepaint();
                    state = "gameOver";
                } else if(state.equals("gameOver")) {
                    if(keyPressed.equals("Escape")){
                        state = "end";
                    }
                }
                Thread.sleep(10);} 
            catch (InterruptedException e) {e.printStackTrace();}
        }
        System.exit(0);
    }
    
}
