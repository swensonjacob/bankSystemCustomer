package se.nackademin.admin.controller;

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
                if (!textFieldEmpty()) {
                    controller.getPanelHandler().changeToAdminMenu(controller.getAdminMenuController().getNewMenuButtonListener());
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

    public boolean textFieldEmpty() {
        if (controller.getPanelHandler().getCreateCustomerView().getFirstName().getText().equals("") ||
                controller.getPanelHandler().getCreateCustomerView().getLastName().getText().equals("") ||
                controller.getPanelHandler().getCreateCustomerView().getPersonalNumber().getText().equals("") ||
                controller.getPanelHandler().getCreateCustomerView().getPinCode().getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }
    }
