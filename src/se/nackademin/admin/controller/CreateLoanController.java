package se.nackademin.admin.controller;

import se.nackademin.admin.model.Account;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateLoanController {

    private Controller controller;

    public CreateLoanController(Controller controller) {
        this.controller = controller;
        controller.getPanelHandler().getCreateLoanView().addListener(new CreateLoanListener());

    }

    class CreateLoanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == controller.getPanelHandler().getCreateLoanView().getBackButton()) {
                changeToAdminMenu();
            } else if (e.getSource() == controller.getPanelHandler().getCreateLoanView().getCreateButton()) {
                controller.getRepository().createLoan(controller.getCurrentCustomer().getId(),
                        Integer.parseInt(controller.getPanelHandler().getCreateLoanView().getAmount().getText()),
                        getFirstAccountFromCustomer().getId(),
                        controller.getPanelHandler().getCreateLoanView().getLoanNumbName().getText(),
                        Integer.parseInt(controller.getPanelHandler().getCreateLoanView().getLoanTime().getText()));

                changeToAdminMenu();
            }

        }
    }
    private Account getFirstAccountFromCustomer() {
        return controller.getAccountsFromCurrentCustomer().get(0);

    }

    private void changeToAdminMenu() {
        controller.getPanelHandler().changeToAdminMenu(controller.getAdminMenuController().getNewMenuButtonListener(),
                controller.getLoansFromCurrentCustomer(), controller.getAccountsFromCurrentCustomer(),
                controller.getAdminMenuController().getloanButtonListener(),
                controller.getAdminMenuController().getAccountButtonListener());
    }
}
