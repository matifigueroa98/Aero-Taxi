package Core;

import Model.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Admin {
    
    ArrayList<User> users = new ArrayList<>();

    Scanner in = new Scanner(System.in);

    public Admin() {
    }

    public void createAnUser() {

        System.out.print("Introduce your name: ");
        String name = in.next();
        System.out.print("Introduce your last name: ");
        String lastName = in.next();
        System.out.print("Introduce your ID: ");
        String id = in.next();
        System.out.print("Introduce your age: ");
        Integer age = in.nextInt();
        System.out.print("Introduce your password: ");
        String pswd = in.next();

        User user = new User(name, lastName, id, age, pswd, Boolean.FALSE);
        users.add(user);
    }

    public void userMenu() {

    }

    public void adminMenu() {
        System.out.println("ADMIN MENU.\n Press 1. To log in.\n 2. Create an admin");

        System.out.print("Introduce your ID: ");
        String id = in.next();
        System.out.print("Introduce your password: ");
        String pswd = in.next();

    }

    public void logIn() { // user log in

        System.out.print("Enter your ID: ");
        String enteredId = in.next();
        System.out.print("Enter your password: ");
        String enteredPswd = in.next();

        Boolean isLoggedIn = false;

        for (User user : users) {
            if (user.getId().equals(enteredId) && user.getPassword().equals(enteredPswd)) {
                System.out.println("Login successful!\n " + "Welcome back, " + user.getLastName() + ", " + user.getName());
                isLoggedIn = true;
                break;
            } else {
                System.out.println("Login failed. Please try again.");
            }
        }

        System.out.print("Are you an admin? choose an option: 1. Yes \n2.No");
        String admin = in.next();
        switch (admin) {
            case "1":
                adminMenu(); // display menu for admin
            case "2":
                userMenu(); // display menu for USER
        }
    }

    /*  public Boolean checkUser (String username){ 
        if (findUser == true){
            
        }
        for (int i = 0; i< // array admins o usuarios; i++){
        if (username.equals(username)){
          return true;  
        }
        
        return null;
        
    } */
    public void findUser(String username) {
        username = JOptionPane.showInputDialog(null, "Introduce your user name: ");
        for (int i = 0; i < username.length(); i++) {
            if (username.equals(username)) {
                // toFind = username;  
            }
        }
    }
}
