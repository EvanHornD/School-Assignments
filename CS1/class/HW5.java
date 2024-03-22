public class HW5 {
    public static int multiply(int[] ints){
        int nums = 1;
        for(int i = 0; i < ints.length;i++){nums*=ints[i];}
        return(nums);
    }
    public static double average(int[] ints){
        double nums = 0.0;
        for(int i = 0; i < ints.length;i++){nums+=ints[i];}
        return(nums/ints.length);
    }
    public static void findInt(int[] ints,int x){
        boolean isNum = false;
        for(int i = 0; i < ints.length;i++){if(ints[i]==x){isNum=true;break;}}
        if(isNum){System.out.println("x is in nums");}else{System.out.println("x is not in nums");}
    }
    public static int numXInNums(int[] ints,int x){
        int timesInNums = 0;
        for(int i = 0; i < ints.length;i++){if(ints[i]==x){timesInNums+=1;}}
        return(timesInNums);
    }
    public static int totalRow(int[][] ints,int r){
        int total = 0;
        int[] row = ints[r];
        for(int i = 0; i < row.length;i++){total+=row[i];}
        return(total);
    }
    public static int totalColumn(int[][] ints,int r){
        int total = 0;
        for(int i = 0; i < ints.length;i++){total+=ints[i][r];}
        return(total);
    }
    public static int totalVowels(String string){
        int total = 0;
        char[] vowels = {'A','a','E','e','I','i','O','o','U','u'};
        for(int i = 0; i < string.length();i++){
        for(int j = 0; j < vowels.length;j++){
        if(string.charAt(i)==vowels[j]){total++;break;}}}
        return(total);
    }  
    public static boolean checkPassword(String string){
        int totalnums = 0;
        for(int i = 0; i < string.length();i++){
        if(string.charAt(i)<123&&string.charAt(i)>96||string.charAt(i)<91&&string.charAt(i)>64||string.charAt(i)<58&&string.charAt(i)>47){
        if(string.charAt(i)<58&&string.charAt(i)>47){totalnums+=1;}}else{return(false);}}
        if(totalnums>1){return(true);}
        return(false);
    }  
    public static void main(String args[]){
        int[] test = {1,2,3,4,2};
        int test2 = 2;
        int[][] test3 = {{1,2,3},{3,6,4},{2,5,8}};
        String test4 = "eaoisouf";
        System.out.println(multiply(test));
        System.out.println(average(test));
        findInt(test,test2);
        System.out.println(numXInNums(test, test2));
        System.out.println(totalRow(test3, test2));
        System.out.println(totalColumn(test3, test2));
        System.out.println(totalVowels(test4));
    }
}
