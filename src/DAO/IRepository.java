package DAO;

import Model.User;

public interface IRepository {
    
    void save (User user);
    void findAll ();
    void update ();
    void delete ();
    void findByID ();

}
