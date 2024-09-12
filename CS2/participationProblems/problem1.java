public class problem1 {
    public static int countEvens(int[][] arr){
        int numEvens = 0;
        for(int r=0;r<arr.length;r++){
            for (int c = 0; c < arr[r].length; c++) {
                if(arr[r][c]%2==0){
                    numEvens++;
                }
            }
        }
        return numEvens;
    }


    public static int countEvensLastDigitIsSix(int[][] arr){
        int numSixes = 0;
        for(int r=0;r<arr.length;r++){
            for (int c = 0; c < arr[r].length; c++) {
                if(arr[r][c]%10==6){
                    numSixes++;
                }
            }
        }
        return numSixes;
    }
    public static void main(String[] args) {
        int[][] nums = {{14,52,2,26,11},{6,4,8,6,9},{11,45,32,67,98},{100,3,54,67,66}};
        System.out.println(countEvens(nums));
        System.out.println(countEvensLastDigitIsSix(nums));
    }
}
