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


            String username = controller.getPanelHandler().getLoginView().getUsername().getText();
            String password = new String (controller.getPanelHandler().getLoginView().getPassword().getPassword());
            System.out.println(username);
            System.out.println(password);

            if (controller.getRepository().verifyAdminPin(username, password)) {
                controller.getPanelHandler().changeToSearchPanel();
            } else {
                controller.getPanelHandler().getLoginView().getErrorText().setText("Felaktig inloggning");
            }
        }
    }
}
