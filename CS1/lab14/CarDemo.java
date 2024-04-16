package lab14;

import java.util.Scanner;

public class CarDemo {

    // Print Make and Model of Cars array

    public static void printMakesAndModels(Car[] Cars){
        for (int i = 0; i < Cars.length; i++) {
            System.out.println("\nCar number "+(i+1)+"'s Make and Model");
            System.out.println(Cars[i].getMake());
            System.out.println(Cars[i].getModel());
        }
    }

    public static String getInput(Scanner input){
        return(input.nextLine());
    }

    
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        // construct Cars

        Car Car1 = new Car();
        Car1.setYear(2001);
        Car1.setMake("Ford");
        Car1.setModel("TrshMchine");
        Car1.setElectric(false);
        Car1.setCost(2315.32);

        Car Car2 = new Car(1880,"MicroSoft","MicroTransaction");
        Car2.setElectric(true);
        Car2.setCost(222233.99);

        // initialize and print Cars array

        while(true){
            Car[] carArray = {Car1,Car2};
            printMakesAndModels(carArray);
            System.out.println("\nDo you want to edit an existing car or create a new one:\n1. Create a new one\n2.Edit an existing one");

        }

    }
}
