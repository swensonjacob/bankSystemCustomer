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

    class withdrawFromAccount implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int accountId = controller.getPanelHandler().getAccountInfo().getCurrentAccount().getId();
            String amount = controller.getPanelHandler().getAccountInfo().getWithdrawSum().getText();
            int minusAmount = Integer.parseInt(amount);


            minusAmount *= -1;
            controller.getRepository().updateBalanceForAccount(accountId,minusAmount);
            List<Account> accounts = controller.getRepository().getAccountFromCustomer(controller.getCurrentCustomer());
            accounts.forEach(a -> {
//                if(a.getId() == controller.getPanelHandler().getAccountInfo().getCurrentAccount().getId()) {
//                    controller.getPanelHandler().changeToAccountPanel()                           //Lägg till den panel som ska va här
//                }
            });

        }

    }

    class depositInAccount implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int accountId = controller.getPanelHandler().getAccountInfo().getCurrentAccount().getId();
            String amount = controller.getPanelHandler().getAccountInfo().getWithdrawSum().getText();
            int minusAmount = Integer.parseInt(amount);


            minusAmount *= -1;
            controller.getRepository().updateBalanceForAccount(accountId,minusAmount);
            List<Account> accounts = controller.getRepository().getAccountFromCustomer(controller.getCurrentCustomer());
            accounts.forEach(a -> {
//                if(a.getId() == controller.getPanelHandler().getAccountInfo().getCurrentAccount().getId()) {
//                    controller.getPanelHandler().changeToAccountPanel()                           //Lägg till den panel som ska va här
//                }
            });


        }
    }
    class FilterDate implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            String startDate = controller.getPanelHandler().getAccountInfo().getStartDate().getText();
            String endDate = controller.getPanelHandler().getAccountInfo().getEndDate().getText();

            if (DateValidator.isValid(startDate) && DateValidator.isValid(endDate)) {
                if (DateValidator.isStartDateBeforeEndDate(startDate,endDate)) {

                    List<AccountHistory> filterdAccounts = controller.getRepository().getAccountHistory(startDate,endDate);
                    filterdAccounts.forEach(a -> {
//                if(a.getId() == controller.getPanelHandler().getAccountInfo().getCurrentAccount().getId()) {
//                    controller.getPanelHandler().changeToAccountPanel()                           //Lägg till den panel som ska va här
//                }
                    });




                } else {
                    JOptionPane.showMessageDialog(null, "datumen är i fel ordning.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "ej korrekta datum, ange YYYY-MM-DD");
            }

        }
    }


}
