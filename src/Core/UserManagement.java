package Core;

import DAO.*;
import Model.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class UserManagement  {
    
    private UserDAO userDAO;
    private final Scanner in = new Scanner(System.in);

    public UserManagement() {
        this.userDAO = new UserDAO ();
    }
    
    public void admin(String username, String password) {
      //  User admin = new User ("Matias", "Figueroa", "admin", 25, "admin123", Boolean.TRUE);
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
    
    public void userMenu() {

    }
    
    public User procedureUser() {
        String username;
        User user = null;

        do {
            System.out.print("Introduce your username: ");
            username = in.next();
            user = userDAO.findByUsername(username);
            if (user != null) { // Checking if the user exist
                user = logIn(user);
                System.out.println("What would you like to do?\nPress 1 if you want to modify your user");
                System.out.println("Press 2 if you want to delete your user");
                Integer s = in.nextInt();
                switch (s) {
                    case 1:
                        modifyingUser();
                        break;
                    case 2: deleteUser();
                        break;
                }
            } else {
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
            }
        } while (user == null);
        return user;
    }
    
    public void signUp() { // creating an user
        String name, lastName, username, pswd;
        Integer age;
        Scanner input = new Scanner (System.in);

        System.out.print("Introduce your name: ");
        name = input.nextLine();
        System.out.print("Introduce your last name: ");
        lastName = input.nextLine();
        System.out.print("Introduce your username: ");
        username = input.nextLine();
        User userExists = userDAO.findByUsername(username);
        while (userExists != null) {
            System.out.println("The username " + username + " already exists. Please try again!");
            System.out.print("Introduce your username: ");
            username = input.nextLine();
            userExists = userDAO.findByUsername(username);
        }
        System.out.print("Introduce your age: ");
        age = integerValidator();
        while (age < 0) {
            System.out.println("Age must be a positive number.");
            System.out.print("Introduce your age: ");
            age = integerValidator();
        }
        System.out.print("Introduce your password: ");
        pswd = input.nextLine();
        String id = UUID.randomUUID().toString(); // unique ID
        User user = new User(name, lastName, username, id, age, pswd, Boolean.FALSE);
        userDAO.save(user);
        System.out.println("User: " + username + " added successfully!");
    }

    public User logIn(User user) { // user log in

        System.out.print("Enter your password: ");
        String enteredPswd = in.next();

        if (user.getPassword().equals(enteredPswd)) {
            System.out.println("Login successful!\n " + "Welcome back: " + user.getLastName() + ", " + user.getName());
        } else {
            System.out.println("Login failed. Forgot your password? (y/n)");
            String option = in.next();
            if (option.equals("y")) {
                resetPassword();
            } else {
                System.out.println("Login failed. Please try again.");
                user = null;
            }
        }
        return user;
    }
    
    public void resetPassword() {
        Scanner pass = new Scanner (System.in);
        
        System.out.println("please re-enter your username: ");
        String username = pass.nextLine();
        System.out.println("Enter a new password: ");
        String newPassword = pass.nextLine();
        userDAO.updatePassword(username, newPassword);
        System.out.println("Password reset successful!");
    }

    public void deleteUser() {
        String username;
        
        System.out.print("Enter the username of the user you want to delete: ");
        username = in.next();
        Boolean deleteUser = userDAO.delete(username);
        if (deleteUser) {
            System.out.println("The username " + username + " has been deleted successfully.");
        } else {
            System.out.println("The username " + username + " could not be found.");
        }
    }

    public void modifyingUser() {
        String username, name, lastName, password;
        Integer age;
        
        System.out.print("Enter the username of the user you want to modify: ");
        username = in.next();
        User user = userDAO.findByUsername(username);
        if (user != null) {
            System.out.print("Enter the new name: ");
            name = in.next();
            System.out.print("Enter the new last name: ");
            lastName = in.next();
            System.out.print("Enter the new password: ");
            password = in.next();
            System.out.print("Enter the new age: ");
            age = integerValidator();

            Boolean updateUser = userDAO.update(username, name, lastName, password, age);
            if (updateUser) {
                System.out.println("Username: " + username + " information has been successfully updated!");
            } else {
                System.out.println("An error occurred while trying to modify the user.");
            }
            System.out.println("Would you like to change the username?\n1.YES \n2.NO");
            Integer option = in.nextInt();
            switch (option) {
                case 1:
                    Boolean valid = false;
                    while (!valid) {
                        System.out.print("Enter the new username: ");
                        String newUsername = in.next();
                        if (!newUsername.equals(username) && !userDAO.userExists(newUsername)) { // check if the username is not repeatead
                            userDAO.updateUsername(username, newUsername); // updating username
                            System.out.println("The username was successfully updated.");
                            valid = true;
                        } else {
                            System.out.println("ERROR! The new username entered is either the same "
                                    + "as the old username or already exists.");
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
            System.out.println("The username entered is incorrect or the user does not exist.");
            System.out.println("Select the operation you want to perform:\n1.Create an user. \n2.Cancel");
            Integer option = in.nextInt();
            switch (option) {
                case 1:
                    signUp();
                    break;
            }
        }
    }

    private Integer integerValidator() { // validating integer
        Boolean validInput = false;
        Integer number = null;

        while (!validInput) {
            try {
                number = in.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                in.next(); // Clear the buffer
            }
        }
        return number;
    }
}
