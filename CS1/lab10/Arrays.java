public class Arrays{
	public static void main(String[] args){
		int[] nums = {10, 27, 91, 42, 12, 72, 39};
		printArray(nums);
		printEvenSum(nums);
		printOddSum(nums);
		printRange(nums);
		print2ndLargest(nums);
	}
	public static void println(String s){System.out.println(s);}
	public static void print(String s){System.out.print(s);}
	
	// Create a method that traverses the array and prints out the contents.
	public static void printArray(int[] arr){
		print("Array contents: ");
		for(int i=0;i<arr.length;i++){print(arr[i]+" ");}
		println("");
	}
	// Create a method that returns the sum of the even indexes. (Not even numbers!)
	public static void printEvenSum(int[] arr){
		int evenSum = 0;
		print("Sum of values at even indexes: ");
		for(int i=0;i<arr.length;i+=2){evenSum+=arr[i];}
		println(evenSum+"");
	}
	// Create a method that returns the sum of all the odd numbers in the array. (Not odd indexes!)
	public static void printOddSum(int[] arr){
		int oddSum = 0;
		print("Sum of all odd numbers: ");
		for(int i=0;i<arr.length;i++){if(arr[i]%2==1){oddSum+=arr[i];}}
		println(oddSum+"");
	}
	// Create a method that returns the range of the array. (Range = max - min)
	public static void printRange(int[] arr){
		int largest = arr[0];
		int smallest = arr[0];
		print("Range of array: ");
		for(int i=1;i<arr.length;i++){if(arr[i]>largest){largest=arr[i];}
		if(arr[i]<smallest){smallest=arr[i];}}
		println((largest-smallest)+"");
	}
	// Create a method that returns the second largest number in the array
	public static void print2ndLargest(int[] arr){
		int largest = arr[0];
		int largestIndex=0;
		int secondLargest = -999999999;
		print("Second largest number: ");
		for(int i=0;i<arr.length;i++){if(arr[i]>largest){largest=arr[i];largestIndex=i;}
		else if(arr[i]>secondLargest&&i!=largestIndex){secondLargest=arr[i];}}
		println(secondLargest+"");
	}
}