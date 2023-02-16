package Core;

import DAO.AirplaneDAO;
import DAO.FlightDAO;
import Model.Airplanes.Airplane;
import Model.Airplanes.BronzeFleet;
import Model.Airplanes.GoldFleet;
import Model.Airplanes.SilverFleet;
import Model.Enums.EAirplaneRate;
import Model.Enums.ECity;
import Model.Enums.EPropulsionType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AeroTaxi {
    
    // the AeroTaxi Company has 3 different types of planes (gold, silver and bronze)
    private FlightDAO flightDAO;
    private AirplaneDAO airplaneDAO;
    private final Scanner in = new Scanner(System.in);
    
    public AeroTaxi (){
        this.flightDAO = new FlightDAO ();
        this.airplaneDAO = new AirplaneDAO ();
    }

    public void newFlight() { // STEPS to book a new flight
        LocalDate departureDate; // indicate desired date
        ECity departure, arrival; // User has to choose the cities of departure and arrival
        Integer passengers; // User indicate number of passengers 
        Integer distance; // total flight distance
        Airplane airplane; // User has to choose an airplane
        
        departureDate = pickDate();
        departure = departureCity();
        arrival = arrivalCity();

        while (departure == arrival) { // validation so that the user doesn't choose the same destinations
            System.out.println("ERROR: You cannot select the same city as departure and arrival!\n");
            departure = departureCity();
            arrival = arrivalCity();
            distance = distances(departure, arrival); // calculate the distance between both destinations
            System.out.println("The distance between " + departure.getCity() + " and " + arrival.getCity() + " is " + distance + " km.");
        }
        airplane = pickAirplane();
        passengers = numberPassengers();
        
//         
//         last step: show cost of total flight and the user must confirm to generate the flight
      //  Flight flight = new Flight (departureDate, airplane, departure, arrival);  
    }
    
    public Airplane pickAirplane() {
        String airplaneType;
        Airplane airplane = null;
        
        while (airplane == null) {
            System.out.print("Please type and select 'bronze', 'silver', or 'gold' to choose an airplane: ");
            airplaneType = in.nextLine();
            if (!airplaneType.equals("bronze") && !airplaneType.equals("silver") && !airplaneType.equals("gold")) {
                System.out.println("Invalid airplane type. Please choose 'bronze', 'silver', or 'gold'.");
            } else {
                switch (airplaneType) { // TESTINGGGGGGGGGGGGGGGGGGGGG
                    case "bronze": // como calculo el total del vuelo si tengo la info aca en el switch?
                        airplane = new BronzeFleet("Bronze", 10000.00, 150.00, 0, 700.00,
                        EPropulsionType.REACTION, EAirplaneRate.BRONZE, EAirplaneRate.AIRPLANE);
                        airplaneDAO.save(airplane);
                        break;
                    case "silver":
                        airplane = new SilverFleet();
                        break;
                    case "gold":
                        airplane = new GoldFleet();
                        break;            
                }
            }
        }
        return airplane;
    }
    
    public Integer numberPassengers() { // validating passengers
        Integer passengersNumber = null;
        Boolean validation = false;

        while (!validation) {
            try {
                System.out.print("Enter the number of passengers that will go on the flight: ");
                passengersNumber = in.nextInt();
                if (passengersNumber <= 0) {
                    System.out.println("Invalid input. The number of passengers must be positive.");
                } else {
                    validation = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number!");
                in.nextLine(); // consume the invalid input
            }
        }
        return passengersNumber;
    }
    
    public LocalDate pickDate() { // The user has to choose a date respecting the validations of the system
        LocalDate departureDate = null;
        
        System.out.println("Indicate the date (dd/mm/yyyy) you want to make the FLIGHT: ");
        while (departureDate == null || departureDate.isBefore(LocalDate.now())) {
            try {
                String date = in.next();
                departureDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (departureDate.isBefore(LocalDate.now())) {
                    System.out.println("You cannot select a date in the past. Please enter a future date.");
                }
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("The date was not entered correctly.");
            }
        }
        System.out.println("You have selected the date: " + departureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return departureDate;
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
    
    public Integer distances (ECity departure, ECity arrival) {
        Integer distance = 0; //km/h
        if ((departure == ECity.BSAS && arrival == ECity.CBA) || 
                (departure == ECity.CBA && arrival == ECity.BSAS)) {
            distance = 695;
        } else if ((departure == ECity.BSAS && arrival == ECity.STGO) || 
                (departure == ECity.STGO && arrival == ECity.BSAS)) {
            distance = 1400;
        } else if ((departure == ECity.BSAS && arrival == ECity.MONT) || 
                (departure == ECity.MONT && arrival == ECity.BSAS)) {
            distance = 950; 
        } else if ((departure == ECity.CBA && arrival == ECity.MONT) || 
                (departure == ECity.MONT && arrival == ECity.CBA)){
            distance = 1190;
        } else if ((departure == ECity.CBA && arrival == ECity.STGO) || 
                (departure == ECity.STGO && arrival == ECity.CBA)){
            distance = 1050;
        } else if ((departure == ECity.MONT && arrival == ECity.STGO) || 
                (departure == ECity.STGO && arrival == ECity.MONT)){
            distance = 2100;
        }
        return distance;
    }

    public void confirmFlight (){

    }
    
    public void changeFlight (){
        
    }
    
    public void cancelFlight (){
        
    }
    
    /*public Double totalFlight (){ // MODIFICAR
        EAirplaneRate fixedRate = null;
       
       Double costFlight = (numberPassengers() * 3500) + fixedRate.getRate(); // DEPENDE EL AVION QUE ELIJA DEBO HACER LA CUENTA
       //distances (metodo en flight)*costokm + cantpassengers * 3500 + fixed rate
       return costFlight;
     }*/
}