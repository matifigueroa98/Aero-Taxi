package DAO;

import Model.Airplanes.Airplane;
import java.util.ArrayList;

public interface IRepositoryPlane {

    ArrayList<Airplane> findAll();

    Airplane findAirplaneByType(String id);
    
    Boolean availableCapacityFleet (String airplaneRate, Integer passengers);
    
}
