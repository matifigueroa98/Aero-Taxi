package DAO;

import Model.Airplanes.Airplane;
import Model.Enums.ECity;
import Model.Flight;
import Model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FlightDAO implements IRepositoryFlight {
 
    private ArrayList<Flight> flights = new ArrayList<>();
    private final String path = "resources/flights.json";
    private final ObjectMapper objMapper = new ObjectMapper();
    private final File file = new File(path);

    @Override
    public void save(Flight flight) {
       retrieveData();
       try {
            flights.add(flight);
            objMapper.writeValue(file, flights); // save flight to json file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Flight> findAll() {
       retrieveData();
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }
        return flights;    
    }

    @Override
    public Boolean update(String id, LocalDate departureDate, Airplane airplane, ECity departureCity, 
    ECity arrivalCity, Integer quantityPassengers, ArrayList<User> passengers) {
       
        return null;
       
    }

    @Override
    public Boolean delete(String id) {

        return null;

    }

    @Override
    public Flight findById(String id) {
     
        return null;
     
    }
    
    private void retrieveData() { // Uploading and reading the flights.json file 
        try {
            if (file.exists()) {
                flights = objMapper.readValue(file, new TypeReference<ArrayList<Flight>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
