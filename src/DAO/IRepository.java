package DAO;

import Model.User;

public interface IRepository {
    
    void save (User user);
    void findAll ();
    Boolean update (String id, String name, String lastName, String password, Integer age);
    Boolean delete (String id);
    User findByUsername (String username);

}
