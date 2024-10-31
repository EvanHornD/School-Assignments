package Lab7;

// YOUR FULL NAME HERE
//[CS2401]


import java.util.Random;

class Lab7_Answers {
  public static void main(String[] args) {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    /*
     * DON'T TOUCH 
     * 
    */
    ////////////////////////////////////////////////////////////////////////////////////////////////
    Concert[] concerts = createConcertArray();
    System.out.println("-----------------------------------------------------");
    System.out.println("The number of concerts in the array: " + concerts.length);
    System.out.println("Printing the Concerts information [Not sorted]");
  
    showConcertInfo(concerts);
    System.out.println("-----------------------------------------------------");

    System.out.println("Sorting the array using Selection Sort based on duration");
    System.out.println("-----------------------------------------------------");
    long start = System.nanoTime();
    SelectionSortByDuration(concerts);
    long end = System.nanoTime();
    long timeSelectionSortDuration = (end - start);
    System.out.println("Printing the sorted array.....");
 
    showConcertInfo(concerts);

    //Restoring to initial state

    concerts = createConcertArray();

    System.out.println("-----------------------------------------------------");

    System.out.println("Sorting the array using Selection Sort based on capacity");
    concerts = createConcertArray();
    System.out.println("-----------------------------------------------------");
    start = System.nanoTime();
    SelectionSortByCapacity(concerts);
    end = System.nanoTime();
    long timeSelectionSortCapacity = (end - start);
    System.out.println("Printing the sorted array.....");
 
    showConcertInfo(concerts);

    // Restoring the array to its initial state
    concerts = createConcertArray();

    System.out.println("-----------------------------------------------------");

    System.out.println("Sorting the array using Selection Sort based on artist");
    concerts = createConcertArray();
    System.out.println("-----------------------------------------------------");
    start = System.nanoTime();
    SelectionSortByArtist(concerts);
    end = System.nanoTime();
    long timeSelectionSortArtist = (end - start);
    System.out.println("Printing the sorted array.....");
 
    showConcertInfo(concerts);

    // Restoring the array to its initial state
    concerts = createConcertArray();


    System.out.println("-----------------------------------------------------");

    System.out.println("Sorting the array using Merge Sort based on duration");
    concerts = createConcertArray();

    System.out.println("-----------------------------------------------------");
    start = 0;
    end = 0;
    start = System.nanoTime();
    MergeSortByDuration(concerts);
    end = System.nanoTime();
    long timeMergeSortDuration = (end - start);
    System.out.println("Printing the sorted array.....");

    showConcertInfo(concerts);

    // Restoring the array to its initial state
    concerts = createConcertArray();

    System.out.println("-----------------------------------------------------");

    
    System.out.println("Sorting the array using Merge Sort based on capacity");
    concerts = createConcertArray();
    System.out.println("-----------------------------------------------------");
    start = 0;
    end = 0;
    start = System.nanoTime();
    MergeSortByCapacity(concerts);
    end = System.nanoTime();
    long timeMergeSortCapacity = (end - start);
    System.out.println("Printing the sorted array.....");

    showConcertInfo(concerts);

    System.out.println("-----------------------------------------------------");
    System.out.println("Sorting the array using Insertion Sort based on capacity");
    concerts = createConcertArray();
    System.out.println("-----------------------------------------------------");
    start = System.nanoTime();
    InsertionSortByCapacity(concerts);
    end = System.nanoTime();
    long timeInsertionSortCapacity = (end - start);
    System.out.println("Printing the sorted array.....");

    showConcertInfo(concerts);

    // Restoring the array to its initial state
    concerts = createConcertArray();

    System.out.println("-----------------------------------------------------");

    System.out.println("Sorting the array using Insertion Sort based on duration");
    concerts = createConcertArray();
    System.out.println("-----------------------------------------------------");
    start = System.nanoTime();
    InsertionSortByDuration(concerts);
    end = System.nanoTime();
    long timeInsertionSortDuration = (end - start);
    System.out.println("Printing the sorted array.....");
   
    showConcertInfo(concerts);

    // Restoring the array to its initial state
    concerts = createConcertArray();


    System.out.println("-----------------------------------------------------");

    System.out.println("Sorting the array using Insertion Sort based on artist");
    concerts = createConcertArray();
    System.out.println("-----------------------------------------------------");
    start = System.nanoTime();
    InsertionSortByArtist(concerts);
    end = System.nanoTime();
    long timeInsertionSortArtist = (end - start);
    System.out.println("Printing the sorted array.....");
   
    showConcertInfo(concerts);

    // Restoring the array to its initial state
    concerts = createConcertArray();
    

    System.out.println("-----------------------------------------------------");
    System.out.println("************ Runtime Statistics **************");
    System.out.println();
    System.out.println("Selection sort - duration: " + timeSelectionSortDuration + " ns");
    System.out.println("Selection sort - capacity: " + timeSelectionSortCapacity + " ns");
    System.out.println("Selection sort - artist: " + timeSelectionSortArtist + " ns");
    System.out.println();
    System.out.println("Merge sort - duration: " + timeMergeSortDuration + " ns");
    System.out.println("Merge sort - capacity: " + timeMergeSortCapacity + " ns");
    System.out.println();
    System.out.println("Insertion sort - duration: " + timeInsertionSortDuration + " ns");
    System.out.println("Insertion sort - capacity: " + timeInsertionSortCapacity + " ns");
    System.out.println("Insertion sort - artist: " + timeInsertionSortArtist + " ns");
    System.out.println();
    System.out.println();
    System.out.println("**********************************************");
    System.out.println("-----------------------------------------------------");
    System.out.println();
  }
  ////////////////////////////////////////////////////////////////////////////////////////////////
  /*
    * DON'T TOUCH 
    * 
  */
  ////////////////////////////////////////////////////////////////////////////////////////////////
  
  /**
   * Change the body of this method to arrange the Concert
   * objects in ascending order of duration.
   * The method must use Selection Sort.
   * Do NOT change the method header.
   * Consider method overloading.
   * 
   * @param allConcerts
   */

    public static void SelectionSortByDuration(Concert[] allConcerts) {
      System.out.println("write code here");
    }


  public static void SelectionSortByCapacity(Concert[] allConcerts) {
    System.out.println("write code here");

  }

  public static void SelectionSortByArtist(Concert[] allConcerts) {
    System.out.println("write code here");

  }





  /**
   * Change the body of this method to arrange the Concert
   * objects in ascending order of duration.
   * The method must use Merge Sort.
   * Do NOT change the method header.
   * Consider method overloading.
   * 
   * @param allConcerts
   */

    public static void MergeSortByDuration(Concert[] allConcerts) {
      System.out.println("write code here");
    }

  static void mergeSort(Concert[] allConcerts, int left, int right) {
    System.out.println("write code here");
  }

  static void merge(Concert[] allConcerts, int left, int middle, int right) {
    System.out.println("write code here");

  }





  /**
   * Change the body of this method to arrange the Concert
   * objects in ascending order of capacity.
   * The method must use Merge Sort.
   * Do NOT change the method header.
   * 
   * @param allConcerts
   */
  public static void MergeSortByCapacity(Concert[] allConcerts) {
    mergeSortByCapacity(allConcerts, 0, allConcerts.length - 1);
  }

  static void mergeSortByCapacity(Concert[] allConcerts, int left, int right) {
    System.out.println("write code here");
  }

  static void mergeByCapacity(Concert[] allConcerts, int left, int middle, int right) {
    System.out.println("write code here");

  }

   /**
   * Change the body of this method to arrange the Concert
   * objects in ascending order of capacity.
   * The method must use Insertion Sort.
   * Do NOT change the method header.
   * 
   * @param allConcerts
   */
  public static void InsertionSortByDuration(Concert[] allConcerts) {
    System.out.println("write code here");
  }

  public static void InsertionSortByCapacity(Concert[] allConcerts) {
    System.out.println("write code here");
  }

  public static void InsertionSortByArtist(Concert[] allConcerts) {
    System.out.println("write code here");
  }


  ////////////////////////////////////////////////////////////////////////////////////////////////
  /*
  * DON'T TOUCH 
  * 
  */
  ////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Display all the variables of a
   * Airplane object in the same sequence
   * they appear in the parameter array.
   * 
   * @param allConcerts
   */

   static void  showConcertInfo(Concert[] allConcerts) {
    int idWidth = 7;
    int artistWidth = 22;
    int capacityWidth = 10;
    int startTimeWidth = 12;
    int endTimeWidth = 10;
    int durWidth = 10;
				
		System.out.println();
		// Print the top border
	 	System.out.println("+" + "-".repeat(idWidth) + "+" + "-".repeat(artistWidth) + "+" 
		+ "-".repeat(capacityWidth) + "+" + "-".repeat(startTimeWidth) + "+" + "-".repeat(endTimeWidth) + "+"
		+ "-".repeat(durWidth) + "+");
    //Print header
    System.out.printf("| %-5s | %-20s | %-8s | %-8s | %-8s | %-8s |%n", 
		 	"ID", "Artist", "Capacity", "Start Time", "End Time", "Duration");
    System.out.println("+" + "-".repeat(idWidth) + "+" + "-".repeat(artistWidth) + "+" 
    + "-".repeat(capacityWidth) + "+" + "-".repeat(startTimeWidth) + "+" + "-".repeat(endTimeWidth) + "+"
    + "-".repeat(durWidth) + "+");
    //Print concerts
    for (int i = 0; i < allConcerts.length; i++) {
      System.out.println(allConcerts[i].toString());
    }
    //Prints bottom border
    System.out.println("+" + "-".repeat(idWidth) + "+" + "-".repeat(artistWidth) + "+" 
    + "-".repeat(capacityWidth) + "+" + "-".repeat(startTimeWidth) + "+" + "-".repeat(endTimeWidth) + "+"
    + "-".repeat(durWidth) + "+");
    System.out.println();

  }

  static Concert[] createConcertArray() {
    //change this when doing extra credit
    Concert[] concerts = new Concert[10];
      Random rand = new Random();

      String[] artists = {"Beyoncé", "Coldplay", "Drake", "Taylor Swift", "Kendrick Lamar", "Adele", "The Weeknd", 
                      "Billie Eilish", "Post Malone", "Bruno Mars", "Sabrina Carpenter", "Chappell Roan", "Noah Khan"};
      //change length of for loop if doing extra credit
      for (int i = 0; i < 10; i++) {
          int capacity = rand.nextInt(100,1000) + 1; // generates capacity between 100 to 1000
          int startTime = rand.nextInt(2300); // generates start time between 0 to 2300
          int endTime = startTime + rand.nextInt(2400 - startTime) + 1; // ensures end time > start time

          String artist = artists[rand.nextInt(artists.length)]; //gets a random artist from the list

          concerts[i] = new Concert(i+1, artist, capacity, startTime, endTime);
      }
      return concerts;

  }
  ////////////////////////////////////////////////////////////////////////////////////////////////
  /*
  * DON'T TOUCH 
  * 
  */
  ////////////////////////////////////////////////////////////////////////////////////////////////
}
