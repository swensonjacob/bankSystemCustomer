package se.nackademin.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCustomerController {

    private Controller controller;

    public CreateCustomerController(Controller controller) {
        this.controller = controller;

        controller.getPanelHandler().getCreateCustomerView().addListener(new CreateCustomerListener());
        controller.getPanelHandler().getCreateCustomerView().addBackButtonListener(new BackToSearchListener());
    }

    class CreateCustomerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            if (!controller.textFieldEmpty()) {
                controller.getPanelHandler().changeToCustomerView();
            } else {
                controller.getPanelHandler().getCreateCustomerView().getErrorText().setText("Samtliga fält måste fyllas i");
            }

        }
    }

        class BackToSearchListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getPanelHandler().changeToSearchPanel();

            }
        }
    }
