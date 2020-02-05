package se.nackademin.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCustomerController {

    private Controller controller;

    public UpdateCustomerController(Controller controller) {
        this.controller = controller;

        controller.getPanelHandler().getUpdateCustomerView().addListener(new UpdateCustomerListener());
    }

    class UpdateCustomerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!controller.textFieldEmpty()) {
                //repo update customer
            } else {
                controller.getPanelHandler().getCreateCustomerView().getErrorText().setText("Samtliga fält måste fyllas i");
            }

        }
    }
}
