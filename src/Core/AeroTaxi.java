package Core;

import DAO.AirplaneDAO;
import DAO.FlightDAO;
import DAO.UserDAO;
import Model.Airplanes.Airplane;
import Model.Enums.ECity;
import Model.Flight;
import Model.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AeroTaxi {
    
    // the AeroTaxi Company has 3 different types of planes (gold, silver and bronze)
    private FlightDAO flightDAO;
    private AirplaneDAO airplaneDAO;
    private UserDAO userDAO;
    private final Scanner in = new Scanner(System.in);
    
    public AeroTaxi (){
        this.flightDAO = new FlightDAO ();
        this.airplaneDAO = new AirplaneDAO ();
        this.userDAO = new UserDAO ();
    }

    public void newFlight(User user) { // STEPS to book a new flight
        LocalDate departureDate; // indicate desired date
        ECity departure, arrival; // User has to choose the cities of departure and arrival
        Integer passengers; // User indicate number of passengers 
        Integer distance; // total flight distance
        Airplane airplane = null; // User has to choose an airplane
        Double totalFlight; // Cost of the total flight
        Boolean availableCapacity;
        
        departureDate = pickDate();
        airplane = showAvailableAirplaneByDateAndType(departureDate);
        departure = departureCity();
        arrival = arrivalCity();

        while (departure == arrival) { // validation so that the user doesn't choose the same destinations
            System.out.println("ERROR: You cannot select the same city as departure and arrival!\n");
            departure = departureCity();
            arrival = arrivalCity();
        }

        distance = distances(departure, arrival);
        passengers = numberPassengers();
        availableCapacity = availableFleet(passengers);
        if (availableCapacity) {
            totalFlight = calculateTotalFlight(distance, passengers, airplane);
            Flight flight = new Flight(user, departureDate, airplane, departure, arrival, passengers, totalFlight);
            confirmFlight(flight);
        } 
    }
    
    public void cancelFlight() {
        long id;
        
        System.out.print("Enter the ID of the flight you want to delete: ");
        id = in.nextLong();
        
        Flight flight = flightDAO.findById(id);
        if (flight != null) {
            User user = flight.getUser();
            userDAO.deleteTotalSpent(user, flight);
        } else {
            System.out.println("The Flight ID = " + id + " could not be found.");
        }
        
        Boolean deleteFlight = flightDAO.delete(id);
        if (deleteFlight) {
            System.out.println("The Flight ID = " + id + " has been deleted successfully.");
        } else {
            System.out.println("The Flight ID = " + id + " may not"
                    + " be canceled the day before the flight on the date.");
        }
    }
    
    public void confirmFlight(Flight flight) {
        Scanner input = new Scanner(System.in);
        Boolean flag = false;

        System.out.println(flight.toString());
        do {
            System.out.println("Would you like to confirm your flight? \n1.YES\n2.NO");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    flightDAO.save(flight);
                    System.out.println("The flight has been confirmed");
                    User user = flight.getUser(); // getting user 
                    userDAO.saveTotalSpent(user, flight); // saving total spent by user
                    flag = true;
                    break;
                case "2":
                    System.out.println("The flight has not been confirmed");
                    flag = true;
                    break;
                default:
                    System.out.println("You entered an invalid option");
                    break;
            }
        } while (flag == false);
    }
    
    public Double calculateTotalFlight(Integer distance, Integer passengers, Airplane airplane) {
        Double totalFlightCost = (distance * airplane.getCostPerKm()) + 
                (passengers * 3500) + airplane.getAirplaneRate().getRate();
        return totalFlightCost;
    }
    
    public Airplane showAvailableAirplaneByDateAndType(LocalDate departureDate) {
        Airplane airplane;
        String airplaneType;
        Boolean notAvailable;
        
        do {
            airplaneType = pickAirplane();
            airplane = airplaneDAO.findAirplaneByType(airplaneType);
            if (airplane != null) {
                notAvailable = flightDAO.findFlightByDate(airplane, departureDate);
                if (notAvailable) {
                    System.out.println("THE AIRPLANE YOU CHOSE IS NOT AVAILABLE ON THE SPECIFIED DATE");
                    airplane = null;
                } else {
                    System.out.println("Airplane is AVAILABLE on the specified date\n");
                }
            } else {
                System.out.println("The airplane you selected is not available.");
            }
        } while (airplane == null);
        return airplane;
    }
    
    public String pickAirplane() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please type and select 'bronze', 'silver', or 'gold' to choose an airplane: ");
        String airplaneType = scan.nextLine();
        while (!airplaneType.equals("bronze") && !airplaneType.equals("silver") && !airplaneType.equals("gold")) {
            System.out.println("Invalid airplane type. Please choose 'bronze', 'silver', or 'gold'.");
            System.out.print("Please, try again: ");
            airplaneType = scan.nextLine();
        }
        
        return airplaneType;
    }
    
    public Boolean availableFleet(Integer passengersNumber) {
        String airplaneType;
        Boolean flag;
        System.out.println("CONFIRM your airplane type\n");
        airplaneType = pickAirplane();
        if (airplaneDAO.availableCapacityFleet(airplaneType, passengersNumber)) {
            flag = true;
        } else {
            System.out.println("We don't have airplanes available with that passenger capacity.");
            flag = false;
        }
        return flag;
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
                    validation = true;
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
}