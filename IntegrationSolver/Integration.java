package IntegrationSolver;

public class Integration {

    static double function1(double x) {
        return (x/Math.cos(x));
    }

    static double function2(double x) {
        return 0;
    }

    static double calculateIntegral(double start,double end,int n){
        double area = 0;
        for(int i = 0; i < n; i++){
            double rectangleWidth = (end-start)/n;
            double rectangleX = start + (i * rectangleWidth);
            double rectangleHeight = function1(rectangleX) - function2(rectangleX);
            area = area + rectangleWidth * rectangleHeight;;
        }
        return(area);
    }

    public static void main(String[] args)
    {
        double start = 0;
        double end = Math.PI/3;
        int numberOfIterations = 1000000;
        System.out.println(calculateIntegral(start,end,numberOfIterations));

    }
}
