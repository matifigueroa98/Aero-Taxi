package DAO;

import Model.User;
import java.util.ArrayList;

public interface IRepository {

    void save(User user);

    ArrayList<User> findAll();

    Boolean update(String id, String name, String lastName, String password, Integer age);

    Boolean delete(String id);

    User findByUsername(String username);

}
