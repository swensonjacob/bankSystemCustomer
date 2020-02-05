package se.nackademin.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewController {

    private Controller controller;

    public LoginViewController(Controller controller) {
        this.controller = controller;

        controller.getPanelHandler().getLoginView().addLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //Utkommenterad av marcus för testsyften
                /*
                String passwordString = new String (panelHandler.getLoginView().getPassword().getPassword());
                String usernameInput = panelHandler.getLoginView().getUsername().getText();
                if (personalNumberExists(usernameInput)) {
                    if (passwordCorrect(usernameInput, passwordString)) {
                        changeToMainMenu();
                        panelHandler.getMainMenuView().getUserLabel().setText("Inloggad som: " +
                                currentCustomer.getFirstName() + " " + currentCustomer.getLastName());
                    } else {
                        panelHandler.getLoginView().getErrorText().setText("Felaktig pinkod");
                        panelHandler.getLoginView().getPassword().setText("");
                    }
                } else {
                    panelHandler.getLoginView().getErrorText().setText("Felaktigt personnummer");
                   panelHandler.getLoginView().getUsername().setText("");
                    panelHandler.getLoginView().getPassword().setText("");
                }
                */

            controller.getPanelHandler().changeToSearchPanel();
        }
    }
}
