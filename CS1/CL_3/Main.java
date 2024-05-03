
/* Evan Horn 
[CS1101] Comprehensive Lab 3 Wordle Game
This work is to be done individually. It is not permitted to.
share, reproduce, or alter any part of this assignment for any
purpose. Students are not permitted to share code, upload
this assignment online in any form, or view/receive/
modifying code written by anyone else. This assignment is part.
of an academic course at The University of Texas at El Paso and
a grade will be assigned for the work produced individually by
the student.

This project is the bare minimum amount of effort im willing to put into this, because of all the restrictions that are put onto me.
I created a version of this that renders everything using 2d graphics, so this version is just an after thought that im only doing for the grade.
*/

package CL_3;
import java.util.Scanner;

public class Main {

    /* Do not modify the method signature. */ 
    public static WordleGame startGame(Scanner scanner)  {
        System.out.println("Enter an integer for which puzzle number you would like to play (between 0 and 2315):");
        int puzzleNumber = scanner.nextInt();
        return new WordleGame(puzzleNumber);
    }

    /* Do not modify the method signature. */ 
    public static void playGame(Scanner scanner, WordleGame game)  {
        while (!game.isGameOver()) {
            System.out.println("Enter a 5 letter guess:");
            String guess = scanner.next();
            while (!WordBank.checkInDictionary(guess)) {
                System.out.println("Invalid guess. Enter a 5 letter guess");
                guess = scanner.next();
            }
            game.makeGuess(guess);
            System.out.println(game);
        }
    }

    /* Do not modify the method signature. */ 
    public static void reportGameOutcome(WordleGame game) {
        if (game.isGameWon()) {
            System.out.println("You won!");
        } else {
            System.out.println("The answer was " + game.getAnswer());
        }
    }

    /* This main method body should not be modified. */ 
    public static void main(String[] args) {
        /* Only use this Scanner for user input, do not create new ones via new Scanner(System.in).*/ 
        Scanner scanner = new Scanner(System.in);
        WordleGame game = startGame(scanner);
        playGame(scanner, game);
        reportGameOutcome(game);
    }
}
