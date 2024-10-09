import java.util.Arrays;


public class balancedParenthesis {
    public static boolean balanced(String s){
        int parenthesisParity = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
            case '(':
                parenthesisParity++;
            break;
            case ')':
                parenthesisParity--;
            break;
            }
            if (parenthesisParity<0) {
                return false;
            }
        }
        return(parenthesisParity==0);
    }
    public static boolean doubleDecker(int[] arr){
        if (arr.length<5) {
            return false;
        }
        int doubleDeckerParity = 0;
        int a = arr[0];
        int b = arr[1];

        for(int i=1;i<arr.length-1;i++){
            if(a==arr[i+1]){
                doubleDeckerParity++;
            } else {
                doubleDeckerParity = 0;
            }
            a = b;
            b = arr[i+1];
            if (doubleDeckerParity>2) {
                return true;
            }
        }
        return false;
    }

    public static boolean stringContains(String st1, String st2){
        for(int i = 0;i<st1.length()-(st2.length()-1);i++){
            if(st1.charAt(i)==st2.charAt(0)){
                if(st1.substring(i,i+st2.length()).equals(st2)){
                    return true;
                }
            }
        }
        return false;
    }

    public static int[] mergeSortedArrarys(int[] num1,int[] num2,int m,int n) {
        for (int i = num1.length-1; i>=0; i--) {
            if(m-1>=0&&n-1>=0){
                if(num1[m-1]>num2[n-1]){
                    num1[i]=num1[m-1];
                    m--;
                } else {
                    num1[i]=num2[n-1];
                    n--;
                }
            } else {
                if(m-1>=0){
                    num1[i]=num1[m-1];
                    m--;
                }
                if(n-1>=0){
                    num1[i]=num2[n-1];
                    n--;
                }
            }
        }
        return num1;
    }

    public static void main(String[] args) {
        System.out.println(balanced("(())()()"));
        System.out.println(doubleDecker(new int[]{1,2,1,2,1,2}));
        System.out.println(stringContains("Hlello", "ello"));
        System.out.println(Arrays.toString(mergeSortedArrarys(new int[]{0,0,1,1,0,0}, new int[]{5,6}, 4, 2)));
    }
}
