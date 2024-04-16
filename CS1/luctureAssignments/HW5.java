package luctureAssignments;

import java.util.Arrays;
public class HW5 {
    // 1 -----------------------------------------------------------------------------------------
    public static int multiply(int[] ints){
        int nums = 1;
        for(int i = 0; i < ints.length;i++){nums*=ints[i];}
        return(nums);
    }
    // --------------------------------------------------------------------------------------------
    // 2 -----------------------------------------------------------------------------------------
    public static double average(int[] ints){
        double nums = 0.0;
        for(int i = 0; i < ints.length;i++){nums+=ints[i];}
        return(nums/ints.length);
    }
    // --------------------------------------------------------------------------------------------
    // 3 -----------------------------------------------------------------------------------------
    public static void findInt(int[] ints,int x){
        boolean isNum = false;
        for(int i = 0; i < ints.length;i++){if(ints[i]==x){
            isNum=true;break;}}
        if(isNum){System.out.println("x is in nums");}
        else{System.out.println("x is not in nums");}
    }
    // --------------------------------------------------------------------------------------------
    // 4 -----------------------------------------------------------------------------------------
    public static int numXInNums(int[] ints,int x){
        int timesInNums = 0;
        for(int i = 0; i < ints.length;i++){if(ints[i]==x){
            timesInNums+=1;}}
        return(timesInNums);
    }
    // --------------------------------------------------------------------------------------------
    // 5 -----------------------------------------------------------------------------------------
    public static int totalRow(int[][] ints,int r){
        int total = 0;
        int[] row = ints[r];
        for(int i = 0; i < row.length;i++){total+=row[i];}
        return(total);
    }
    // --------------------------------------------------------------------------------------------
    // 6 -----------------------------------------------------------------------------------------
    public static int totalColumn(int[][] ints,int r){
        int total = 0;
        for(int i = 0; i < ints.length;i++){total+=ints[i][r];}
        return(total);
    }
    // --------------------------------------------------------------------------------------------
    // 7 -----------------------------------------------------------------------------------------
    public static int totalVowels(String string){
        int total = 0;
        char[] vowels = {'A','a','E','e','I','i','O','o','U','u'};
        for(int i = 0; i < string.length();i++){
            for(int j = 0; j < vowels.length;j++){
                if(string.charAt(i)==vowels[j]){total++;break;}}}
        return(total);
    }  
    // --------------------------------------------------------------------------------------------
    // 8 -----------------------------------------------------------------------------------------
    public static boolean checkPassword(String string){
        int totalnums = 0;
        for(int i = 0; i < string.length();i++){
            if(string.charAt(i)<123&&string.charAt(i)>96||string.charAt(i)<91&&string.charAt(i)>64||string.charAt(i)<58&&string.charAt(i)>47){
                if(string.charAt(i)<58&&string.charAt(i)>47){totalnums+=1;}}
            else{return(false);}}
        if(totalnums>1&&string.length()>7){return(true);}
        return(false);
    }  
    // --------------------------------------------------------------------------------------------
    // 9 -----------------------------------------------------------------------------------------
    public static int minValue(int[][] array){
        int minVal = array[0][0];
        for(int i=0;i<array.length;i++){
            for(int ii=0;ii<array[i].length;ii++){
                if(minVal>array[i][ii]){minVal=array[i][ii];}}}
        return(minVal);
    }
    // --------------------------------------------------------------------------------------------
    // 10 -----------------------------------------------------------------------------------------
    public static int[] sortArray(int[] array){
        int temp;
        for(int i=0;i<array.length-1;i++){
            for(int ii=0;ii<array.length-1;ii++){
                if(array[ii] > array[ii+1]){
                    temp = array[ii+1];
                    array[ii+1] = array[ii];
                    array[ii]=temp;}}}
        return(array);
    }
    public static double median(int[] array){
        int[] sortedArray = sortArray(array);
        switch (array.length%2) {
            case 1:return(sortedArray[array.length/2]);
            case 0:return((sortedArray[array.length/2]+sortedArray[(array.length/2)-1])/2.0);
        }
        return(0.0);
    }
    // --------------------------------------------------------------------------------------------
    // 11 -----------------------------------------------------------------------------------------
    public static int numIndex(int[] array,int num){
        for(int i=0;i<array.length;i++){if(array[i]==num){return(i);}}
        return(-1);
    }
    // --------------------------------------------------------------------------------------------
    public static void main(String args[]){
        int[] test = {1,2,3,4,2};
        int test2 = 2;
        int[][] test3 = {{7,2,3},{3,6,4},{2,5,8}};
        String test4 = "eao56iou";
        System.out.println(multiply(test));
        System.out.println(average(test));
        findInt(test,test2);
        System.out.println(numXInNums(test, test2));
        System.out.println(totalRow(test3, test2));
        System.out.println(totalColumn(test3, test2));
        System.out.println(totalVowels(test4));
        System.out.println(checkPassword(test4));
        System.out.println(minValue(test3));
        System.out.println(median(test));
        System.out.println(numIndex(test, test2));
        System.out.println(Arrays.toString(sortArray(test)));
    }
}
