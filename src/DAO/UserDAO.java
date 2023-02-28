package DAO;

import Model.Flight;
import Model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserDAO implements IRepository {

    private ArrayList<User> users = new ArrayList<>();
    private final String path = "resources/users.json";
    private final ObjectMapper objMapper = new ObjectMapper();
    private final File file = new File(path);

    public UserDAO() {
    }

    @Override
    public void save(User user) {
        retrieveData();
        try {
            users.add(user);
            objMapper.writeValue(file, users); // save user to json file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void saveTotalSpent(User user, Flight flight) {
        retrieveData();
        try {
            User userToUpdate = null;
            for (User u : users) {
                if (u.getId().equals(user.getId())) {
                    userToUpdate = u;
                }
            }
            double newTotalSpent = userToUpdate.getTotalSpent() + flight.getTotalFlight();
            userToUpdate.setTotalSpent(newTotalSpent);
            
            String currentBestAirplaneRate = userToUpdate.getBestAirplaneRate();
            String flightAirplaneRate = flight.getAirplane().getAirplaneRate().toString();
            
            if (currentBestAirplaneRate.equals("NONE") || (flightAirplaneRate.equals("BRONZE") && !currentBestAirplaneRate.equals("SILVER"))
                    || (flightAirplaneRate.equals("SILVER") && currentBestAirplaneRate.equals("GOLD"))) {
                userToUpdate.setBestAirplaneRate(flightAirplaneRate);
            }
            
            objMapper.writeValue(file, users); // save total spent to json file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteTotalSpent(User user, Flight flight) {
        retrieveData();
        try {
            User userToUpdate = null;
            for (User u : users) {
                if (u.getId().equals(user.getId())) {
                    userToUpdate = u;
                }
            }
            double newTotalSpent = userToUpdate.getTotalSpent() - flight.getTotalFlight();
            userToUpdate.setTotalSpent(newTotalSpent);
             
            objMapper.writeValue(file, users); // update total spent to json file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> findAll() {
        retrieveData();
        for (User user : users) {
            System.out.println(user.toString());
        }
        return users;
    }

    @Override
    public Boolean update(String username, String name, String lastName, String password, Integer age) {
        retrieveData();
        User updateUser = findByUsername(username);
        Boolean success = false;
        if (updateUser != null) {
            updateUser.setName(name);
            updateUser.setLastName(lastName);
            updateUser.setPassword(password);
            updateUser.setAge(age);

            if (updateUser.getIsAdmin()) { // checking user rol 
                updateUser.setIsAdmin(true);
            } else {
                updateUser.setIsAdmin(false);
            }
            success = true;
            try {
                objMapper.writeValue(file, users); // the list is saved back to the file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }
    
    public Boolean updatePassword(String username, String password) {
        retrieveData();
        User updateUser = findByUsername(username);
        Boolean success = false;
        if (updateUser != null) {
            updateUser.setPassword(password);
            success = true;
            try {
                objMapper.writeValue(file, users); // the password is saved back to the file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }
    
    public void updateUsername(String oldUsername, String newUsername) {
        retrieveData();
        User updateUser = findByUsername(oldUsername);
        if (updateUser != null) {
            updateUser.setUsername(newUsername);
            try {
                objMapper.writeValue(file, users); // the username is saved back to the file and updated
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean userExists (String username) { // method to know if the username is in the database
        Boolean flag = false;
    for (User user : users) {
        if (user.getUsername().equals(username)) {
            flag = true;
        }
    }
        return flag;
}

    @Override
    public Boolean delete(String username) {
        retrieveData();
        User updateUser = findByUsername(username);
        Boolean success = false;
        if (updateUser != null) {
            users.remove(updateUser);
            success = true;
            try {
                objMapper.writeValue(file, users);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    @Override
    public User findByUsername(String username) {
        retrieveData();
        User toFind = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                toFind = user;
            }
        }
        return toFind;
    }

    private void retrieveData() { // Uploading and reading the users.json file 
        try {
            /*The readValue reads JSON data and map it to a list of User objects stored in the users attribute. 
        The TypeReference specifies the type of the Java object to be created from the JSON data.*/
            if (file.exists()) {
                users = objMapper.readValue(file, new TypeReference<ArrayList<User>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
