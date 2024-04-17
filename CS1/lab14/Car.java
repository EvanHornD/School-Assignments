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
    public Car(Car car){
        this.year = car.getYear();
        this.make = car.getMake();
        this.model = car.getModel();
        this.isElectric = car.getIsElectric();
        this.cost = car.getCost();
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

    // Car information

    public void printCarInfo(int index){
        System.out.println("------Car "+index+"'s Information------");
        System.out.println("Year: "+this.year);
        System.out.println("make: "+this.make);
        System.out.println("model: "+this.model);
        System.out.println("Electric (true or false): "+this.isElectric);
        System.out.println("Car Price: "+this.cost);
        System.out.println("---------------------------");
    }


}
