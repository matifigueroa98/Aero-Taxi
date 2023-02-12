package Model;

import java.util.UUID;

public class User {
    
    private String name;
    private String lastName;
    private String username; // Identification for the login ID
    private String id; // works as a unique identifier for the system
    private Integer age;
    private String password;
    private Boolean isAdmin;
    
    public User (){
    }

    public User(String name, String lastName, String username, String id, Integer age, String password, Boolean admin) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.id = UUID.randomUUID().toString();
        this.age = age;
        this.password = password;
        this.isAdmin = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId (String id){
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nLast name: " + lastName + "\nUsername: " + username + "\nID: " + id
                + "\nage: " + age + "\nPassword: " + password + "\nAdmin: " + isAdmin;
    }
}
