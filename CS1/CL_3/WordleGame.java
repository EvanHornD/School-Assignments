package CL_3;

public class WordleGame {
  	/* allGuesses represents the wordle game */
  	WordleLetter[][] allGuesses = new WordleLetter[6][5];
	String wordleAnswer;
	int numberOfGuesses;

	public String getAnswer(){
		return this.wordleAnswer;
	}

	public int getNumberOfGuesses(){
		return this.numberOfGuesses;
	}

	//gets the array of the guess at the index in the 2d array
	public WordleLetter[] getGuess(int guessNumber){
		return this.allGuesses[guessNumber];
	}


  	WordleGame(int gameNumber){
		wordleAnswer = WordBank.getAnswerForPuzzleNumber(gameNumber);
  	}

	// sets the color of the letters based on the wordle coloring logic
	public void makeGuess(String guess){
		for (int i = 0; i < guess.length(); i++) {
			char currentChar = guess.charAt(i);
			WordleLetter letter = new WordleLetter(currentChar);
			if(this.wordleAnswer.charAt(i) == currentChar){
				letter.setColor("green");
			} else if(this.wordleAnswer.indexOf(currentChar)>=0){
				letter.setColor("yellow");
			} else {
				letter.setColor("red");
			}
			this.allGuesses[this.numberOfGuesses][i] = letter;
		}
		numberOfGuesses++;
	}

	public boolean isGameOver() {
		return this.numberOfGuesses == 6 || isGameWon();
	}

	// checks if the character assigned to each of the letters in the guess is equal to the answer
	public boolean isGameWon() {
		if(numberOfGuesses==0){
			return false;
		}
		String lastGuess = "";
		for (int i = 0; i < 5; i++) {
			lastGuess+=allGuesses[numberOfGuesses-1][i].getChar();
		}
		if(lastGuess == wordleAnswer){
			return true;
		}
		return false;
	}

  	public String toString() {
    	/* result will be used to build the full answer String */ 
    	String result = ""; 
       	/* for each word guessed so far */ 
  	   	for (int i = 0; i < this.numberOfGuesses; i++) {
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
