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
            controller.getPanelHandler().changeToCustomerView();
        }
    }

}
