package Model;

import Model.Enums.*;
import Model.Airplanes.Airplane;
import java.time.LocalDate;
import java.util.ArrayList;

public class Flight {
    
    private LocalDate date;
    private Airplane airplane; // the passenger has to choose an airplane
    private ECity departureCity; // departure time is when a plane leaves the gate
    private ECity arrivalCity; // arrival time is when the plane pulls up to the gate
    private ArrayList<Integer> passengers = new ArrayList <>();
    private Integer quantityPassengers;
    private Integer fleetCapacity;

    public Flight (){
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public ArrayList<Integer> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Integer> passengers) {
        this.passengers = passengers;
    }

    public Integer getQuantityPassengers() {
        return quantityPassengers;
    }

    public void setQuantityPassengers(Integer quantityPassengers) {
        this.quantityPassengers = quantityPassengers;
    }

    public Integer getFleetCapacity() {
        return fleetCapacity;
    }

    public void setFleetCapacity(Integer fleetCapacity) {
        this.fleetCapacity = fleetCapacity;
    }
}
