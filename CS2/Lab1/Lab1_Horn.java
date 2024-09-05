import java.io.File;
import java.util.Scanner;
public class Lab1_Horn {

    public static int[][] readFileToArray(String fileName){
        try {
            File file = new File(fileName); 
            Scanner dimensionScanner = new Scanner(file);
            int rows = 0;
            int collumns = 1;
            String firstRow = dimensionScanner.nextLine();

            for (int i=0; i<firstRow.length(); i++) {
                if (firstRow.charAt(i)==','){
                    collumns++;
                }
            }
            rows++;

            while(dimensionScanner.hasNextLine()){
                dimensionScanner.nextLine();
                rows++;
            }
            int[][] fileResult = new int[rows][collumns];
            dimensionScanner.close();
            Scanner arrayScanner = new Scanner(file);
            int rowNum = 0;
            String currNum = "";

            while(arrayScanner.hasNextLine()){
                String row = arrayScanner.nextLine();
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
                currNum="";
                rowNum++;
            }
            arrayScanner.close();
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
        int numOfCols = array[0].length;
        int[][] arrayOrbits = new int[numOfRows][numOfCols];
        for (int row = 0;row<numOfRows;row++) {
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
        int numOfCols = orbit[0].length;
        int[] orbitTotals = new int[(int)Math.ceil(Math.min(numOfCols, numOfCols)/2.)];
        for (int row = 0;row<numOfRows;row++) {
            for(int col = 0;col<numOfCols;col++){
                orbitTotals[orbitPattern[row][col]-1]+=orbit[row][col];
            }
        }
        return orbitTotals;
    }


    public static void main(String[] args) {
        int[][] orbits = readFileToArray("CS2\\Lab1\\array.txt");
        print2DArray(orbits);
        int[][] orbitPattern = getOrbitPattern(orbits);
        print2DArray(orbitPattern);
        int[] orbitTotals = calculateOrbitTotals(orbits, orbitPattern);
        System.out.println("Orbit Totals: \n");
        for(int i=0;i<orbitTotals.length;i++){
            System.out.println((i+1)+": "+orbitTotals[i]);
        }
    }
}
