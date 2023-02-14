package Model;

import Model.Enums.*;
import Model.Airplanes.Airplane;
import java.time.LocalDate;
import java.util.ArrayList;

public class Flight {

    private LocalDate departureDate; //  plane departure date
    private Airplane airplane; // the passenger has to choose an airplane
    private ECity departureCity; // departure time is when a plane leaves the gate
    private ECity arrivalCity; // arrival time is when the plane pulls up to the gate
    private ArrayList<User> passengers = new ArrayList<>();
    private Integer quantityPassengers;
    private Double totalFlight; // total cost of the flight 

    public Flight() {
    }

    public Flight(LocalDate departureDate, Airplane airplane, ECity departureCity, ECity arrivalCity) {
        this.departureDate = departureDate;
        this.airplane = airplane;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.quantityPassengers = quantityPassengers;
        this.totalFlight = totalFlight;
        distances(); // load distances in km/h
    }
    
    public Integer distances() {
        Integer distance = 0; //km/h
        ECity departure = departureCity;
        ECity arrival = arrivalCity;
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

    public ArrayList<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<User> passengers) {
        this.passengers = passengers;
    }

    public Integer getQuantityPassengers() {
        return quantityPassengers;
    }

    public void setQuantityPassengers(Integer quantityPassengers) {
        this.quantityPassengers = quantityPassengers;
    }

    public Double getTotalFlight() {
        return totalFlight;
    }

    public void setTotalFlight(Double totalFlight) {
        this.totalFlight = totalFlight;
    }

    @Override
    public String toString() {
        return "Flight data:\n" + "Departure Date = " + departureDate + "\nAirplane = " + airplane + 
                "\nDeparture City = " + departureCity + "\nArrival City = " + arrivalCity + "\nPassengers = " + 
                passengers + "\nQuantity Passengers = " + quantityPassengers + "\nTotal Flight = " + totalFlight;
    }
}
