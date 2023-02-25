package Model.Airplanes;

import Model.Enums.*;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("silver")
public class SilverFleet extends Airplane implements ICateringService {

    public SilverFleet() {
    }

    public SilverFleet(Double fuelCapacity, Double costPerKm, Integer passengerCapacity, Double maximumSpeed, EPropulsionType propulsionType, EAirplaneRate airplaneRate, EAirplaneRate fixedRate) {
        super(fuelCapacity, costPerKm, passengerCapacity, maximumSpeed, propulsionType, EAirplaneRate.SILVER);
    }

    @Override
    public String cateringService() {
        return "catering service";
    }
}
