package participationProblems;

public class discreteMath {

    public static void Function(int n ){
        // base case
        if (n<1){
            return;
        }

        // the work that is being done in the each call of the method is f(n) = n^2 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(i+j);
            }
        }
 
        // the sub divisions on each recursion = a
        // the amount n is being divided by on each recursion = b
        Function(n/2);
        Function(n/2);
        Function(n/2);
    }

    // in The master theorem 
    // a = 3
    // b = 2
    // f(n) = n^2
    // d = f(n)
    // n^log_b(a) = n^1.58496250072......
    // n^log_b(a) < d
    // so we are in big O of d n^2

    //|------------------|
    //|                  |
    //|       N^2        |
    //|                  |
    //|------------------|

    public static void main(String[] args) {
        Function(64);
    }
}
