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
            controller.getPanelHandler().changeToAdminMenu(controller.getAdminMenuController().getNewMenuButtonListener());
        }
    }


    class newCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.getPanelHandler().changeToCreateCustomerPanel();
        }
    }

}
