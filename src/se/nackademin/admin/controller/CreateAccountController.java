package se.nackademin.admin.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountController {

    private Controller controller;

    public CreateAccountController(Controller controller) {
        this.controller = controller;
        controller.getPanelHandler().getCreateAccountView().addListener(new createAccountListener());
    }
    class createAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == controller.getPanelHandler().getCreateAccountView().getBackButton()) {
                changeToAdminMenu();
            } else if (e.getSource() == controller.getPanelHandler().getCreateAccountView().getCreateButton()) {
                controller.getRepository().addNewAccount(controller.getPanelHandler().getCreateAccountView().getAccountName().getText(),
                        controller.getCurrentCustomer().getId());
                changeToAdminMenu();
            }

        }
    }
    private void changeToAdminMenu() {
        controller.getPanelHandler().changeToAdminMenu(controller.getAdminMenuController().getNewMenuButtonListener(),
                controller.getLoansFromCurrentCustomer(), controller.getAccountsFromCurrentCustomer(),
                controller.getAdminMenuController().getloanButtonListener(),
                controller.getAdminMenuController().getAccountButtonListener());
    }
}
