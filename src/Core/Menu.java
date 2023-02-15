package Core;

import javax.swing.JOptionPane;

public class Menu {

    public void systemMenu() { // dropping down the menu
        Integer menu;
        JOptionPane.showMessageDialog(null, """
                                          Welcome to the AeroTaxi Company! What would you like?
                                          
                                          1. Log in
                                          2. Create an user
                                          3. TEST
                                          4. EXIT
                                          
                                   """);

        UserManagement manage = new UserManagement(); // TEST PURPOSE
        AeroTaxi test = new AeroTaxi (); // TEST PURPOSE

        menu = Integer.parseInt(JOptionPane.showInputDialog("Choose an option"));
        switch (menu) {
            case 1:
                manage.mainMenu();
                break;
            case 2:
                manage.signUp();
                break;
            case 3:
                test.numberPassengers();
                break;
        }
    }
}
