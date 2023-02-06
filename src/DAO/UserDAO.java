package DAO;

import Core.UserManagement;
import java.util.ArrayList;

public class UserDAO implements IRepository {

    private ArrayList<UserManagement> users = new ArrayList<>();

    public UserDAO() {
    }

    public ArrayList<UserManagement> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserManagement> users) {
        this.users = users;
    }      

    @Override
    public void save() {
        UserManagement user = new UserManagement();
        users.add(user);
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
