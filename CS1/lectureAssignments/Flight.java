package lectureAssignments;

public class Flight {

    // Set attributes

    int flightNumber;
    String origin;
    String destination;
    String departureAndArrivalTime;
    boolean isDomestic;
    double ticketPrice;

    // Constructors

    public Flight() {
    }
    public Flight(String originIn) {
        this.origin = originIn;
    }
    public Flight(String originIn,String destinationIn) {
        this.origin = originIn;
        this.destination = destinationIn;

    }
    public Flight(int flightNumberIn, String originIn, String destinationIn, String departureAndArrivalTimeIn,boolean isDomesticIn, double ticketPriceIn) {
        this.flightNumber = flightNumberIn;
        this.origin = originIn;
        this.destination = destinationIn;
        this.departureAndArrivalTime = departureAndArrivalTimeIn;
        this.isDomestic = isDomesticIn;
        this.ticketPrice = ticketPriceIn;
    }

    // Getters
    
    public int getFlightNumber() {
        return flightNumber;
    }
    public String getOrigin() {
        return origin;
    }    
    public String getDestination() {
        return destination;
    }
    public String getDepartureAndArrivalTime() {
        return departureAndArrivalTime;
    }
    public boolean getIsDomestic() {
        return isDomestic;
    }
    public double getTicketPrice() {
        return ticketPrice;
    }

    //Setters

    public void setFlightNumber(int flightNumberIn) {
        this.flightNumber = flightNumberIn;
    }
    public void setOrigin(String originIn) {
        this.origin = originIn;
    }
    public void setDestination(String destinationIn) {
        this.destination = destinationIn;
    }
    public void setDepartureAndArrivalTime(String departureAndArrivalTimeIn) {
        this.departureAndArrivalTime = departureAndArrivalTimeIn;
    }
    public void setDomestic(boolean isDomesticIn) {
        this.isDomestic = isDomesticIn;
    }
    public void setTicketPrice(double ticketPriceIn) {
        this.ticketPrice = ticketPriceIn;
    }

    // Flight information

    void printFlightInfo(){
        System.out.println("------Flight Information------");
        System.out.println("Number: "+flightNumber);
        System.out.println("Origin: "+origin);
        System.out.println("Destination: "+destination);
        System.out.println("Departure and Arrival times: "+departureAndArrivalTime);
        System.out.println("Domestic (true or false): "+isDomestic);
        System.out.println("Ticket Price: "+ticketPrice);
        System.out.println("------------------------------");
    }
}
