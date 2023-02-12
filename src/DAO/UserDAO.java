package DAO;

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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public void save(User user) {
        try {
            retrieveData();
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
    public void update(String id, String name, String lastName, String password, Integer age) {
        retrieveData();
        User updateUser = findByID(id);
        updateUser.setName(name);
        updateUser.setLastName(lastName);
        updateUser.setPassword(password);
        updateUser.setAge(age);
        try {
            objMapper.writeValue(file, users); // the list is saved back to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void updateID(String oldId, String newId) {
        retrieveData();
        User updateUser = findByID(oldId);
        if (updateUser != null) {
            updateUser.setId(newId);
            try {
                objMapper.writeValue(file, users); // the ID is saved back to the file and updated
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean isIdExist(String id) {
    for (User user : users) {
        if (user.getId().equals(id)) {
            return true;
        }
    }
    return false;
}

    @Override
    public Boolean delete(String id) {
        retrieveData();
        Boolean success = false;
        for (User user : users) {
            if (user.getId().equals(id)) {
                users.remove(user);
                success = true;
                try {
                    objMapper.writeValue(file, users); // the list is saved back to the file
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    @Override
    public User findByID(String id) {
        retrieveData();
        User toFind = null;
        for (User user : users) {
            if (user.getId().equals(id)) {
                toFind = user;
            }
        }
        return toFind;
    }

    private void retrieveData() { // Uploading and reading the users.json file
        if (file.exists()) {
            try {
                /*The readValue reads JSON data and map it to a list of User objects stored in the users attribute. 
        The TypeReference specifies the type of the Java object to be created from the JSON data.*/
                users = objMapper.readValue(file, new TypeReference<ArrayList<User>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
                objMapper.writeValue(file, users);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
