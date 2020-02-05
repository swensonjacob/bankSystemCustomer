package se.nackademin.admin.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCustomerController {

    private Controller controller;

    public CreateCustomerController(Controller controller) {
        this.controller = controller;

        controller.getPanelHandler().getCreateCustomerView().addListener(new CreateCustomerListener());
        controller.getPanelHandler().getCreateCustomerView().addListener(new CreateCustomerListener());
    }

    class CreateCustomerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource()==controller.getPanelHandler().getCreateCustomerView().getCreateButton()) {
                if (!controller.textFieldEmpty()) {
                    String firstName = controller.getPanelHandler().getCreateCustomerView().getFirstName().getText();
                    String lastName = controller.getPanelHandler().getCreateCustomerView().getLastName().getText();
                    String personalNR = controller.getPanelHandler().getCreateCustomerView().getPersonalNumber().getText();
                    String pinCode = controller.getPanelHandler().getCreateCustomerView().getPinCode().getText();


                    if (controller.getRepository().addNewCustomer(firstName,lastName,personalNR,pinCode)) {
                        JOptionPane.showMessageDialog(null, firstName + " tillagd som en ny kund.");
                        controller.getPanelHandler().changeToAdminMenu(controller.getAdminMenuController().getNewMenuButtonListener());
                    } else {
                        JOptionPane.showMessageDialog(null, "Något gick fel, Kund kunde ej läggas till.");
                    }
                } else {
                    controller.getPanelHandler().getCreateCustomerView().getErrorText().setText("Samtliga fält måste fyllas i");
                }
            } else if (e.getSource()==controller.getPanelHandler().getCreateCustomerView().getBackButton()) {
                controller.getPanelHandler().changeToSearchPanel();
            }

        }
    }

        class BackToSearchListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("klick");
                controller.getPanelHandler().changeToSearchPanel();


            }
        }
    }
