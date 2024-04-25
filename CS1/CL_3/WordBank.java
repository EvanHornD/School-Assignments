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
      		/* Return the very next word */ 
      		return scanner.next();
    	} catch (Exception e) {
      		/* Handle exception */
      		System.out.println("Input/File not found!");
    	}
    	return null;
  	}

  /* Do not modify the method signature. */
  	public static boolean checkInDictionary(String proposed) {
		try {
			/* Create a file scanner to read answers.txt */
		  	Scanner scanner = new Scanner(new File("C:\\Users\\Evan Horn\\GitRepositories\\School-Assignments\\CS1\\CL_3\\answers.txt"));
			while (scanner.hasNextLine()) {
				if(proposed.equals(scanner.nextLine())){
					return true;
				}
			}
			return false;
	  	} catch (Exception e) {
			/* Handle exception */
			System.out.println("Input/File not found!");
	  	}
    	return false;
  	}
}
