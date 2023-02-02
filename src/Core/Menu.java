package Core;

import javax.swing.JOptionPane;

public class Menu {
       public void systemMenu() { // dropping down the menu
        int menu;
        JOptionPane.showMessageDialog(null, """
                                          Welcome to the AeroTaxi Company! What would you like to do? 
                                          
                                          1. Create an user
                                          2. 
                                   """);

        menu = Integer.parseInt(JOptionPane.showInputDialog("Choose an option"));
        switch (menu) {
            case 1: 
        }
    }
}
