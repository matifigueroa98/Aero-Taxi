package DAO;

import Model.Airplanes.Airplane;
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
    public void save(Airplane airplane) {
        retrieveData();
        try {
            airplanes.add(airplane);
            objMapper.writeValue(file, airplanes); // save airplane to json file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Airplane> findAll() {
       
        return null;
       
    }

    @Override
    public Airplane findById(String id) {
       
        return null;
       
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
