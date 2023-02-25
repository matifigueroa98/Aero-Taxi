package Model.Airplanes;

import Model.Enums.*;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("bronze")
public class BronzeFleet extends Airplane {

    public BronzeFleet() {
    }

    public BronzeFleet(Double fuelCapacity, Double costPerKm, Integer passengerCapacity, Double 
     maximumSpeed, EPropulsionType propulsionType, EAirplaneRate airplaneRate, EAirplaneRate fixedRate) {
      super(fuelCapacity, costPerKm, passengerCapacity, maximumSpeed, propulsionType, EAirplaneRate.BRONZE);
    }
}

