public class HW1 {
    public static void main(String[] args) {
        int[] x = new int[3];
        System.out.println(x[0]);

        System.out.println();
        int[] x2 = {1,2,3,4};
        int[] y = x2;
        
        x = new int[2];
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
            
        }
    }
}
