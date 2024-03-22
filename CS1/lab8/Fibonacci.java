public class Fibonacci {
    public static int fibonacciN(int n){
        int i1 = 0, i2 = 0,temp1 = 0,temp2 = 0;
        for(int i = 0; i <= n; i++){
            temp1 = i1+i2;
            temp2 = i1;
            i2 = temp2;
            i1 = temp1;
            if(i==0){i1=1;}
            System.out.println(i2);
        }
        return(i2);
    }
    public static void main(String[] args) {
        fibonacciN(5);
    }
}
