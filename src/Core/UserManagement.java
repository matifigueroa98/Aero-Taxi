package Core;

import DAO.*;
import Model.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserManagement  {
    
    private UserDAO userDAO;
    private final Scanner in = new Scanner(System.in);

    public UserManagement() {
        this.userDAO = new UserDAO ();
    }
    
    public void admin(String username, String password) {
        User admin = new User ("Matias", "Figueroa", "admin", 25, "admin123", Boolean.TRUE);
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("Admin added successfully!");
        } else {
            System.out.println("Incorrect username or password. Admin not added.");
        }
    }
    
    public void adminMenu() {
        System.out.println("ADMIN MENU.\n Press 1. To log in.\n 2. Create an admin");
        Integer admin = in.nextInt();
        switch (admin) {
            case 1:
                
                break;
            case 2:
                
                break;
        }
        System.out.print("Introduce your ID: ");
        String id = in.next();
        System.out.print("Introduce your password: ");
        String pswd = in.next();
    }
    
    public User procedureUser (){
        String id;
        User user = null;

        do {
            System.out.print("Introduce your ID: ");
            id = in.next();
            //user = findUser(id);
            if (user == null) { // Checking if the user exist
                System.out.println("The user does not match in the system. Would you like to add a new one?");
                System.out.println("Enter '1' if desired, '2' to start over");
                Integer option = in.nextInt();
                switch (option) {
                    case 1:
                        signUp();
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } else {
                user = logIn(user);
            }
        } while (user == null);
        return user;
    }
    
    public void signUp() { // creating an user

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
        userDAO.save(user);
        System.out.println("User added successfully!");
    }

    public void userMenu() {

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

    public void deleteUser() {
        String id;
        System.out.print("Enter the ID of the user you want to delete: ");
        id = in.next();
        Boolean deleteUser = userDAO.delete(id);
        if (deleteUser) {
            System.out.println("The user with ID " + id + " has been deleted successfully.");
        } else {
            System.out.println("The user with ID " + id + " could not be found.");
        }
    }

    public void modifyingUser() {
        String id, name, lastName, password;
        Integer age;
        System.out.print("Enter the ID of the user you want to modify: ");
        id = in.next();
        User user = userDAO.findByID(id);
        if (user != null) {
            System.out.print("Enter the new name: ");
            name = in.next();
            System.out.print("Enter the new last name: ");
            lastName = in.next();
            System.out.print("Enter the new password: ");
            password = in.next();
            age = getAgeInput();
            userDAO.update(id, name, lastName, password, age);
            System.out.println("User ID: " + id + " information has been successfully updated!");
            System.out.println("Would you like to change the id?\n1.YES \n2.NO");
            Integer option = in.nextInt();
            switch (option) {
                case 1:
                    Boolean valid = false;
                    while (!valid) {
                        System.out.print("Enter the new ID User: ");
                        String newID = in.next();
                        if (!newID.equals(id) && !userDAO.isIdExist(newID)) { // check if the ID is not repeatead
                            userDAO.updateID(id, newID); // updating ID
                            System.out.println("The ID was successfully updated.");
                            valid = true;
                        } else {
                            System.out.println("ERROR! The new ID entered is either the same as the old ID or already exists."
                                    + "Please enter a unique ID.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option selected. Please try again.");
                    break;
            }
        } else {
            System.out.println("The ID entered is incorrect or the user does not exist.");
            System.out.println("Select the operation you want to perform:\n1.Create an user. \n2.Cancel");
            Integer option = in.nextInt();
            switch (option) {
                case 1:
                    signUp();
                    break;
            }
        }
    }

    private Integer getAgeInput() { // to handle the age input
        boolean validInput = false;
        Integer age = null;
        while (!validInput) {
            try {
                System.out.print("Enter the new age: ");
                age = in.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number for the age.");
                in.next(); // Clear the buffer
            }
        }
        return age;
    }
}
