package GraphicsTest;

import java.awt.*;

public class wordleGameTest {
    final static Color green = new Color(84,140,78);
    final static Color yellow = new Color(181,159,59);
    final static Color black = new Color(18,18,19);
    final static Color lightGrey = new Color(129,131,132);
    final static Color darkGrey = new Color(58,58,60,255);
    final static Color white = new Color(248,248,248);

    private String answer;
    private static String currentGuess;
    private int numberOfGuesses;
    private static String keyPressed;
    private static String state="running";

    public String getAnswer() {
        return this.answer;
    }

    public int getNumberGuessesSoFar() {
        return this.numberOfGuesses;
    }

    wordleGameTest(String answerIn,newPanel panel){
        this.answer = answerIn;
        this.numberOfGuesses = 0;
        runWordleGame(panel);

    }

    public static void closeProgram(){

    }

    public static void checkWordleGuess(){

    }

    public static void addInputToWordleGuess(String input){

    }

    public static void removeFromWordleGuess(){

    }

    public static void checkInput(String input){
        if(!keyPressed.equals("")){
            if(keyPressed.equals("Escape")){
                state = "end";
            }else if(keyPressed.equals("Enter")){

            }else if(keyPressed.equals("Backspace")){

            }else{

            }
        }
    }

    public static void runWordleGame(newPanel panel){
        wordleRectangle[][] rectangles = panel.getRectangles();
        while(!(state.equals("end"))){
            try {   
                keyPressed = panel.getLastKeyPressed();
                checkInput(keyPressed);
                panel.triggerRepaint();
                Thread.sleep(10);} 
            catch (InterruptedException e) {e.printStackTrace();}
        }
        System.exit(0);
    }
    
}
