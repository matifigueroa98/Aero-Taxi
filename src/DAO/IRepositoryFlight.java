package DAO;

import Model.Airplanes.Airplane;
import Model.Enums.ECity;
import Model.Flight;
import Model.User;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IRepositoryFlight {

    void save(Flight flight);

    ArrayList<Flight> findAll();

    Boolean update(String id, LocalDate departureDate, Airplane airplane, ECity departureCity, ECity arrivalCity,
            Integer quantityPassengers, ArrayList<User> passengers);

    Boolean delete(String id);

    Flight findById(String id);

}
