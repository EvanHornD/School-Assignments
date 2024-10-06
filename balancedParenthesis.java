

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
    public static void main(String[] args) {
        System.out.println(balanced("(())()()"));
        System.out.println(doubleDecker(new int[]{1,2,1,2,1,2}));
        System.out.println(stringContains("Hlello", "ello"));
    }
}
