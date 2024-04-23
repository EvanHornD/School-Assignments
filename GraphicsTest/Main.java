package GraphicsTest;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    WordleGame game = startGame(scanner);
    playGame(game, scanner);
    reportGameOutcome(game);
    scanner.close();
    }

  public static WordleGame startGame(Scanner scanner) {
    System.out.println("Enter an integer for which puzzle number you would like to play (between 0 and 2315):");
    int puzzleNumber = scanner.nextInt();
    return new WordleGame(puzzleNumber);
  }

  public static void playGame(WordleGame game, Scanner scanner) {
    while (!game.isGameOver()) {
      System.out.println("Enter a 5 letter guess:");
      String guess = scanner.next();

      while (!WordBank.checkInDictionary(guess)) {
        System.out.println("Invalid guess. Enter a 5 letter guess:");
        guess = scanner.next();
      }

      game.guess(guess);
      System.out.println(game);
    }
  }

  public static void reportGameOutcome(WordleGame game) {
    if (game.isGameWin()) {
      System.out.println("You won!");
    } else {
      System.out.println("The answer was " + game.getAnswer());
    }
  }
}