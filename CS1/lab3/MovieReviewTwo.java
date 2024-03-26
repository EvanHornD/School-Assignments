import java.util.Scanner;

//scanner.nextDouble();
//scanner.nextLine();
//scanner.nextInt();
//scanner.nextBoolean();
//scanner.nextLong();

class MovieReviewTwo {
	public static void main(String[] args) {

		// Example
		//  System.out.println("Enter your username: ");
    	// 	String username = scanner.nextLine();
    	// 	System.out.println("Username is " + username);

		//----------
		// Q1
		//----------
		// This time you will be using user input from the scanner to do so!
		// Start by adding in the prompts for your user, as seen in the example above
		Scanner userInput = new Scanner(System.in);

		System.out.println("what is your favorite movie");
		String movieName = userInput.nextLine();
		System.out.println(" ");
		System.out.println("who directed it");
		String movieDirector = userInput.nextLine();

		System.out.println("My favorite movie is " + movieName  + " directed by " + movieDirector + ".");

		//----------
		// Q2
		//----------
		System.out.println(" ");
		System.out.println("what year was it released");
		String releaseYear= userInput.nextLine();
		//Below you will print the year the movie was released
		System.out.println("The movie was released in year " + releaseYear + ".");
		System.out.println(" ");
		System.out.println("how many tickets were sold");
		String ticketsSold = userInput.nextLine();
		// Below you will print number of tickets sold, Ex. 103150000 tickets
		System.out.println("While in the box office this movie sold " + ticketsSold + " ticket.");
		System.out.println(" ");
		System.out.println("give the movie a letter grade");
		String movieRating = userInput.nextLine();
		System.out.println("Overall, this movie should be considered " + movieRating + " tier.");
		System.out.println(" ");

		//----------
		// Q3
		//----------

		// Below should be a single variable including dollars and some cents.
	
		System.out.println("Enter the price of the movie: ");
		Double ticketPrice = userInput.nextDouble();
		// Initialize a boolean variable to check if the price is above $20.00
		// Using the boolean, answer the question below (expensive is above $20.00)
		boolean isExpensive = (ticketPrice > 20.00);
		userInput.close();
		System.out.println("Is the movie expensive? " + isExpensive);

	}
}