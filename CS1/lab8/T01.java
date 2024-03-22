import java.util.Scanner;
class T01{
    public static boolean isPrime(int num){
            boolean isPrime = true;
            if (num ==1){
                isPrime = true;
            } else if(num!=1){
                for (int i = 2; i<=((num/2)+1);i++){
                    if (num%i==0){
                        isPrime = false;
                        break;
                    }
                }
            }

            return isPrime;
    }
    public static void main(String[] args){
        Scanner i01 = new Scanner(System.in);
        System.out.println("Enter a number: ");
        System.out.println("Result " +isPrime(i01.nextInt()));
    }
}

