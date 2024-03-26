class MovieReview {
	public static void main(String[] args) {
		//----------
		// Example
		//----------
		String myName = "Evan H";  
		System.out.println("Hi, my name is " + myName + ".");

		//----------
		// Q1
		//----------

		String movieName = "Mega Mind";
		String directorName = "Tom McGrath";
		System.out.println("My favorite movie is " + movieName + " directed by " + directorName + ".");

		//----------
		// Q2
		//----------

		
		// You may not declare/initialize any strings for this question.
		// int, char, double, long
		// Each variable type initialized in this question can only be used once.
		int year = 2011;
		System.out.println("The movie was released in year " + year + ".");
		long tickets = 20438095;
		System.out.println("While in the box office this movie sold " + tickets + " ticket.");
		double ticketPrice = 15.75;
		System.out.println("A movie ticket to see it might sell for $" + ticketPrice + "!");
		char grade = 'S';
		System.out.println("Overall, this movie should be considered " + grade + " tier.");

		//----------
		// Q3
		//----------

		tickets = tickets + 1;

		System.out.println("I bought another ticket! Make that " + tickets + " tickets.");
	}
}