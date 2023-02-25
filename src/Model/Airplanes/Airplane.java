package Model.Airplanes;

import Model.Enums.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo( // is used to specify that the type information will be stored as a property called "type"
        use = JsonTypeInfo.Id.NAME, 
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = BronzeFleet.class, name = "bronze"),
    @JsonSubTypes.Type(value = SilverFleet.class, name = "silver"),
    @JsonSubTypes.Type(value = GoldFleet.class, name = "gold")
})

public abstract class Airplane {
    
   private Double fuelCapacity;
   private Double costPerKm; // this value is between $150 and $300
   private Integer passengerCapacity;
   private Double maximumSpeed; // in km/h
   private EPropulsionType propulsionType; // types: reaction, propeller and piston
   private EAirplaneRate airplaneRate; // different rate due to airplane types 

    public Airplane() {
    }

    public Airplane(Double fuelCapacity, Double costPerKm, Integer passengerCapacity, Double maximumSpeed, 
            EPropulsionType propulsionType, EAirplaneRate airplaneRate) {
        this.fuelCapacity = fuelCapacity;
        this.costPerKm = costPerKm;
        this.passengerCapacity = passengerCapacity;
        this.maximumSpeed = maximumSpeed;
        this.propulsionType = propulsionType;
        this.airplaneRate = airplaneRate;
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

    @Override
    public String toString() {
        return "Fuel Capacity: " + fuelCapacity + 
               "Cost per KM: " + costPerKm + 
               "Passenger Capacity: " + passengerCapacity + 
               "Maximum Speed: " + maximumSpeed + 
               "Propulsion Type: " + propulsionType +
               "Airplane Rate: " + airplaneRate;
    } 
}
