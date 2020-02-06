package se.nackademin.admin.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCustomerController {

    private Controller controller;

    public UpdateCustomerController(Controller controller) {
        this.controller = controller;

        controller.getPanelHandler().getUpdateCustomerView().addListener(new UpdateCustomerListener());
        controller.getPanelHandler().getUpdateCustomerView().addBackButtonListener(new BackToSearchListener());
    }

    class UpdateCustomerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

                if (e.getSource() == controller.getPanelHandler().getUpdateCustomerView().getUpdateButton()) {
                    if (!textFieldEmpty()) {
                       if(controller.getRepository().updateCustomerInfo(controller.getCurrentCustomer().getId(),
                                controller.getPanelHandler().getUpdateCustomerView().getFirstName().getText(),
                                controller.getPanelHandler().getUpdateCustomerView().getLastName().getText(),
                                controller.getPanelHandler().getUpdateCustomerView().getPersonalNumber().getText(),
                                controller.getPanelHandler().getUpdateCustomerView().getPinCode().getText())) {
                           JOptionPane.showMessageDialog(null,"Kund uppdaterad");
                       }
                        controller.getPanelHandler().changeToAdminMenu(controller.getAdminMenuController().getNewMenuButtonListener(),
                                controller.getLoansFromCurrentCustomer(), controller.getAccountsFromCurrentCustomer(),
                                controller.getAdminMenuController().getloanButtonListener(),
                                controller.getAdminMenuController().getAccountButtonListener());

                    } else {
                        controller.getPanelHandler().getCreateCustomerView().getErrorText().setText("Samtliga fält måste fyllas i");
                    }
                } else if (e.getSource() == controller.getPanelHandler().getUpdateCustomerView().getEraseButton()) {
                    if(controller.getRepository().deleteCustomer(controller.getCurrentCustomer().getId())) {
                        JOptionPane.showMessageDialog(null, "Kund Raderad");
                    }
                    controller.getPanelHandler().changeToSearchPanel();
                }


        }
    }
        class BackToSearchListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getPanelHandler().changeToAdminMenu(controller.getAdminMenuController().getNewMenuButtonListener(),
                        controller.getLoansFromCurrentCustomer(), controller.getAccountsFromCurrentCustomer(),
                        controller.getAdminMenuController().getloanButtonListener(),
                        controller.getAdminMenuController().getAccountButtonListener());
            }
        }

    public boolean textFieldEmpty() {
        if (controller.getPanelHandler().getUpdateCustomerView().getFirstName().getText().equals("") ||
                controller.getPanelHandler().getUpdateCustomerView().getLastName().getText().equals("") ||
                controller.getPanelHandler().getUpdateCustomerView().getPersonalNumber().getText().equals("") ||
                controller.getPanelHandler().getUpdateCustomerView().getPinCode().getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }
    }
