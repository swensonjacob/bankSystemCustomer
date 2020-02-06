package se.nackademin.admin.controller;


import se.nackademin.admin.DateValidator;
import se.nackademin.admin.model.Account;
import se.nackademin.admin.model.AccountHistory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AccountInfoController {

    private Controller controller;

    public AccountInfoController(Controller controller) {
        this.controller = controller;
    }

    class AccountinfoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == controller.getPanelHandler().getAccountInfo().getBackButton()) {
                changeToAdminMenu();
            } else if (e.getSource() == controller.getPanelHandler().getAccountInfo().getWithdrawButton()) {
                int withdrawsum = Integer.parseInt(controller.getPanelHandler().getAccountInfo().getWithdrawSum().getText());
                withdrawsum =  withdrawsum -  (withdrawsum * 2);
                controller.getRepository().updateBalanceForAccount(getCurrentAccount().getId(), withdrawsum);
                updateAccountPanel();
            } else if (e.getSource() == controller.getPanelHandler().getAccountInfo().getDepositButton()) {
                controller.getRepository().updateBalanceForAccount(getCurrentAccount().getId(),
                        Integer.parseInt(controller.getPanelHandler().getAccountInfo().getWithdrawSum().getText()));
                updateAccountPanel();
            } else if (e.getSource() == controller.getPanelHandler().getAccountInfo().getChangeInterestButton()) {
                controller.getRepository().changeRateForAccount(getCurrentAccount().getId(),
                        Double.parseDouble(controller.getPanelHandler().getAccountInfo().getInterestField().getText()));
                updateAccountPanel();
            } else if (e.getSource() == controller.getPanelHandler().getAccountInfo().getDeleteButton()) {
                controller.getRepository().deleteAccount(controller.getPanelHandler().getAccountInfo().getCurrentAccount().getId());
                changeToAdminMenu();
            } else if(e.getSource() == controller.getPanelHandler().getAccountInfo().getShowHistory()) {
                String startDate = controller.getPanelHandler().getAccountInfo().getStartDate().getText();
                String endDate = controller.getPanelHandler().getAccountInfo().getEndDate().getText();

                if (DateValidator.isValid(startDate) && DateValidator.isValid(endDate)) {
                    if (DateValidator.isStartDateBeforeEndDate(startDate,endDate)) {
                        List<AccountHistory> filterdAccounts = controller.getRepository().getAccountHistory(startDate,endDate);
                        controller.getPanelHandler().getAccountInfo().setFilteredTransactionHistory(filterdAccounts);

                    } else {
                        JOptionPane.showMessageDialog(null, "datumen är i fel ordning.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ej korrekta datum, ange YYYY-MM-DD");
                }
            }

        }

    }

    public ActionListener getAccountInfoListener() {
        return new AccountinfoListener();
    }

    public Account getCurrentAccount() {
       return controller.getPanelHandler().getAccountInfo().getCurrentAccount();
    }

    public void updateAccountPanel() {
        List<Account> accounts = controller.getRepository().getAccountFromCustomer(controller.getCurrentCustomer());
        accounts.forEach(a -> {
            if (a.getId() == controller.getPanelHandler().getAccountInfo().getCurrentAccount().getId()) {
                controller.getPanelHandler().changeToAccountPanel(getAccountInfoListener(),a);
            }
        });
    }
    public void changeToAdminMenu() {
        controller.getPanelHandler().changeToAdminMenu(controller.getAdminMenuController().getNewMenuButtonListener(),
                controller.getLoansFromCurrentCustomer(),
                controller.getAccountsFromCurrentCustomer(),
                controller.getAdminMenuController().getloanButtonListener(),
                controller.getAdminMenuController().getAccountButtonListener());
    }


}
