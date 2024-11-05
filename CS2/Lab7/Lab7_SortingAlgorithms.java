package Lab7;

// Evan Horn
//[CS2401]

import Lab7.UI.*;
public class Lab7_SortingAlgorithms {

	public static String sortingType = "Null";
	public static int currentFrame = -1;
	public static int sortingIteration = 0;
	public static int animationSpeed = 30;

	public static void setSortingType(String sortingtype, Concert[] concerts){
		finishSortingAlgorithms(concerts);
		sortingType = sortingtype;
		currentFrame = -1;
		if(sortingType.contains("Insertion")){
			sortingIteration = 1;
			return;
		}
		sortingIteration = 0;
	}

	public static void runSortingAlgorithms(Concert[] concerts){
		// check if there is a sorting type
		if(sortingType == "Null"){
			return;
		}

		// increment animation frame counter
		currentFrame++;

		// check if the animation frame matches the speed
		if(currentFrame%animationSpeed!=0){
			return;
		}

		// run the sorting algorithm which matches the sorting type
		Sort(concerts);
	}

	public static void Sort(Concert[] concerts){
		switch (sortingType) {
			case "SelectionArtist":
				SelectionSortByArtist(concerts);
			break;
			case "SelectionCapacity":
				SelectionSortByCapacity(concerts);
			break;
			case "SelectionDuration":
				SelectionSortByDuration(concerts);
			break;
			case "InsertionArtist":
				InsertionSortByArtist(concerts);
			break;
			case "InsertionCapacity":
				InsertionSortByCapacity(concerts);
			break;
			case "InsertionDuration":
				InsertionSortByDuration(concerts);
			break;
			case "MergeCapacity":
				MergeSortByCapacity(concerts);
			break;
			case "MergeDuration":
				MergeSortByDuration(concerts);
			break;
		}
	}

	public static void finishSortingAlgorithms(Concert[] concerts){
		while (sortingType!="Null") {
			Sort(concerts);
		}
		finishAnimations(concerts);
	}

	private static void finishAnimations(Concert[] concerts){
		for (Concert concert : concerts) {
			concert.moveToFinalPos();
		}
	}


	public static void setIDs(Concert[] concerts){
		for (int i = 0; i < concerts.length; i++) {
			concerts[i].setID(i+1);
		}
	}

	public static void printConcerts(Concert[] concerts){
		System.out.println(String.format("| %-20s | %-7s | %-6s | %-4s |", "====================","=======","======","===="));
		for (Concert concert : concerts) {
			System.out.println(concert.toString());
		}
		System.out.println(String.format("| %-20s | %-7s | %-6s | %-4s |", "====================","=======","======","===="));
	}

	//#region   Selection

	/**
	 * Change the body of this method to arrange the Concert
	 * objects in ascending order of duration.
	 * The method must use Selection Sort.
	 * Do NOT change the method header.
	 * Consider method overloading.
	 * 
	 * @param allConcerts
	 */

	public static boolean artistIsLessThan(String a1, String a2){
		String shorterString = a1;
		if(a2.length()<a1.length()){
			shorterString = a2;
		}
		for (int i = 0; i < shorterString.length(); i++) {
			if(a1.toLowerCase().charAt(i)<a2.toLowerCase().charAt(i)){
				return true;
			} else if (a1.toLowerCase().charAt(i)>a2.toLowerCase().charAt(i)) {
				return false;
			}
		}
		return false;
	}

	public static void swap(Concert[] concerts,int c1, int c2){
		Concert temp = new Concert(concerts[c1]);
		concerts[c1] = concerts[c2];
		concerts[c2] = temp;
	}

	public static void SelectionSort(int[] arr){
        // loop over every element except for the last element because it will always be sorted
        for (int i = 0; i < arr.length-1; i++) {
            // The index for the smallest element in the array
            int minElement = i;
            //find the smallest element
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElement]) {
                    // Update the index of the smallest element
                    minElement = j;
                }
            }
            //move the element to its correct position
            int temp = arr[i];
            arr[i] = arr[minElement];
            arr[minElement] = temp; 
        }
    }

	public static void SelectionSortByDuration(Concert[] allConcerts) {
		//check if animation ended
		if(sortingIteration>=allConcerts.length-1){
			sortingType = "Null";
			return;
		}

		// run 1 iteration of swapping
		int minElement = sortingIteration;
		for (int j = sortingIteration + 1; j < allConcerts.length; j++) {
			if (allConcerts[j].getDuration() < allConcerts[minElement].getDuration()) {
				minElement = j;
			}
		}
		swap(allConcerts, sortingIteration, minElement);
		setIDs(allConcerts);

		// iteratate the animation
		sortingIteration++;
	}

	public static void SelectionSortByCapacity(Concert[] allConcerts) {
		//check if animation ended
		if(sortingIteration>=allConcerts.length-1){
			sortingType = "Null";
			return;
		}
		
		// run 1 iteration of swapping
		int minElement = sortingIteration;
		for (int j = sortingIteration + 1; j < allConcerts.length; j++) {
			if (allConcerts[j].getCapacity() < allConcerts[minElement].getCapacity()) {
				minElement = j;
			}
		}
		swap(allConcerts, sortingIteration, minElement);
		setIDs(allConcerts);

		// iteratate the animation
		sortingIteration++;
	}

	public static void SelectionSortByArtist(Concert[] allConcerts) {
		//check if animation ended
		if(sortingIteration>=allConcerts.length-1){
			sortingType = "Null";
			return;
		}
		
		// run 1 iteration of swapping
		int minElement = sortingIteration;
		for (int j = sortingIteration + 1; j < allConcerts.length; j++) {
			if (artistIsLessThan(allConcerts[j].getArtist(), allConcerts[minElement].getArtist())) {
				minElement = j;
			}
		}
		swap(allConcerts, sortingIteration, minElement);
		setIDs(allConcerts);

		// iteratate the animation
		sortingIteration++;
	}
	//#endregion

	//#region	Merge Sort

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
		mergeSort(allConcerts, 0, allConcerts.length - 1);
		setIDs(allConcerts);
		sortingType = "Null";
	}

	static void mergeSort(Concert[] allConcerts, int left, int right) {
		if (left < right) {
			int middle = left + (right - left) / 2;
			mergeSort(allConcerts, left, middle);
			mergeSort(allConcerts, middle + 1, right);
			merge(allConcerts, left, middle, right);
		}
	}

	static void merge(Concert[] allConcerts, int left, int middle, int right) {
		int n1 = middle - left + 1;
		int n2 = right - middle;
		Concert[] leftArray = new Concert[n1];
		Concert[] rightArray = new Concert[n2];
		for (int i = 0; i < n1; i++) {
			leftArray[i] = allConcerts[left + i];
		}
		for (int j = 0; j < n2; j++) {
			rightArray[j] = allConcerts[middle + 1 + j];
		}
		int i = 0, j = 0;
		int k = left;
		while (i < n1 && j < n2) {
			if (leftArray[i].getDuration() <= rightArray[j].getDuration()) {
				allConcerts[k] = leftArray[i];
				i++;
			} else {
				allConcerts[k] = rightArray[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			allConcerts[k] = leftArray[i];
			i++;
			k++;
		}
		while (j < n2) {
			allConcerts[k] = rightArray[j];
			j++;
			k++;
		}
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
		setIDs(allConcerts);
		sortingType = "Null";
	}
	
	static void mergeSortByCapacity(Concert[] allConcerts, int left, int right) {
		if (left < right) {
			int middle = left + (right - left) / 2;
	
			mergeSortByCapacity(allConcerts, left, middle);
			mergeSortByCapacity(allConcerts, middle + 1, right);
			mergeByCapacity(allConcerts, left, middle, right);
		}
	}
	
	static void mergeByCapacity(Concert[] allConcerts, int left, int middle, int right) {
		int n1 = middle - left + 1;
		int n2 = right - middle;
		Concert[] leftArray = new Concert[n1];
		Concert[] rightArray = new Concert[n2];
		for (int i = 0; i < n1; i++) {
			leftArray[i] = allConcerts[left + i];
		}
		for (int j = 0; j < n2; j++) {
			rightArray[j] = allConcerts[middle + 1 + j];
		}
		int i = 0, j = 0;
		int k = left;
		while (i < n1 && j < n2) {
			if (leftArray[i].getCapacity() <= rightArray[j].getCapacity()) {
				allConcerts[k] = leftArray[i];
				i++;
			} else {
				allConcerts[k] = rightArray[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			allConcerts[k] = leftArray[i];
			i++;
			k++;
		}
		while (j < n2) {
			allConcerts[k] = rightArray[j];
			j++;
			k++;
		}
	}

	//#endregion

	//#region Insertion Sort

	/**
	 * Change the body of this method to arrange the Concert
	 * objects in ascending order of capacity.
	 * The method must use Insertion Sort.
	 * Do NOT change the method header.
	 * 
	 * @param allConcerts
	 */

	public static boolean artistIsGreaterThan(String a1, String a2){
		String shorterString = a1;
		if(a2.length()<a1.length()){
			shorterString = a2;
		}
		for (int i = 0; i < shorterString.length(); i++) {
			if(a1.toLowerCase().charAt(i)>a2.toLowerCase().charAt(i)){
				return true;
			} else if (a1.toLowerCase().charAt(i)<a2.toLowerCase().charAt(i)) {
				return false;
			}
		}
		return false;
	}

	public static int[] InsertionSort(int[] arr) {
        // insert every item into the array except for the first one
        for (int i = 1; i < arr.length; i++) {
            // the item we are insterting
            int key = arr[i];
            // the index of the largest sorted number
            int j = i-1;
            // repeatedly check if the item at j is greater than the item we are trying to sort
            while(j>=0&&arr[j]>key){
                //slide the item at j forward in the array then decrement j
                arr[j+1]=arr[j];
                j--;
            }
            // insert the item we are trying to sort into its correct spot
            arr[j+1] = key;
        }
        return arr;
    }

	public static void InsertionSortByDuration(Concert[] allConcerts) {
		//check if animation ended
		if(sortingIteration>=allConcerts.length){
			sortingType = "Null";
			return;
		}

		// run 1 iteration of inserting
		Concert temp = allConcerts[sortingIteration];
		int j = sortingIteration-1;
		while(j>=0&&allConcerts[j].getDuration()>temp.getDuration()){
			allConcerts[j+1] = allConcerts[j];
			j--;
		}
		allConcerts[j+1] = temp;
		setIDs(allConcerts);

		// iteratate the animation
		sortingIteration++;
	}

	public static void InsertionSortByCapacity(Concert[] allConcerts) {
		//check if animation ended
		if(sortingIteration>=allConcerts.length){
			sortingType = "Null";
			return;
		}

		// run 1 iteration of inserting
		Concert temp = allConcerts[sortingIteration];
		int j = sortingIteration-1;
		while(j>=0&&allConcerts[j].getCapacity()>temp.getCapacity()){
			allConcerts[j+1] = allConcerts[j];
			j--;
		}
		allConcerts[j+1] = temp;
		setIDs(allConcerts);

		// iteratate the animation
		sortingIteration++;
	}

	public static void InsertionSortByArtist(Concert[] allConcerts) {
		//check if animation ended
		if(sortingIteration>=allConcerts.length){
			sortingType = "Null";
			return;
		}

		// run 1 iteration of inserting
		Concert temp = allConcerts[sortingIteration];
		int j = sortingIteration-1;
		while(j>=0&&artistIsGreaterThan(allConcerts[j].getArtist(), temp.getArtist())){
			allConcerts[j+1] = allConcerts[j];
			j--;
		}
		allConcerts[j+1] = temp;
		setIDs(allConcerts);

		// iteratate the animation
		sortingIteration++;
	}

	//#endregion
}
