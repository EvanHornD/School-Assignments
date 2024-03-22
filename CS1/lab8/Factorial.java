public class Factorial {
    public static int factorial(int n){
        int fact = 1;
        if(n==0){fact=0;}
        for(int i = 1; i<=n;i++){
            fact *= i;
        }
        return fact;
    }
    public static void main(String[] args) {
        System.out.println(factorial(4));
    }
}
