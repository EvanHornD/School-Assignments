import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
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

    public static int[][] getOrbitPattern(int[][] array){
        int numOfRows = array.length;
        //this creates the orbit array with the correct number of rows
        int[][] arrayOrbits = new int[numOfRows][];
        //this assigns the correct length to each row in the orbit array
        for (int i = 0; i < array.length; i++) {
            arrayOrbits[i] = new int[array[i].length];
        }

        // this loops over every value in the input array calculating its distance from the closest edge and assigning that distance to the orbit that item is apart of
        for (int row = 0;row<numOfRows;row++) {
            int numOfCols = array[row].length;
            for(int col = 0;col<numOfCols;col++){
                arrayOrbits[row][col] = Math.min(Math.min(row,(numOfRows-1)-row), Math.min(col,(numOfCols-1)-col))+1;
            }
        }
        return arrayOrbits;
    }

    public static int[] calculateOrbitTotals(int[][] orbit, int[][] orbitPattern){
        int numOfRows = orbit.length;
        int longestRow = 0;
        // this finds out number of collumns by looping through the arrays and finding the longest row
        for (int[] row : orbit) {
            if (row.length > longestRow) {
                longestRow = row.length;
            }
        }
        // this creates an array which stores the final totals for each orbit 
        // the number of orbits is calculated based on the number of rows and which row is the longest, but there may be less orbits total than calculated
        
        int[] orbitTotals = new int[(int)Math.ceil(Math.min(numOfRows, longestRow)/2.)];

        // this loops though the input array and adds each value to the corresponding orbit that was colculated in the orbit pattern array
        for (int row = 0;row<numOfRows;row++) {
            int numOfCols = orbit[row].length;
            for(int col = 0;col<numOfCols;col++){
                orbitTotals[orbitPattern[row][col]-1]+=orbit[row][col];
            }
        }
        return orbitTotals;
    }


    public static void main(String[] args) {
        // getting and printing the input array
        int[][] orbits = readFileToArray("CS2\\Lab1\\array4x4-same.txt");
        print2DArray(orbits);

        // getting and printing the orbit pattern
        int[][] orbitPattern = getOrbitPattern(orbits);
        print2DArray(orbitPattern);

        // calculating and printing the orbit totals
        int[] orbitTotals = calculateOrbitTotals(orbits, orbitPattern);
        System.out.println("Orbit Totals: ");
        int numOfOrbits = 0;
        for(int i=0;i<orbitTotals.length;i++){
            if(orbitTotals[i]>0){
                numOfOrbits++;
                System.out.println((i+1)+": "+orbitTotals[i]);
            }
        }

        // finding how many orbits have the same total by sorting them and then checking if the next item in the array has the same total
        Arrays.sort(orbitTotals,0 ,numOfOrbits);
        int numOfSameSum = 0;
        // check the first orbit
        if(orbitTotals[0]==orbitTotals[1]){
            numOfSameSum++;
        }
        // check all of the middle orbits
        for(int i=1;i<orbitTotals.length-1;i++){
            if(orbitTotals[i]==orbitTotals[i+1]||orbitTotals[i]==orbitTotals[i-1]){
                numOfSameSum++;
            }
        }
        // check the last orbit
        if(orbitTotals[orbitTotals.length-1]==orbitTotals[orbitTotals.length-2]){
            numOfSameSum++;
        }
        System.out.println("\nThere are: "+numOfSameSum+" orbits with the same total");
    }
}
