package DAO;

import Model.Airplanes.Airplane;
import java.util.ArrayList;

public interface IRepositoryPlane {

    void save(Airplane airplane);

    ArrayList<Airplane> findAll();

    Airplane findById(String id);
}
