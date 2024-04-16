package lectureAssignments;

public class FlightDemo {

    // Print origin and destination of flights array

    public static void printOriginsAndDestinations(Flight[] flights){
        for (int i = 0; i < flights.length; i++) {
            System.out.println("\nFlight number "+(i+1)+"'s origin and destination");
            System.out.println(flights[i].getOrigin());
            System.out.println(flights[i].getDestination());
        }
    }

    
    public static void main(String[] args) {

        // construct flights

        Flight flight1 = new Flight();
        flight1.setFlightNumber(7);
        flight1.setOrigin("The CS Building");
        flight1.setDestination("The UGLC");
        flight1.setDepartureAndArrivalTime("10:01AM - 9:52PM");
        flight1.setDomestic(false);
        flight1.setTicketPrice(2315.32);
        

        Flight flight2 = new Flight("China");
        flight2.setDestination("Mexico");

        Flight flight3 = new Flight("Texas","Britain");

        Flight flight4 = new Flight(2,"Peru","Mississippi","10:30AM - 10:31AM",true,.02);

        // initialize and print flights array

        Flight[] filghtArray = {flight1,flight2,flight3,flight4};
        printOriginsAndDestinations(filghtArray);
    }
}
