package Model;

import Model.Enums.*;
import Model.Airplanes.Airplane;
import java.time.LocalDate;
import java.util.UUID;

public class Flight {

    private UUID uuid;
    private long id;
    private LocalDate departureDate; //  plane departure date
    private Airplane airplane; // the passenger has to choose an airplane
    private ECity departureCity; // departure time is when a plane leaves the gate
    private ECity arrivalCity; // arrival time is when the plane pulls up to the gate
//    private User user;
    private Integer passengers;
//    private Double totalFlight; // total cost of the flight 

    public Flight() {
    }

    public Flight(LocalDate departureDate, Airplane airplane, ECity departureCity, ECity arrivalCity, 
             Integer passengers) {
        this.uuid = UUID.randomUUID();
        this.id = uuid.getMostSignificantBits();
        this.departureDate = departureDate;
        this.airplane = airplane;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
       // this.user = user;
        this.passengers = passengers;
      //  this.totalFlight = totalFlight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public ECity getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(ECity departureCity) {
        this.departureCity = departureCity;
    }

    public ECity getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(ECity arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

//    public Double getTotalFlight() {
//        return totalFlight;
//    }
//
//    public void setTotalFlight(Double totalFlight) {
//        this.totalFlight = totalFlight;
//    }
//
//    @Override
//    public String toString() {
//        return "Flight data:\n" + "User = "+ user.getUsername()+"\nDeparture Date = " + departureDate + 
//                "\nAirplane = " + airplane.getType() + 
//                "\nDeparture City = " + departureCity + "\nArrival City = " + arrivalCity + 
//                "\nNumber of Passengers = " + passengers + "\nTotal Flight = $ " + totalFlight;
//    }
}
