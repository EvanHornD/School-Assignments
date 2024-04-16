package lectureAssignments;

public class HW6 {

    // Q1
    //--------------------------------------------------------------
    public static int multiplyArr(int[] arr, int i){
        if(i>=arr.length){
            return(1);
        }
        return(arr[i]*multiplyArr(arr, i+1));
    }
    //--------------------------------------------------------------

    // Q2
    //--------------------------------------------------------------
    public static boolean checkInArr(int[] arr, int index,int val){
        if(index>=arr.length){
            return(false);
        }
        if(arr[index]==val){
            return(true);
        }
        return(checkInArr(arr, index+1, val));
    }
    //--------------------------------------------------------------

    // Q3
    //--------------------------------------------------------------
    public static int checkNInArr(int[] arr, int index,int val){
        if(index>=arr.length){
            return(0);
        }
        if(arr[index]==val){
            return(1+checkNInArr(arr, index+1, val));
        }
        return(checkNInArr(arr, index+1, val));
    }
    //--------------------------------------------------------------

    // Q4
    //--------------------------------------------------------------
    public static int power(int x,int n){
        if(n<=0){
            return(1);
        }
        return(x*power(x,n-1));
    }
    //--------------------------------------------------------------

    // Q5
    //--------------------------------------------------------------
    public static int checkIndexInArr(int[] arr, int val,int index){
        if(index>=arr.length){
            return(-1);
        }
        if(arr[index]==val){
            return(index);
        }
        return(checkIndexInArr(arr, val, index+1));
    }
    //--------------------------------------------------------------

    // Q6
    //--------------------------------------------------------------
    public static int fib(int index){
        if(index<=1){
            return(index);
        }
        return(fib(index-1)+fib(index-2));
    }
    //--------------------------------------------------------------

    public static void main(String[] args) {
        int[] arr = {1,0,3,0,5};
        System.out.println(multiplyArr(arr, 0));
        System.out.println(checkInArr(arr, 1, 0));
        System.out.println(checkNInArr(arr, 4, 0));
        System.out.println(power(5, 4));
        System.out.println(checkIndexInArr(arr,0,2));
        System.out.println(fib(9));
    }

}
