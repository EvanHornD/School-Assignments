import java.util.Scanner;

public class HW3 {
    /*public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = 0, total = 0;
        while(n>=0){total = total + n; n = input.nextInt();}
        System.out.println(total);
    }*/

    /*public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int lowerBound = scanner.nextInt();
        int upperBound = scanner.nextInt();
        int sum = 0;

        if (lowerBound>upperBound){System.out.println("try again");}
        else{while(lowerBound!=upperBound){sum = sum + lowerBound; lowerBound++;}}
        System.out.println(sum+upperBound);
    }*/

    /*public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int factorial = 1;

        while(n>0){factorial=factorial*n;n--;}
        System.out.println(factorial);
    }*/

    /*public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double piApprox = 0;
        double frac = 0.;
        while(n>0){
            frac = n;
            switch(n%2){
                case 1:piApprox = piApprox +1/(2*(frac)-1); break; 
                case 0:piApprox = piApprox - (1/((2*frac)-1)); break;
            }
            n--;
        }
        piApprox = 4*piApprox;
        System.out.println("piApprox = " + piApprox);
    }*/

    /*public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String name = "";
        while(!name.contains("END")){
            System.out.println(name);
            System.out.println("Type a name, Type END to stop");
            name = scanner.nextLine();
        }
        System.out.println("I am done");
    }*/

    // public static void main(String[] args){
    //     Scanner scanner = new Scanner(System.in);
    //     int lowerBound = scanner.nextInt();
    //     int upperBound = scanner.nextInt();
    //     while (lowerBound<=upperBound) {
    //         if (lowerBound%3 == 0){System.out.println("UTEP");}
    //         else if (lowerBound%5 == 0){System.out.println("Miners");}
    //         else {System.out.println(lowerBound);}
    //         lowerBound++;
    //     }
    // }

    // public static void main(String[] args) {
    //     Scanner grade = new Scanner(System.in);
    //     double totalGrade = 0;
    //     int numberOfGrades = 0;
    //     int gradeInput = grade.nextInt();
    //     while (gradeInput>0){totalGrade = totalGrade+gradeInput;
    //         gradeInput = grade.nextInt();
    //         numberOfGrades++;}
    //     double average = totalGrade/numberOfGrades;
    //     if (average>=90){System.out.println("A");}
    //     else if (90>average&&average>=80){System.out.println("B");}
    //     else if (80>average&&average>=70){System.out.println("C");}
    //     else if (70>average&&average>=60){System.out.println("D");}
    //     else if (60>average){System.out.println("F");}
    // }

    //public static void main(String[] args){
    //     Scanner scanner = new Scanner(System.in);
    //     int n = scanner.nextInt();
    //     int length = Integer.toString(n).length();
    //     for(int i = 0;i<length;i++){System.out.print(n%10 + " ");n = n/10;}
    // }
  

    // public static void main(String[] args){
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Enter an integer: ");
    //     int n = scanner.nextInt();
    //     while(n!=1){
    //         switch (n%2) {
    //             case 0: n = n/2;
    //                 break;
    //             case 1: n = (n*3)+1;
    //                 break;
    //         }
    //         System.out.println(n);
    //     }
    // }

    // public static void main(String[] args) {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Enter the first integer: ");
    //     int start = scanner.nextInt();
    //     System.out.print("Enter the second integer: ");
    //     int end = scanner.nextInt();
    //     int total = 0;
    //     while(start<end){if(start%2==0){total+=start;}start++;}
    //     System.out.println(total);
    // }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the start year: ");
        int startYear = scanner.nextInt();
        System.out.print("Enter the end year: ");
        int endYear = scanner.nextInt();
        while(startYear<=endYear){
            if(startYear%4==0&&(!(startYear%100==0)||(startYear%400==0))){
                System.out.println(startYear);
            }
            startYear++;
        }
        scanner.close();
    }
}
