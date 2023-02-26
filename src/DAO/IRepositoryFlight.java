package DAO;

import Model.Airplanes.Airplane;
import Model.Flight;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IRepositoryFlight {

    void save(Flight flight);

    ArrayList<Flight> findAll(LocalDate flightDate);

    Boolean findFlightByDate(Airplane airplane, LocalDate flightDate);

    Boolean delete(Long id);

    Flight findById(Long id);

}
