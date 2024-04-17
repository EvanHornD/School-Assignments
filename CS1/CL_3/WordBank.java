package CL_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordBank {

  /*  This first method implementation is completed for you already. 
      Do not modify the method signature 
   */
  public static String getAnswerForPuzzleNumber(int puzzleNumber) {
    try {
      /* Create a file scanner to read answers.txt */
      Scanner scanner = new Scanner(new File("C:\\Users\\Evan Horn\\GitRepositories\\School-Assignments\\CS1\\CL_3\\answers.txt"));

      /* Skip the first puzzleNumber number of words in the file */
      for (int i = 0; i < puzzleNumber; i++) {
        scanner.next();
      }
      String nextWord = scanner.next();
      scanner.close();
      /* Return the very next word */ 
      return nextWord;

    } catch (Exception e) {
      /* Handle exception */
      System.out.println("Input/File not found!");
    }
    return null;
  }

  public static boolean checkInDictionary(String proposed) {
      try {
        Scanner scanner = new Scanner(new File("C:\\Users\\Evan Horn\\GitRepositories\\School-Assignments\\CS1\\CL_3\\dictionary.txt"));
        while (scanner.hasNext()) {
          if (scanner.next().equals(proposed)) {
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
  /* Do not modify the method signature. */


}
