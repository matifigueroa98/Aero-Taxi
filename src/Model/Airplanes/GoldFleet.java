package Model.Airplanes;

import Model.Enums.*;

public class GoldFleet extends Airplane implements ICateringService {
    
    private Boolean wifiConection;

    public GoldFleet() {
    }

    public GoldFleet(Boolean wifiConection, String id, Double fuelCapacity, Double costPerKm, Integer 
    passengerCapacity, Double maximumSpeed, EPropulsionType propulsionType, EAirplaneRate airplaneRate, EAirplaneRate fixedRate) {
        super(id, fuelCapacity, costPerKm, passengerCapacity, maximumSpeed, propulsionType, EAirplaneRate.GOLD, EAirplaneRate.AIRPLANE);
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
