package Core;

import java.util.Scanner;

public class AeroTaxi {
    
    // the AeroTaxi Company has 3 different types of planes (gold, silver and bronze)
    private final Scanner in = new Scanner(System.in);
    
    public AeroTaxi (){
    }

    public void newFlight() { // To book a new flight, the user must complete a questionnaire
        String date;
        Integer passengersNumber;
        System.out.println("Indicate the appropriate date to make a flight (dd-mm-yyyy)");
        date = in.next();
        System.out.println(date);
        
        System.out.println("Choose flight departure city");
        // ECity departure = // departure 
        System.out.println("Choose flight arrival city");
        //  ECity arrival = // arrival
        System.out.print("Enter the number of passengers that will go on the flight: ");
        passengersNumber = in.nextInt();
        // last step: show cost of total flight and the user must confirm to generate the flight
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