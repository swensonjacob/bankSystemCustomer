package se.nackademin.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchController {

    private Controller controller;

    public SearchController(Controller controller) {
        this.controller = controller;

        controller.getPanelHandler().getSearchView().addListener(new SearchListener(), new newCustomerListener());
    }

    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String personalNR = controller.getPanelHandler().getSearchPanel().getSearchField();

            if (controller.getRepository().verifyPersonalNumber(personalNR)) {
//                controller.setCurrentCustomer(controller.getRepository().getCurrCustomer(personalNR)); // måste pushas in det nya
                controller.getPanelHandler().changeToAdminMenu(controller.getAdminMenuController().getNewMenuButtonListener());
            } else {
                controller.getPanelHandler().getSearchPanel().getErrorText().setText("Felaktigt personnummer");
            }
        }
    }


    class newCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.getPanelHandler().changeToCreateCustomerPanel();
        }
    }

}
