package se.nackademin.admin.controller;

import se.nackademin.admin.model.Account;
import se.nackademin.admin.model.Loan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminMenuController {

    private Controller controller;

    public AdminMenuController(Controller controller) {
        this.controller = controller;
    }


    class LoanButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Object ae = e.getSource();
            if (ae instanceof JButton) {
                JButton b = (JButton) ae;
                List<Loan> loans = controller.getRepository().getLoansFromCustomer(controller.getCurrentCustomer());
                loans.forEach(l -> {
                    if (Integer.toString(l.getId()).equalsIgnoreCase(b.getName())) {
                        controller.getPanelHandler().changeToLoanPanel(controller.getLoanInfoController().getLoanInfoListener(), l);
                    }
                });

            }
        }
    }

    class AccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Object ae = e.getSource();
            if (ae instanceof JButton) {
                JButton b = (JButton) ae;
                List<Account> accounts = controller.getRepository().getAccountFromCustomer(controller.getCurrentCustomer());
                accounts.forEach(a -> {
                    if (Integer.toString(a.getId()).equalsIgnoreCase(b.getName())) {
                        controller.getPanelHandler().changeToAccountPanel(controller.getAccountInfoController().getAccountInfoListener(), a);
                    }
                });

            }
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

                } else if (e.getSource() == controller.getPanelHandler().getAdminMenuView().getBackButton()) {
                    controller.getPanelHandler().changeToSearchPanel();
                } else if (e.getSource() == controller.getPanelHandler().getAdminMenuView().getCreateAccount()) {
                    controller.getPanelHandler().changeToCreateAccountPanel();
                } else if (e.getSource() == controller.getPanelHandler().getAdminMenuView().getGrantLoan()) {
                    controller.getPanelHandler().changeToCreateLoanPanel();
                }

            }
        }

    public ActionListener getNewMenuButtonListener() {
        return new menuButtonListener();
    }

    public ActionListener getloanButtonListener() {
        return new LoanButtonListener();
    }

    public ActionListener getAccountButtonListener() {
        return new AccountButtonListener();
    }
}
