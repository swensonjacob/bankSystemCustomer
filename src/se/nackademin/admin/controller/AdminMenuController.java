package se.nackademin.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenuController {

    private Controller controller;

    public AdminMenuController(Controller controller) {
        this.controller = controller;

    }

    class LoanButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.getPanelHandler().changeToLoanPanel(new LoanInfoListener());
        }
    }

    class AccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.getPanelHandler().changeToAccountPanel(new LoanInfoListener());
        }
    }


    class LoanInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //controller.getPanelHandler().changeToAdminMenu();
        }
    }

    class menuButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == controller.getPanelHandler().getAdminMenuView().getManageCustomer()) {
                controller.getPanelHandler().changeToUpdateCustomerPanel();

            } else if(e.getSource() == controller.getPanelHandler().getAdminMenuView().getBackButton()) {
                controller.getPanelHandler().changeToSearchPanel();
            } else if (e.getSource() == controller.getPanelHandler().getAdminMenuView().getCreateAccount()) {
                controller.getPanelHandler().changeToCreateAccountPanel();
            }

        }
    }

    public ActionListener getNewMenuButtonListener() {
        return new menuButtonListener();
    }

}
