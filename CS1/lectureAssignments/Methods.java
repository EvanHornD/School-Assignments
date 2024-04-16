package lectureAssignments;

public class Methods {
    public static int robotAntennas(int n){
        int antennas = 0;
        for(int i=0;i<n;i++){antennas+=3;}
        return(antennas);
    }
    public static int robotAntennasEO(int n){
        int antennas = 0;
        for(int i=1;i<=n;i++){antennas+=4-(i%2);}
        return(antennas);
    }
    public static int sumDigits(int n){
        int total = 0;
        for(double i=1;i<=String.valueOf(n).length();i++){total+=((n%Math.pow(10.0,i))-total)/Math.pow(10.0,i-1);}
        return(total);
    }
    public static int count7(int n){
        int sevens = 0;
        for(int i=0;i<String.valueOf(n).length();i++){if(String.valueOf(n).charAt(i)=='7'){sevens+=1;};}
        return(sevens);
    }
    public static String changeVowels(String s){
        String vowels = "";
        for(int i=0;i<s.length();i++){
            switch(s.charAt(i)){
                case'a': vowels+='z';break;
                case'e': vowels+='z';break;
                case'i': vowels+='z';break;
                case'o': vowels+='z';break;
                case'u': vowels+='z';break;
                default: vowels+=s.charAt(i);
            }
            if(s.charAt(i)=='7'){};}
        return(vowels);
    }
    public static void printOrderedNums(int a, int b, int c){
        if(a>b){
            if(a>c){
                if(b>c){
                    System.out.println(a+" "+b+" "+c);
                } else {
                    System.out.println(a+" "+c+" "+b);
                }
            } else {
                System.out.println(c+" "+a+" "+b);
            }
        } else if(b>c){
            if(c>a){
                System.out.println(b+" "+c+" "+a);
            } else {
                System.out.println(b+" "+a+" "+c);
            }
        } else {
            System.out.println(c+" "+b+" "+a);
        }

    }
    public static void printAverage(int a, int b, int c, int d, int e){
        System.out.println((a+b+c+d+e)/5.0);
    }
    public static void printCharNum(String s,char c){
        int charNum = 0;
        for(int i=0;i<s.length();i++){if(s.charAt(i)==c){charNum+=1;}}
        System.out.println(charNum);
    }
    public static void printIsValidPassword(String s){
        System.out.println(s.length()>=8);
    }
    public static void printIsMultiple(int n,int m){
        System.out.println(n%m==0);
    }
    public static void printComputeArea(int n){
        System.out.println((n*n)*3.14);
    }
    public static void main(String[] args) {
        printComputeArea(10);
    }
}