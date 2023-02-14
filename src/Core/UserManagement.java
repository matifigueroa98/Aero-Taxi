package Core;

import DAO.*;
import Model.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class UserManagement {

    private UserDAO userDAO;
    private final Scanner in = new Scanner(System.in);

    public UserManagement() {
        this.userDAO = new UserDAO();
    }

    public void adminMenu() {
        System.out.println("----------Welcome to ADMIN MENU----------");
        System.out.println("""
                           what do you want to do?
                           1. Create an user
                           2. Modify an user
                           3. Delete an user
                           4. User List""");
        Integer a = in.nextInt();
        switch (a) {
            case 1:
                signUp();
                break;
            case 2:
                modifyingUser();
                break;
            case 3:
                deleteUser();
                break;
            case 4:
                userDAO.findAll();
                break;
        }
    }

    public void userMenu() {
        
        System.out.println("What would you like to do?\nPress 1 if you want to modify your user");
        System.out.println("Press 2 if you want to delete your user");
        Integer s = in.nextInt();
        switch (s) {
            case 1:
                modifyingUser();
                break;
            case 2:
                deleteUser();
                break;
        }
    }

    public void mainMenu() {
        User user = logIn();
        if (user != null) { 
            System.out.println("Login successful!\n " + "Welcome back: "+ user.getLastName() + ", " + user.getName()+" !");
            if (!Boolean.TRUE.equals(user.getIsAdmin())) { // deploy different menu if its admin
                userMenu();
            } else {
                adminMenu();
            }
        } else {
            System.out.println("The user does not match in the system. Would you like to add a new one?");
            System.out.println("Enter '1' if desired, '2' Reset password, '3' to EXIT");
            Integer option = in.nextInt();
            switch (option) {
                case 1:
                    signUp();
                    break;
                case 2:
                    resetPassword();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            } 
        }
    }

    public void signUp() { // creating an user
        String name, lastName, username, pswd;
        Integer age;
        Scanner input = new Scanner(System.in);

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
        while (age < 0 && age < 100) {// For health reasons, the company does not allow people over 100 years
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

    public User logIn() { // user log in 
        System.out.print("Introduce your username: ");
        String username = in.next();
        System.out.print("Enter your password: ");
        String enteredPswd = in.next();
        User user = userDAO.findByUsername(username);
        if (user != null && !user.getPassword().equals(enteredPswd)) {
            user = null;
        }
        return user;
    }

    public void resetPassword() {
        Scanner pass = new Scanner(System.in);

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
            System.out.println("Select the operation you want to perform:\n1.Create an user \n2.Cancel");
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
