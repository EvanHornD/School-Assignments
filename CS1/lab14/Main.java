package lab14;

import java.util.Scanner;

public class Main {

    // Print Make and Model of Cars array

    public static void printMakesAndModels(Car[] Cars){
        for (int i = 0; i < Cars.length; i++) {
            System.out.println("\nCar number "+(i+1)+"'s Make and Model");
            System.out.println(Cars[i].getMake());
            System.out.println(Cars[i].getModel());
        }
    }

    // prints all of the cars information
    public static void printCars(Car[] Cars){
        for (int i = 0; i < Cars.length; i++) {
            Cars[i].printCarInfo(i+1);
        }
    }

    // returns the users next line of input
    public static String getInput(Scanner input){
        return(input.nextLine());
    }

    // parses a String for an integer returns a -1 if its not an integer
    public static int parseIntInput(String input){
        try {
            return(Integer.parseInt(input));
        } catch (Exception e) {
            return(-1);
        }
    }

    // parses a String for an double returns a -1 if its not an double
    public static double parseDoubleInput(String input){
        try {
            return(Double.parseDouble(input));
        } catch (Exception e) {
            return(-1.);
        }
    }

    // parses a String for an boolean returns a array of boolean values the first value is whether or not there is a boolean value the second value is if there is a boolean value
    public static boolean[] parseBooleanInput(String input){
        if(input.toLowerCase().contains("true")){
            return new boolean[]{true,true};
        }
        if(input.toLowerCase().contains("false")){
            return new boolean[]{true,false};
        }
        return new boolean[]{false,false};
    }

    // creates a new array of cars adding all of the old cars to the array and then initiallizes a new car as the last value of the array. returns the new array of cars
    public static Car[] addCarToArray(Car[] Cars,Car newCar){
        Car[] newArray=new Car[Cars.length+1];
        for (int i = 0; i < Cars.length; i++) {
            newArray[i]=Cars[i];
        }
        newArray[newArray.length-1]=new Car(newCar);
        return newArray;
    }

    // creates a new array of cars adding all of the old cars to the array and replaces an existing car by initializing a new one in its place. returns the new array of cars
    public static Car[] replaceCarInArray(Car[] Cars,int CarIndex,Car newCar){
        Car[] newArray=new Car[Cars.length];
        for (int i = 0; i < Cars.length; i++) {
            if(i==CarIndex){
                newArray[i]=new Car(newCar);
            }else{
                newArray[i]=Cars[i];
            }
        }
        return newArray;
    }

    // creates a new car object and then pocedurally sets all of the values of the car using the users input. returns the car with all of the values initialized
    public static Car createNewCar(Scanner input){
        Car newCar = new Car();
        String state = "pickingYear";
        while(state=="pickingYear"){
            System.out.println("what year was it made in?\n (it is an integer)");
            int year = parseIntInput(getInput(input));
            if(year>=0){
                newCar.setYear(year);
                state = "pickingMake";
            }else{
                System.out.println("you made an error in your input, try again.");
            }
        }
        while(state=="pickingMake"){
            System.out.println("what is its make?\n (it is a string)");
            String make = getInput(input);
            if(make!=""){
                newCar.setMake(make);
                state = "pickingModel";
            }else{
                System.out.println("you made an error in your input, try again.");
            }
        }
        while(state=="pickingModel"){
            System.out.println("what is its model?\n (it is a string)");
            String model = getInput(input);
            if(model!=""){
                newCar.setModel(model);
                state = "pickingIsElectric";
            }else{
                System.out.println("you made an error in your input, try again.");
            }
        }
        while(state=="pickingIsElectric"){
            System.out.println("Is it electric?\n (true or false)");
            boolean[] isElectric = parseBooleanInput(getInput(input));
            if(isElectric[0]){
                newCar.setElectric(isElectric[1]);
                state = "pickingCost";
            }else{
                System.out.println("you made an error in your input, try again.");
            }
        }
        while(state=="pickingCost"){
            System.out.println("what is its cost?\n (it is a double)");
            double cost = parseDoubleInput(getInput(input));
            if(cost>=0){
                newCar.setCost(cost);
                state = "end";
            }else{
                System.out.println("you made an error in your input, try again.");
            }
        }
        return(newCar);
    }

    
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        // construct the initial Cars
        Car Car1 = new Car();
        Car1.setYear(2001);
        Car1.setMake("Ford");
        Car1.setModel("TrshMchine");
        Car1.setElectric(false);
        Car1.setCost(2315.32);

        Car Car2 = new Car(1880,"MicroSoft","MicroTransaction");
        Car2.setElectric(true);
        Car2.setCost(222233.99);
        String state = "starting";
        Car[] carArray = {Car1,Car2};

        // prints only the makes and models of the cars, as asked by the instructor
        printMakesAndModels(carArray);

        while(!state.equals("exit")){
            System.out.println("\nDo you want to edit an existing car or create a new one:\n1. Edit an existing car\n2. Create a new car\n3. Exit the program");
            switch (parseIntInput(getInput(userInput))) {
                case 1:
                    state = "editingCar";
                    while(state=="editingCar"){
                        String carString = "";
                        for (int i = 0; i < carArray.length; i++) {
                            carString+="\n Car number "+(i+1)+" the "+carArray[i].getMake()+" "+carArray[i].getModel();
                        }
                        System.out.println("Which car do you want to edit:"+carString);
                        int carIndex = parseIntInput(getInput(userInput));
                        if(carIndex>0&&carIndex<=carArray.length){
                            carArray = replaceCarInArray(carArray, carIndex, createNewCar(userInput));
                            state = "starting";
                        }else{
                            System.out.println("you made an error in your input, try again.");
                        }
                    }
                    printCars(carArray);
                break;

                case 2:
                    carArray = addCarToArray(carArray,createNewCar(userInput));
                    printCars(carArray);
                break;

                case 3:
                    printCars(carArray);
                    state="exit";
                break;

                default:
                    System.out.println("there was and error in your input, try again");
                    printCars(carArray);
                break;
            } 
        }
    }
}
