package CL_3;

public class WordleGame {
  private WordleLetter[][] allGuesses = new WordleLetter[6][5];
  private String answer;
  private int numberGuessesSoFar = 0;

  public WordleGame(int puzzleNumber) {
    this.answer = WordBank.getAnswerForPuzzleNumber(puzzleNumber);
  }

  public String getAnswer() {
    return this.answer;
  }

  public int getNumberGuessesSoFar() {
    return this.numberGuessesSoFar;
  }

  public WordleLetter[] getGuess(int guessNumber) {
    return this.allGuesses[guessNumber];
  }

  public void guess(String guessWord) {
    for (int i = 0; i < 5; i++) {
      char guessChar = guessWord.charAt(i);
      WordleLetter wordleLetter = new WordleLetter(guessChar);

      if (this.answer.charAt(i) == guessChar) {
        wordleLetter.setColor("green");
      } else if (this.answer.indexOf(guessChar) != -1) {
        wordleLetter.setColor("yellow");
      } else {
        wordleLetter.setColor("red");
      }

      this.allGuesses[this.numberGuessesSoFar][i] = wordleLetter;
    }

    this.numberGuessesSoFar++;
  }

  public boolean isGameOver() {
    return this.numberGuessesSoFar == 6 || isGameWin();
  }

  public boolean isGameWin() {
    if (this.numberGuessesSoFar == 0) {
      return false;
    }

    WordleLetter[] lastGuess = getGuess(this.numberGuessesSoFar - 1);
    for (int i = 0; i < 5; i++) {
      if (!"green".equals(lastGuess[i].getColor())) {
        return false;
      }
    }

    return true;
  }

  public String toString() {
    String result = "";
    for (int i = 0; i < this.numberGuessesSoFar; i++) {
      for (int j = 0; j < 5; j++) {
        result += this.allGuesses[i][j];
      }
      result += "\n";
    }
    return result;
  }
}
