package CL_3;

import java.io.FileNotFoundException;

public class WordleGame {
  	/* allGuesses represents the wordle game */
  	private WordleLetter[][] allGuesses = new WordleLetter[6][5];
	private String wordleAnswer;

	public String getAnswer(){
		return this.wordleAnswer;
	}

  	WordleGame(int gameNumber){
		wordleAnswer = WordBank.getAnswerForPuzzleNumber(gameNumber);
  	}




  	public String toString() {
    	/* result will be used to build the full answer String */ 
    	String result = ""; 
       	/* for each word guessed so far */ 
  	   	for (int i = 0; i < getNumberGuessesSoFar(); i++) {
         	/* get each letter of each word */
  	     	for (int j = 0; j < 5; j++) {
           		/* concatenate it to the result */ 
           		/* WordleLetter's toString() is automatically invoked here. */
  	       		result += getGuess(i)[j];
  	     	}
         	/* new line separator between each word */ 
  	     	result += "\n";
  	   	}
    	return result;
  	}
}
