package Core;

import DAO.FlightDAO;
import Model.Enums.ECity;
import Model.Flight;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AeroTaxi {
    
    // the AeroTaxi Company has 3 different types of planes (gold, silver and bronze)
    private FlightDAO flightDAO;
    private final Scanner in = new Scanner(System.in);
    
    public AeroTaxi (){
        this.flightDAO = new FlightDAO ();
    }

    public void newFlight() { // To book a new flight, the user must complete a questionnaire
        String date;
        Integer passengersNumber;
        System.out.println("Indicate the appropriate date to make a flight (dd/mm/yyyy)");
        date = in.next();
        System.out.println(date);
        LocalDate departureDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Choose flight departure city");
        // ECity departure = // departure 
        System.out.println("Choose flight arrival city");
        //  ECity arrival = // arrival
        System.out.print("Enter the number of passengers that will go on the flight: ");
        passengersNumber = in.nextInt();
        // last step: show cost of total flight and the user must confirm to generate the flight
       // Flight flight = new Flight (departureDate, airplane, ECity.BSAS, ECity.BSAS); IMPROVE 
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