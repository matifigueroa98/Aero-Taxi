package Core;

import javax.swing.JOptionPane;

public class Menu {
       public void systemMenu() { // dropping down the menu
        Integer menu;
        JOptionPane.showMessageDialog(null, """
                                          Welcome to the AeroTaxi Company! What would you like to do? 
                                          
                                          1. Create an user
                                          2. TEST
                                   """);
        
        UserManagement user = new UserManagement (); // TEST PURPOSE
        
        menu = Integer.parseInt(JOptionPane.showInputDialog("Choose an option"));
        switch (menu) {
            case 1: 
                user.signUp();
                break;
            case 2: user.procedureUser();
            break;
        }
    }
}
