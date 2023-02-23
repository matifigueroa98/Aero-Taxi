package DAO;

import Model.Airplanes.Airplane;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class AirplaneDAO implements IRepositoryPlane {

    private ArrayList<Airplane> airplanes = new ArrayList<>();
    private final String path = "resources/airplanes.json";
    private final ObjectMapper objMapper = new ObjectMapper();
    private final File file = new File(path);

    @Override
    public ArrayList<Airplane> findAll() {
      retrieveData();
        for (Airplane airplane : airplanes) {
            System.out.println(airplane.toString());
        }
        return airplanes;
    }

    @Override
    public Airplane findAirplaneByType (String type) {
        retrieveData();
        Airplane toFind = null;
        for (Airplane airplane : airplanes) {
            if (airplane.getClass().getAnnotation(JsonTypeName.class).value().equals(type)) {
                toFind = airplane;
                break;
            }
        }
        return toFind;
    }
    
    private void retrieveData() { // Uploading and reading the airplanes.json file 
        try {
            if (file.exists()) {
                airplanes = objMapper.readValue(file, new TypeReference<ArrayList<Airplane>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
