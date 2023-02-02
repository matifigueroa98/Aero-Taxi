package Model.Airplanes;

import Model.Enums.*;

public abstract class Airplane {
    
   private String id;
   private Double fuelCapacity;
   private Double costPerKm; // this value is between $150 and $300
   private Integer passengerCapacity;
   private Double maximumSpeed; // in km/h
   private EPropulsionType propulsionType; // types: reaction, propeller and piston
   private EAirplaneRate airplaneRate; // different rate due to airplane types 
   private EAirplaneRate fixedRate; // fixed rate for each passenger 

    public Airplane() {
    }

    public Airplane(String id, Double fuelCapacity, Double costPerKm, Integer passengerCapacity, 
            Double maximumSpeed, EPropulsionType propulsionType, EAirplaneRate airplaneRate, EAirplaneRate fixedRate) {
        this.id = id;
        this.fuelCapacity = fuelCapacity;
        this.costPerKm = costPerKm;
        this.passengerCapacity = passengerCapacity;
        this.maximumSpeed = maximumSpeed;
        this.propulsionType = propulsionType;
        this.airplaneRate = airplaneRate;
        this.fixedRate = fixedRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(Double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public Double getCostPerKm() {
        return costPerKm;
    }

    public void setCostPerKm(Double costPerKm) {
        this.costPerKm = costPerKm;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Double getMaximumSpeed() {
        return maximumSpeed;
    }

    public void setMaximumSpeed(Double maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }

    public EPropulsionType getPropulsionType() {
        return propulsionType;
    }

    public void setPropulsionType(EPropulsionType propulsionType) {
        this.propulsionType = propulsionType;
    }

    public EAirplaneRate getAirplaneRate() {
        return airplaneRate;
    }

    public void setAirplaneRate(EAirplaneRate airplaneRate) {
        this.airplaneRate = airplaneRate;
    }

    public EAirplaneRate getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(EAirplaneRate fixedRate) {
        this.fixedRate = fixedRate;
    }

    @Override
    public String toString() {
        return "ID: " + id + 
               ", Fuel Capacity: " + fuelCapacity + 
               ", Cost per KM: " + costPerKm + 
               ", Passenger Capacity: " + passengerCapacity + 
               ", Maximum Speed: " + maximumSpeed + 
               ", Propulsion Type: " + propulsionType +
               ", Airplane Rate: " + airplaneRate +
               ", Fixed Rate: " + fixedRate; 
    } 
}
