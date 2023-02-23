package Model.Airplanes;

import Model.Enums.*;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("gold")
public class GoldFleet extends Airplane implements ICateringService {
    
    private Boolean wifiConection;

    public GoldFleet() {
    }

    public GoldFleet(Boolean wifiConection, Double fuelCapacity, Double costPerKm, Integer 
    passengerCapacity, Double maximumSpeed, EPropulsionType propulsionType, EAirplaneRate airplaneRate, EAirplaneRate fixedRate) {
        super(fuelCapacity, costPerKm, passengerCapacity, maximumSpeed, propulsionType, EAirplaneRate.GOLD, EAirplaneRate.AIRPLANE);
        this.wifiConection = wifiConection;
    }

    @Override
    public String cateringService() {
       return "catering service";
    }

    public Boolean haveWifiOnBoard() { // some airplanes have wifi conection
        return wifiConection;
    }

    public void setWifiConection(Boolean wifiConection) {
        this.wifiConection = wifiConection;
    }
}
