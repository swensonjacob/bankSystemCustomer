package se.nackademin.admin.controller;

import se.nackademin.admin.model.Account;
import se.nackademin.admin.model.Loan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoanInfoController {

    private Controller controller;

    public LoanInfoController(Controller controller) {
        this.controller = controller;
    }

    class LoanInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == controller.getPanelHandler().getLoanInfo().getBackButton()) {

                controller.getPanelHandler().changeToAdminMenu(controller.getAdminMenuController().getNewMenuButtonListener(),
                        controller.getLoansFromCurrentCustomer(),
                        controller.getAccountsFromCurrentCustomer(),
                        controller.getAdminMenuController().getloanButtonListener(),
                        controller.getAdminMenuController().getAccountButtonListener());
            } else if (e.getSource() == controller.getPanelHandler().getLoanInfo().getEditRent()) {

                controller.getRepository().changeRateForLoan(getCurrentLoan().getId(),
                        Double.parseDouble(controller.getPanelHandler().getLoanInfo().getRentInput().getText()));
                JOptionPane.showMessageDialog(null,"Räntan är uppdaterad");
                updateLoanPanel();

            } else if (e.getSource() == controller.getPanelHandler().getLoanInfo().getEditTerm()) {
                controller.getRepository().changeLoanTimebyMonth(getCurrentLoan().getId(),Integer.parseInt(controller.getPanelHandler().getLoanInfo().getLoanTermInput().getText()));
                JOptionPane.showMessageDialog(null,"Lånetiden uppdaterad");
                updateLoanPanel();
            }

        }
    }

    public ActionListener getLoanInfoListener() {
        return new LoanInfoListener();
    }

    private Loan getCurrentLoan() {
        return controller.getPanelHandler().getLoanInfo().getCurrentLoan();
    }

    public void updateLoanPanel() {
        List<Loan> loans = controller.getRepository().getLoansFromCustomer(controller.getCurrentCustomer());
        loans.forEach( l -> {
            if (l.getId() == controller.getPanelHandler().getLoanInfo().getCurrentLoan().getId()) {
                controller.getPanelHandler().changeToLoanPanel(getLoanInfoListener(),l);
            }
        });
    }

}
