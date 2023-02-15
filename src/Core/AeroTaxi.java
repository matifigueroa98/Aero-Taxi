package Core;

import DAO.FlightDAO;
import Model.Enums.ECity;
import Model.Flight;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AeroTaxi {
    
    // the AeroTaxi Company has 3 different types of planes (gold, silver and bronze)
    private FlightDAO flightDAO;
    private final Scanner in = new Scanner(System.in);
    
    public AeroTaxi (){
        this.flightDAO = new FlightDAO ();
    }

    public void newFlight() { // To book a new flight, the user must complete a questionnaire
//        String date;
//        Integer passengersNumber;
//        System.out.println("Indicate the appropriate date to make a flight (dd/mm/yyyy)");
//        date = in.next();
//        System.out.println(date);
//        LocalDate departureDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/mm/yyyy"));

        // User has to choose the cities of departure and arrival.
        ECity departure = departureCity();
        ECity arrival = arrivalCity();
        
        while (departure == arrival) { // validation so that the user doesn't choose the same destinations
            System.out.println("ERROR: You cannot select the same city as departure and arrival!\n");
            System.out.println("Choose the city of DEPARTURE:\n");
            departure = pickDestination();
            System.out.println("Choose the city of ARRIVAL:\n");
            arrival = pickDestination();
        }
        
//        System.out.print("Enter the number of passengers that will go on the flight: ");
//        passengersNumber = in.nextInt();
//        System.out.print("Choose an airplane: ");
        // options
        // last step: show cost of total flight and the user must confirm to generate the flight
        // Flight flight = new Flight (departureDate, airplane, ECity.BSAS, ECity.BSAS); IMPROVE 
    }
    
    public ECity pickDestination() {
        ECity destination = null;
        int i = 1; // index

        for (ECity city : ECity.values()) {
            System.out.println(i + ") " + city.getCity());
            i++;
        }
        try {
            int choice = in.nextInt();
            while (choice < 1 || choice > ECity.values().length) { // making sure the user enters a valid number
                System.out.println("Invalid choice. Please enter a number between 1 and " + ECity.values().length + ".");
                choice = in.nextInt();
            }
            destination = ECity.values()[choice - 1];
            System.out.println("You have selected: " + destination.getCity() + ".\n--------------------");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            in.next(); // consume the invalid input
        }
        return destination; // returning city chosen by the user
    }
    
    public ECity departureCity (){
        System.out.println("Choose the city of DEPARTURE:\n");
        ECity departure = pickDestination();
        
        return departure;
    }
    
    public ECity arrivalCity (){
        System.out.println("Choose the city of ARRIVAL:\n");
        ECity arrival = pickDestination();
        
        return arrival;
    }

    public void buyFlight (){

    }
    
    public void changeFlight (){
        
    }
    
    public void cancelFlight (){
        
    }
    
    /*public Double totalFlight (){
      /* distances (metodo en flight)*costokm + cantpassengers * 3500 + fixed rate
       return ;
     }*/
}