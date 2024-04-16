package lab14;

public class Car {

    // Set attributes

    int year;
    String make;
    String model;
    boolean isElectric;
    double cost;

    // Constructors

    public Car() {
    }
    public Car(int yearIn, String makeIn,String modelIn) {
        this.year = yearIn;
        this.make = makeIn;
        this.model = modelIn;
    }
    public Car(int yearIn, String makeIn, String modelIn,boolean isElectricIn, double costIn) {
        this.year = yearIn;
        this.make = makeIn;
        this.model = modelIn;
        this.isElectric = isElectricIn;
        this.cost = costIn;
    }

    // Getters
    
    public int getYear() {
        return year;
    }
    public String getMake() {
        return make;
    }    
    public String getModel() {
        return model;
    }
    public boolean getIsElectric() {
        return isElectric;
    }
    public double getCost() {
        return cost;
    }

    //Setters

    public void setYear(int yearIn) {
        this.year = yearIn;
    }
    public void setMake(String makeIn) {
        this.make = makeIn;
    }
    public void setModel(String modelIn) {
        this.model = modelIn;
    }
    public void setElectric(boolean isElectricIn) {
        this.isElectric = isElectricIn;
    }
    public void setCost(double costIn) {
        this.cost = costIn;
    }

    // Flight information

    void printCarInfo(){
        System.out.println("------Car Information------");
        System.out.println("Number: "+year);
        System.out.println("make: "+make);
        System.out.println("model: "+model);
        System.out.println("Electric (true or false): "+isElectric);
        System.out.println("Ticket Price: "+cost);
        System.out.println("---------------------------");
    }
}
