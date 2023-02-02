package Model;

public class User {
    
    private String name;
    private String lastName;
    private String id; // Identification for the login ID, works as a username
    private Integer age;
    private String password;
    private Boolean admin;
    
    public User (){
    }

    public User(String name, String lastName, String id, Integer age, String password, Boolean admin) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.age = age;
        this.password = password;
        this.admin = false;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Last name: " + lastName + ", ID: " + id + ", Age: " + age;
    }
}
