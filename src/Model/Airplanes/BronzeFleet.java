package Model.Airplanes;

import Model.Enums.*;

public class BronzeFleet extends Airplane {

    public BronzeFleet() {
    }

    public BronzeFleet(String id, Double fuelCapacity, Double costPerKm, Integer passengerCapacity, Double 
     maximumSpeed, EPropulsionType propulsionType, EAirplaneRate airplaneRate, EAirplaneRate fixedRate) {
      super(id, fuelCapacity, costPerKm, passengerCapacity, maximumSpeed, propulsionType, EAirplaneRate.BRONZE, EAirplaneRate.AIRPLANE);
    }
}

