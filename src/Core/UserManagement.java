package Core;

import Model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManagement {
    
    private ArrayList<User> users = new ArrayList<>();
    private final Scanner in = new Scanner(System.in);

    public UserManagement() {
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    public void admin(String username, String password) {
        User admin = new User ("Matias", "Figueroa", "admin", 25, "admin123", Boolean.TRUE);
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("Admin added successfully!");
        } else {
            System.out.println("Incorrect username or password. Admin not added.");
        }
    }
    
    public User procedureUser (){
        String id;
        User user = null;

        do {
            System.out.print("Introduce your ID: ");
            id = in.next();
            user = findUser(id);
            if (user == null){ // Checking if the user exist
                System.out.println("The user does not match in the system. Would you like to add a new one?");
                System.out.println("Enter '1' if desired, '2' to start over");
                Integer option  = in.nextInt();
                 switch (option) {
                case 1:
                    user = signIn();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
            } else{
                user = logIn(user);
            }
        } while (user == null);

        return user;
    }
    

    public User signIn() { // creating an user
        
        String name, lastName, id, pswd;
        Integer age;      

        System.out.print("Introduce your name: ");
        name = in.next();
        System.out.print("Introduce your last name: ");
        lastName = in.next();
        System.out.print("Introduce your ID: ");
        id = in.next();
        System.out.print("Introduce your age: ");
        age = in.nextInt();
        System.out.print("Introduce your password: ");
        pswd = in.next();

        User user = new User(name, lastName, id, age, pswd, Boolean.FALSE);
        users.add(user);
        return user;
    }

    public void userMenu() {

        
    }

    public void adminMenu() {
        System.out.println("ADMIN MENU.\n Press 1. To log in.\n 2. Create an admin");
        Integer admin = in.nextInt();
        switch (admin){
            case 1: 
            case 2: 
        }
        System.out.print("Introduce your ID: ");
        String id = in.next();
        System.out.print("Introduce your password: ");
        String pswd = in.next();

    }

    public User logIn(User user) { // user log in

        System.out.print("Enter your password: ");
        String enteredPswd = in.next();

            if (user.getPassword().equals(enteredPswd)) {
                System.out.println("Login successful!\n " + "Welcome back, " + user.getLastName() + ", " + user.getName());
            } else {
                System.out.println("Login failed. Please try again.");
                user = null;
            }   
        return user;
    }

    /*    System.out.print("Are you an admin? choose an option: 1. Yes \n2.No");
        String admin = in.next();
        switch (admin) {
            case "1":
                adminMenu(); // display menu for admin
            case "2":
                userMenu(); // display menu for USER
        }
    }*/

    public User findUser(String id) { // find the user ID using toFind method
        User toFind = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                toFind = users.get(i);  
            }
        }
        return toFind;
    }
}
