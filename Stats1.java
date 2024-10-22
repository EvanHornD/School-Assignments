import java.util.*;

public class Stats1 {
    public static int[][][] generatePermutations(int numOfPeople) {
        
        // Calculate the total number of permutations (factorial of numOfPeople)
        int numOfPermutations = factorial(numOfPeople);
        // Create the layers
        int[][] layers = new int[numOfPeople][numOfPermutations];
        int repetitions = 1;

        // Fill each layer
        for (int layer = 0; layer < numOfPeople; layer++) {
            int[] currentLayer = new int[numOfPermutations];
            int currentIndex = 0;

            for (int j = 0; j < repetitions; j++) {

                // Create a list of possible remaining integers, and then remove the ones that are already used
                ArrayList<Integer> remainingIntegers = new ArrayList<>();
                for (int r = 0; r < numOfPeople; r++) {
                    remainingIntegers.add(r+1);
                }
                for (int r = 0; r < layer; r++) {
                    remainingIntegers.remove(remainingIntegers.indexOf(layers[r][currentIndex]));
                }

                // Add all of the remaining integers to the layer, each integer being repeated a certain number of times based on the current layer and the total number of people
                for (int n = 0; n < numOfPeople-layer; n++) {
                    for (int i = 0; i < factorial(numOfPeople-(layer+1)); i++) {
                        currentLayer[currentIndex] = remainingIntegers.get(n);
                        currentIndex++;
                    }
                }
            }

            //
            repetitions *= numOfPeople-layer;
            layers[layer]=currentLayer;
        }
        
        // Now we generate the final permutations by selecting one number from each layer
        int[][][] permutations = new int[numOfPermutations][2][numOfPeople/2];
        for (int k = 0; k < numOfPermutations; k++) {
            for (int i = 0; i < numOfPeople/2; i++) {
                permutations[k][0][i] = layers[i][k];
            }
            for (int i = numOfPeople/2; i < numOfPeople; i++) {
                permutations[k][1][i-numOfPeople/2] = layers[i][k];
            }
        }

        return permutations;
    }



    // Utility function to calculate factorial
    private static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        int numOfPeople = 8; // Example with 5 people

        // Generate the layered permutations
        int[][][] permutations = generatePermutations(numOfPeople);

        // Print the generated permutations
        for (int i = 0; i < permutations.length; i++) {
            System.out.println(Arrays.deepToString(permutations[i]));
        }

        // Count the number of permutations where 1 and 2 are next to eachother in the same array and subtracts them from the total
        int possibleCombinations = permutations.length;
        for (int[][] permutation : permutations) {
            for (int[] side : permutation) {
                boolean p0 = side[0]==1||side[0]==2;
                boolean p1 = side[1]==1||side[1]==2;
                if(p0&&p1){
                    possibleCombinations--;
                }else{
                    boolean isNextToEachOther = false;
                    for (int i = 1; i < side.length-1; i++) {
                        p0 = side[i-1]==1||side[i-1]==2;
                        p1 = side[i]==1||side[i]==2;
                        boolean p2 = side[i+1]==1||side[i+1]==2;
                        if(p0&&p1||p1&&p2){
                            possibleCombinations--;
                            isNextToEachOther = true;
                            break;
                        }
                    }
                    if(!isNextToEachOther){
                        p0 = side[side.length-2]==1||side[side.length-2]==2;
                        p1 = side[side.length-1]==1||side[side.length-1]==2;
                        if(p0&&p1){
                            possibleCombinations--;
                        }
                    }
                }
            }
        }

        // the same as other permutations counter but combines the 2 sub arrays for each permutation into 1
        int possibleCombinations2 = permutations.length;
        for (int[][] permutation : permutations) {
            int[] side = new int[permutation[0].length+permutation[1].length];
            for (int i = 0; i < permutation.length; i++) {
                for (int j = 0; j < permutation[i].length; j++) {
                    side[i*permutation[i].length+j]=permutation[i][j];
                }
            }
            boolean p0 = side[0]==1||side[0]==2;
            boolean p1 = side[1]==1||side[1]==2;
            if(p0&&p1){
                possibleCombinations2--;
            }else{
                boolean isNextToEachOther = false;
                for (int i = 1; i < side.length-1; i++) {
                    p0 = side[i-1]==1||side[i-1]==2;
                    p1 = side[i]==1||side[i]==2;
                    boolean p2 = side[i+1]==1||side[i+1]==2;
                    if(p0&&p1||p1&&p2){
                        possibleCombinations2--;
                        isNextToEachOther = true;
                        break;
                    }
                }
                if(!isNextToEachOther){
                    p0 = side[side.length-2]==1||side[side.length-2]==2;
                    p1 = side[side.length-1]==1||side[side.length-1]==2;
                    if(p0&&p1){
                        possibleCombinations2--;
                    }
                }
            
            }
        }
        System.out.println("--------------\nnumber of possible seating arrangments for 8 people: "+permutations.length+"\n--------------");
        System.out.println("number of possible seating arrangments for 8 people if 2 people wont sit next to each other: "+possibleCombinations+"\n--------------");
        System.out.println("number of possible seating arrangments for 8 people if 2 people wont sit next to each other and were sitting in a line: "+possibleCombinations2+"\n--------------");
    }
}
