package DAO;

import Model.Airplanes.Airplane;
import Model.Flight;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
            objMapper.registerModule(new JavaTimeModule()); // to handle the LocalDate type properly
            flights.add(flight);
            objMapper.writeValue(file, flights); // save flight to json file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Flight> findAll(LocalDate flightDate) {
       retrieveData();
        for (Flight flight : flights) {
            if (flight.getDepartureDate().equals(flightDate)){
               System.out.println(flight.toString()); 
            }
        }
        return flights;    
    }

    @Override
    public Boolean findFlightByDate(Airplane airplane, LocalDate flightDate) { 
        retrieveData();
        Boolean foundFlight = false;
        for (Flight flight : flights) {
            if (flight.getDepartureDate().equals(flightDate) && 
                flight.getAirplane().getAirplaneRate().equals(airplane.getAirplaneRate())) {
                foundFlight = true; // airplane is not available on the specified date
            }
        }
        return foundFlight;
    }

    @Override
    public Boolean delete(Long id) {
        retrieveData();
        Flight flightToDelete = findById(id);
        Boolean success = false;
        if (flightToDelete != null) {
            LocalDate today = LocalDate.now();
            LocalDate departureDate = flightToDelete.getDepartureDate();
            if (departureDate.isAfter(today.plusDays(1))) {// cannot cancel a flight with less than 24 hours
                flights.remove(flightToDelete);
                success = true;
                try {
                    objMapper.writeValue(file, flights);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    @Override
    public Flight findById(Long id) {
        retrieveData();
        Flight toFind = null;
        for (Flight flight : flights) {
            if (flight.getId() == id) {
                toFind = flight;
            }
        }
        return toFind;
    }
    
    private void retrieveData() { // Uploading and reading the flights.json file 
        try {
            if (file.exists()) {
                objMapper.registerModule(new JavaTimeModule());
                flights = objMapper.readValue(file, new TypeReference<ArrayList<Flight>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
