import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
public class Lab1_Horn_Difficult {

    public static int[][] readFileToArray(String fileName){
        try {
            File file = new File(fileName); 
            Scanner dimensionScanner = new Scanner(file);
            // this gets the number of rows in the file by creating a scanner for the file and then incrementing a value for how many nextLines there are
            int rows = 0;
            while(dimensionScanner.hasNextLine()){
                dimensionScanner.nextLine();
                rows++;
            }
            int[][] fileResult = new int[rows][];

            dimensionScanner = new Scanner(file);
            // this gets the number of collumns in each row by counting the number of commas in each of the lines
            rows = 0;
            while(dimensionScanner.hasNextLine()){
                String row = dimensionScanner.nextLine();
                int collumns = 1;
                for (int i = 0; i < row.length(); i++) {
                    if (row.charAt(i)==','){
                        collumns++;
                    }
                }
                fileResult[rows] = new int[collumns];
                rows++;
            }

            dimensionScanner = new Scanner(file);
            int rowNum = 0;
            // this reads the entire file contructing each individual number and then putting it where it is supposed to be
            while(dimensionScanner.hasNextLine()){
                // this creates an empty string which will be used to store each individual digit
                String currNum = "";
                String row = dimensionScanner.nextLine();
                int collumnNum = 0;
                for(int i=0;i<row.length();i++){
                    // this loops over ever character adding it to the curNum string if it isn't a comma or a space
                    // and if it is a comma it parses the currnum adds it to the final array and then clears the currnum String
                    switch (row.charAt(i)) {
                        case ',' -> {
                            fileResult[rowNum][collumnNum]=Integer.parseInt(currNum);
                            collumnNum++;
                            currNum="";
                        }
                        case ' ' -> {}
                        default -> currNum += row.charAt(i);
                    }
                }
                fileResult[rowNum][collumnNum]=Integer.parseInt(currNum);
                rowNum++;
            }
            dimensionScanner.close();
            return(fileResult);
        } catch (Exception e) {
            System.out.println(e);
            return new int[1][1];
        }
    }
    
    public static void print2DArray(int[][] array){
        // this just prints out the 2d array in a readable fashion
        for (int[] row : array) {
            for (int col = 0; col < row.length; col++) {
                System.out.print(row[col] + ",");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int getDistanceFromEdge(int index, int length){
        //This gets how close an item is to the edge of a range when given the length of the range and its current index in the range 
        return(Math.min(index,length-index));
    }

    public static int[][] getOrbitPattern(int[][] array){
        // This code loops over every item in the 2d array and gets the orbit it is apart of by finding how close it is to the edge of the array
        int numOfRows = array.length;
        // This creates the orbits array and determines how many rows exist in it
        int[][] arrayOrbits = new int[numOfRows][];
        // this loops over every row in the array and gets the length of each row
        for (int i = 0; i < array.length; i++) {
            arrayOrbits[i] = new int[array[i].length];
        }
        // this creates 2 variables for each item in the 
        for (int row = 0;row<numOfRows;row++) {
            int numOfCols = array[row].length;
            for(int col = 0;col<numOfCols;col++){
                int verticalDist = getDistanceFromEdge(row, numOfRows-1);
                int horizontalDist = getDistanceFromEdge(col, numOfCols-1);
                arrayOrbits[row][col] = Math.min(verticalDist, horizontalDist)+1;
            }
        }
        return arrayOrbits;
    }

    public static int[] calculateOrbitTotals(int[][] orbit, int[][] orbitPattern){
        // finds the number of rows in the array
        int numOfRows = orbit.length;
        int longestRow = 0;
        // finds the longest row and sets that to be the number of collumns
        for (int[] row : orbit) {
            if (row.length > longestRow) {
                longestRow = row.length;
            }
        }
        // uses the number of rows and collumns to calculate the largest number of orbits that could exist
        int[] orbitTotals = new int[(int)Math.ceil(Math.min(numOfRows, longestRow)/2.)];

        // uses the orbit pattern and the input array to calculate the totals of each orbit
        for (int row = 0;row<numOfRows;row++) {
            int numOfCols = orbit[row].length;
            for(int col = 0;col<numOfCols;col++){
                orbitTotals[orbitPattern[row][col]-1]+=orbit[row][col];
            }
        }
        return orbitTotals;
    }

    public static int calculateMatchingNumbers(int[] totals){
        // calculates the number of orbits that arent empty
        int numOrbits = 0;
        for(int i = 0; i<totals.length;i++){
            if(totals[i]>0){
                numOrbits++;
            }
        }
        // creates a new array with the value that was just calculated
        int[] newTotals = new int[numOrbits];
        
        // fills in the array with the values that aren't empty
        int j = 0;
        for(int i = 0; i<totals.length;i++){
            if(totals[i]>0){
                newTotals[j] = totals[i];
                j++;
            }
        }
        // sorting the array into ascending order
        Arrays.sort(newTotals);
        int sameTotals = 0;
        // checks each orbit to see if it is the same as the ones beside it in the array
        if(newTotals.length>1){
            if(newTotals[0]==newTotals[1]){
                sameTotals++;
            }
            for (int i = 1; i < newTotals.length-1; i++) {
                if(newTotals[i]==newTotals[i-1]||newTotals[i]==newTotals[i+1]){
                    sameTotals++;
                }
            }
            if(newTotals[newTotals.length-1]==newTotals[newTotals.length-2]){
                sameTotals++;
            }
        }

        return sameTotals;
    }


    public static void main(String[] args) {

        int[][] orbits = readFileToArray("School-Assignments\\CS2\\Lab1\\array4x4-same.txt");
        print2DArray(orbits);

        int[][] orbitPattern = getOrbitPattern(orbits);
        print2DArray(orbitPattern);

        int[] orbitTotals = calculateOrbitTotals(orbits, orbitPattern);
        System.out.println("Orbit Totals: \n");

        // prints out the orbit totals and ignores any empty orbits that were caused in the calculate totals method
        for(int i=0;i<orbitTotals.length;i++){
            if(orbitTotals[i]>0){
                System.out.println((i+1)+": "+orbitTotals[i]);
            }
        }
        System.out.println(calculateMatchingNumbers(orbitTotals)+ " orbits are the same.");
    }
}
