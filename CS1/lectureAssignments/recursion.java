package lectureAssignments;


public class recursion {
    public static int exp(int base,int expo){
        if(expo==0){return 1;}
        return(base*exp(base, expo-1)); 
    }

    public static void main(String[] args) {
        System.out.println(exp(3,4));
    }
}
