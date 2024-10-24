import java.util.ArrayList;
import java.util.Arrays;

public class Lab6_Horn {

    // Problem 1 - while loop with nested for loop

    // Time complexity: O(nlog(n))
    /*
     * the while loop is only ran log(n) times because j is being multiplied by 2 every iteration instead of being iincreased
     * in the loop there is a loop the runs n times every iteration
     */
    public static void foo1(int[] arr) {
        int j = 1;
        int n = arr.length;
        while (j < n) {
            for (int k = 0; k < arr.length; k++) {
                System.out.print("* ");
            }
            System.out.println("$ ");
            j = j * 2;
        }
        System.out.println();
    }

    // Problem 2 - a for loop

    // Time complexity: O(log(n))
    /*
     * Explanation: the for loop is only ran log(n) times because i is being multiplied by 2 every iteration
     */
    public static void foo2(int[] arr) {
        for (int i = 1; i < arr.length; i *= 2) {
            System.out.print("# ");
        }
        System.out.println();
    }

    // Problem 3 - two for loops

    // Time complexity: O(n)
    /*
     * in this case it is being run n+log(n) times and because n increases faster than log(n) we take n to be the complexity
     */
    public static void foo3(int[] arr) {
        for (int i = 1; i < arr.length; i *= 2) {
            System.out.print("@ ");
        }
        System.out.println();
        for (int j = 0; j < arr.length; j++) {
            System.out.print("& ");
        }
        System.out.println();

    }

    // Problem 4 - a for loop

    // Time complexity: O(n)
    /*
     * it loops over each element of the array
     */
    public static void foo4(int[] arr) {
        for (int j = 0; j < arr.length; j++) {
            System.out.print("% ");
        }
        System.out.println();
    }

    // Problem 5 - two nested for loops looking for two numbers that sum up to
    // target

    // Time complexity: O(n^2)
    /*
     * 2 nestfor loops each iterating linearly based on the length of the array is n^2
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ":\t ");
            for (int j = i + 1; j < nums.length; j++) {
                System.out.print(nums[j] + ", ");
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
            System.out.println();
        }
        return null;
    }

    // Problem 6 - two nested for loops sorting a given array.
    // It is being sorted by first going though the array and assuming the first
    // element is the min index, we then go through the array again skipping the
    // first element and comparing the elements. If it is less than the current
    // minimum element we make that element the current minimum. We then move the
    // minimum element to its correct position by storing element in the current
    // position, making the current position the correct minimum element, and then
    // doing the reverse for the element that was moved. Essentially swapping the
    // elements.

    // Best time complexity: O(n^2)
    // Worse time complexity: O(n^2)
    // Average time complexity: O(n^2)
    /*
     * Explanation for best: the loop will still loop over every element in the array n times whether or not the array is not sorted
     * Explanation for worst: the loop will still loop over every element in the array n times whether or not the array is not sorted
     * Explanation for average: the loop will still loop over every element in the array n times whether or not the array is not sorted
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            System.out.print(arr[i] + ":\t ");
            int min_idx = i;

            for (int j = i + 1; j < n; j++) {
                System.out.print(arr[j] + ", ");
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[min_idx];
            arr[min_idx] = temp;
            System.out.println();
        }
    }

    // Problem 7 - utilize a nested for loop to traverse the array multiple times
    // while sorting

    // Time complexity: O( n^2 )
    /*
     * Explanation: it will always loop over every element n times
     */
    static void bubblesort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }

    // Problem 8.1 - swap two characters from indices i1 and i2

    // Time complexity: O(constant)
    /*
     * only ever runs 3 opperations
     */
    public static void swap(char[] arr, int i1, int i2) {
        char temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
        // write your code here
    }

    // Problem 8.2 - traverse array, swapping values to make combinations of indices
    // recursive call to traverse for each index final swap for backtracking so each
    // permutation is drawn from the original order of the array

    // Time complexity: O(n!)
    /*
     * Explanation: there are n! permutations and we print every single one of them
     */
    public static void printPermutations(char[] arr, int index) {
        //base case
        if (index == arr.length-1) {
            System.out.println(Arrays.toString(arr));
            return;
        }
 
        // array traversal
        for (int i = index; i < arr.length; i++) {
            // swap for each index of the array
            swap(arr, index, i);

            // recursive call
            printPermutations(arr, index+1);

            // final swap for backtracking
            swap(arr, index, i);
        }
    }

    // I did an iterative approach along side the recursive one as a challenge

    public static int[] factorials(int num) {
        int[] factorials = new int[num + 1];
        factorials[0] = 1;
        for (int i = 1; i <= num; i++) {
            factorials[i] = factorials[i - 1] * i;
        }
        return factorials;
    }

    public static char[][] generatePermutations(char[] arr) {
        int length = arr.length;
        int[] factorial = factorials(length);
        int numOfPermutations = factorial[length];
        char[][] permutations = new char[factorial[length]][];

        ArrayList<Integer> elements = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            elements.add(i);
        }

        // Generate each permutation one by one
        for (int permIndex = 0; permIndex < numOfPermutations; permIndex++) {
            ArrayList<Integer> tempElements = new ArrayList<>(elements);
            char[] permutation = new char[length];

            int currentIndex = permIndex;
            for (int layer = 0; layer < length; layer++) {
                int fact = factorial[length - 1 - layer]; 
                int selectedIndex = currentIndex / fact;
                permutation[layer] = arr[tempElements.get(selectedIndex)-1];
                tempElements.remove(selectedIndex);
                currentIndex %= fact; 
            }

            permutations[permIndex] = permutation;
        }
        return permutations;
    }

    // I dont know if it 100% consistent

    public static void main(String[] args) {

        int[] arr1 = fillIntArray(20, 2);
        int[] arr2 = fillIntArray(10, 1);
        int[] arr3 = fillIntArray(40, 5);
        int[] arr4 = fillIntArray(7, -1);
        int[] arr5 = { 64, 25, 12, 22, 11 };
        int[] arr6 = { 64, 34, 25, 12, 22, 11, 90 };
        int[] arr7 = fillIntArray(9, -2);
        char[] arr8 = { 'a', 'b', 'c' };
        char[] arr9 = { 'z', 'y', 'x', 'w', 'v' };
        char[] arr10 = { 'a', 'b', 'c' };
        // twoSum testing
        int[] nums1 = { 2, 7, 11, 15 };
        int target1 = 26;
        int[] nums2 = { 4, 5, 6, 7, 8, 9, 10, 12, 15, 17, 90 };
        int target2 = 107;

        // Problem 1: foo1
        System.out.println("Testing foo1:");
        System.out.println("Input: " + Arrays.toString(arr1));
        foo1(arr1);
        System.out.println("Input: " + Arrays.toString(arr4));
        foo1(arr4);
        System.out.println();

        // Problem 2: foo2
        System.out.println("Testing foo2:");
        System.out.println("Input: " + Arrays.toString(arr1));
        foo2(arr1);
        System.out.println();
        System.out.println("Input: " + Arrays.toString(arr4));
        foo2(arr4);
        System.out.println();

        // Problem 3: foo3
        System.out.println("Testing foo3:");
        System.out.println("Input: " + Arrays.toString(arr3));
        foo3(arr3);
        System.out.println();
        System.out.println("Input: " + Arrays.toString(arr2));
        foo3(arr2);
        System.out.println();

        // Problem 4: foo4
        System.out.println("Testing foo4:");
        System.out.println("Input: " + Arrays.toString(arr5));
        foo4(arr5);
        System.out.println();
        System.out.println("Input: " + Arrays.toString(arr1));
        foo4(arr1);
        System.out.println();

        // Problem 5: twoSum
        System.out.println("Testing twoSum:");
        System.out.println("Input: " + Arrays.toString(nums1) + ", Target: " +
                target1);
        System.out.println("Output: " + Arrays.toString(twoSum(nums1, target1)));
        System.out.println();
        System.out.println("Input: " + Arrays.toString(nums2) + ", Target: " +
                target2);
        System.out.println("Output: " + Arrays.toString(twoSum(nums2, target2)));
        System.out.println();

        // Problem 6: selectionSort
        System.out.println("Testing selectionSort:");
        System.out.println("Input: " + Arrays.toString(arr5));
        selectionSort(arr5);
        System.out.println("Output: " + Arrays.toString(arr5));
        System.out.println();
        System.out.println("Input: " + Arrays.toString(arr4));
        selectionSort(arr4);
        System.out.println("Output: " + Arrays.toString(arr4));
        System.out.println();

        // Problem 7: bubbleSort
        System.out.println("Testing bubbleSort:");
        System.out.println("Input: " + Arrays.toString(arr6));
        bubblesort(arr6);
        System.out.println("Output: " + Arrays.toString(arr6));
        System.out.println();
        System.out.println("Input: " + Arrays.toString(arr7));
        bubblesort(arr7);
        System.out.println("Output: " + Arrays.toString(arr7));
        System.out.println();

        // Problem 8.1: swap
        System.out.println("Testing swap:");
        System.out.println("Input: " + Arrays.toString(arr10) + ", Index1: " + 0 + ", Index2: " + 2);
        swap(arr10, 0, 2);
        System.out.println("Output: " + Arrays.toString(arr10));
        System.out.println();
        System.out.println("Input: " + Arrays.toString(arr9) + ", Index1: " + 1 + ", Index2: " + 4);
        swap(arr9, 1, 4);
        System.out.println("Output: " + Arrays.toString(arr9));
        System.out.println();

        // Problem 8.2: printPermutations
        System.out.println("Testing printPermutations:");
        System.out.println("Input: " + Arrays.toString(arr8));
        printPermutations(arr8, 0);
        System.out.println();
        System.out.println("Input: " + Arrays.toString(arr9));
        printPermutations(arr9, 0);
        System.out.println();

        // iterative permutations solution that I did for fun
        System.out.println();
        System.out.println("Input: " + Arrays.toString(arr9));
        char[][] test = generatePermutations(arr9);
        System.out.println(Arrays.deepToString(test));
        System.out.println();
    }

    /* ------------------ DO NOT TOUCH CODE ------------------ */

    public static int[] fillIntArray(int size, int increments) {
        int[] newArr = new int[size];
        for (int i = 0; i < size; i++) {
            newArr[i] = i * increments;
        }
        return newArr;
    }
}
