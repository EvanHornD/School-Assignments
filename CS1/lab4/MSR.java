import java.util.Scanner;

class MSR {
	
	public static void println(String text){
		System.out.println(text);
	}

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);

		System.out.println("How much money are you willing to spend on movie snacks?: ");
		double pricePoint = userInput.nextDouble();

		System.out.println("I would be interested in trying a slushy! (type true or false): ");
		boolean isInterested = userInput.nextBoolean();

		System.out.println("I would prefer popcorn over candy (type true or false): ");
		boolean preferPopcorn = userInput.nextBoolean();

		System.out.println("How many people are you buying snacks for?: ");
		int numPeople = userInput.nextInt();
		
		// 								<pricePoint>
		// 								/           \
		//                      <10.00 /             \ >=10.00
		//                            /               \
		//               <isInterested>              <preferPopcorn>
		//                  /      \                     /         \
		//            true /        \ false        true /           \ false
		//                /          \                 /             \
		//          STRAWBERRY      SODA       LARGE POPCORN          <numPeople>
		//           SLUSHY                                              /    \
		//                                                            1 /      \ >1
		//                                                             /        \
		//                                                          COOKIE    NERDS 
		//                                                          DOUGH     GUMMY
		//                                                          BITES     CLUSTERS

		System.out.print("Recommendation: "); // Don't change this

		if (pricePoint>=10){
			if (preferPopcorn){
				println("LARGE POPCORN");
			}else if(numPeople>1){
				println("NERDS GUMMY CLUSTERS");
			}else{
				println("COOKIE DOUGH BITES");
			}
		}else if(pricePoint>=0){
			if (isInterested){
				println("STRAWBERRY SLUSHY");
			}else{
				println("SODA");
			}
		}
		println("On a scale from 1-10, how satisfied are you with your recommendation? (enter a positive integer) ");

		int rating = userInput.nextInt();

		if (rating>10||rating<1) {
			println("Invalid input. Goodbye!");
		}
		if (rating<7) { 
			println("Thank you for your feedback! Here is the code for a free movie ticket: JFH34J5J ");
		}
		else { 
			println("Thank you for your feedback! Here is the code for a 50% discount on your next movie ticket: KFH34J5J ");
		}
		userInput.close();
	}
}