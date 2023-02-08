package DAO;

import Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserDAO implements IRepository {

    private ArrayList<User> users = new ArrayList<>();
    private final String path = "resources/users.json";

    public UserDAO() {
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }      

    @Override
    public void save(User user) {
        ObjectMapper objMapper = new ObjectMapper ();
        File file = new File (path);
        try {
            users.add(user);
            objMapper.writeValue(file, users); // object to JSON in file
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void findAll() {
      
    }

    @Override
    public void update() {
      
    }

    @Override
    public void delete() {
       
    }

    @Override
    public void findByID(){
        
        
    }
        
}
