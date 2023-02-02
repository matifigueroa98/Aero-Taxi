package Model.Airplanes;

import Model.Enums.*;

public class SilverFleet extends Airplane implements ICateringService {

    public SilverFleet() {
    }

    public SilverFleet(String id, Double fuelCapacity, Double costPerKm, Integer passengerCapacity, Double 
    maximumSpeed, EPropulsionType propulsionType, EAirplaneRate airplaneRate, EAirplaneRate fixedRate) {
     super(id, fuelCapacity, costPerKm, passengerCapacity, maximumSpeed, propulsionType, EAirplaneRate.SILVER, EAirplaneRate.AIRPLANE);
    }

    @Override
    public String cateringService (){
        return "catering service";
    }
}