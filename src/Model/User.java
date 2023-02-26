package Model;

import java.util.UUID;

public class User {
    
    private String name;
    private String lastName;
    private String username; // Identification for the login ID
    private String id; // works as a unique identifier for the system
    private Integer age;
    private String password;
    private boolean isAdmin; // primitive data is placed because otherwise the library is broken
    private double totalSpent;
    private String bestAirplaneRate;
    
    public User (){
    }

    public User(String name, String lastName, String username, String id, Integer age, String password, Boolean isAdmin) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.id = UUID.randomUUID().toString();
        this.age = age;
        this.password = password;
        this.isAdmin = isAdmin;
        this.totalSpent = 0.0;
        this.bestAirplaneRate = "NONE";
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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public String getBestAirplaneRate() {
        return bestAirplaneRate;
    }

    public void setBestAirplaneRate (String bestAirplaneRate) {
        this.bestAirplaneRate = bestAirplaneRate;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nLast name: " + lastName + "\nUsername: " + username + "\nID: " + id
                + "\nAge: "+ age+ "\nAdmin: " + isAdmin + 
                "\nBest Airplane: " + bestAirplaneRate +"\nTOTAL SPENT: " + totalSpent+
                "\n----------------------------------";
    }
}
