import java.io.File;
import java.util.Scanner;
public class Lab1_Horn_Difficult {

    public static int[][] readFileToArray(String fileName){
        try {
            File file = new File(fileName); 
            Scanner dimensionScanner = new Scanner(file);
            // this gets the number of rows in the file
            int rows = 0;
            while(dimensionScanner.hasNextLine()){
                dimensionScanner.nextLine();
                rows++;
            }
            int[][] fileResult = new int[rows][];

            dimensionScanner = new Scanner(file);
            // this gets the number of collumns in each row
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
                String currNum = "";
                String row = dimensionScanner.nextLine();
                int collumnNum = 0;
                for(int i=0;i<row.length();i++){
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
        for (int[] row : array) {
            for (int col = 0; col < row.length; col++) {
                System.out.print(row[col] + ",");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int getDistanceFromEdge(int index, int length){
        if(index<length-index){
            return index;
        }else{
            return length-index;
        }
    }

    public static int[][] getOrbitPattern(int[][] array){
        int numOfRows = array.length;
        int[][] arrayOrbits = new int[numOfRows][];
        for (int i = 0; i < array.length; i++) {
            arrayOrbits[i] = new int[array[i].length];
        }
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
        int numOfRows = orbit.length;
        int longestRow = 0;
        for (int[] row : orbit) {
            if (row.length > longestRow) {
                longestRow = row.length;
            }
        }
        int[] orbitTotals = new int[(int)Math.ceil(Math.min(numOfRows, longestRow)/2.)];
        for (int row = 0;row<numOfRows;row++) {
            int numOfCols = orbit[row].length;
            for(int col = 0;col<numOfCols;col++){
                orbitTotals[orbitPattern[row][col]-1]+=orbit[row][col];
            }
        }
        return orbitTotals;
    }


    public static void main(String[] args) {
        int[][] orbits = readFileToArray("Lab1\\array_jagged.txt");
        print2DArray(orbits);
        int[][] orbitPattern = getOrbitPattern(orbits);
        print2DArray(orbitPattern);
        int[] orbitTotals = calculateOrbitTotals(orbits, orbitPattern);
        System.out.println("Orbit Totals: \n");
        for(int i=0;i<orbitTotals.length;i++){
            if(orbitTotals[i]>0){
                System.out.println((i+1)+": "+orbitTotals[i]);
            }
        }
    }
}
