
public class inlab4 {

    public static String reverse(String s,int i){
        if(i==s.length()-1){
            return(""+s.charAt(0));}
        return (s.charAt(s.length()-1-i)+reverse(s, i+1));
    }

    public static boolean isPalindrome(String s, int i){
        if(i==s.length()/2){
            return true;}
        if(!(s.charAt(s.length()-1-i)==s.charAt(i))){
            return(false);}
        return isPalindrome(s, i+1);
    }

    public static void main(String[] args) {
        System.out.println(reverse("bruh",0));
        System.out.println(isPalindrome("tacocat", 0));
    }

}
