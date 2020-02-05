package se.nackademin.admin.view;

import se.nackademin.customer.model.Account;
import se.nackademin.customer.model.Loan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static se.nackademin.admin.view.SwingSetup.*;
import static se.nackademin.customer.view.SwingSetup.createBigLabel;
import static se.nackademin.customer.view.SwingSetup.createPanel;
import static se.nackademin.customer.view.SwingSetup.createTwoLineButton;

public class AdminMenu {

    private PanelHandler panelHandler;
    private JPanel actionMenu;
    private JLabel userLabel;
    private JPanel loanPanel;
    private java.util.List<Loan> loans;
    private java.util.List<Account> accounts;
    private java.util.List<JButton> loanButtons;
    private List<JButton> accountButtons;
    private JButton viewAccount;
    private JButton createAccount;
    private JButton manageCustomer;
    private JButton backButton;


    private Dimension buttonBounds = new Dimension(50,65);

    public AdminMenu(PanelHandler panelHandler, ActionListener adminMenuListener) {

        this.panelHandler = panelHandler;
        loanButtons = new ArrayList<>();
        accountButtons = new ArrayList<>();
        createAccountButtons();
        createLoanButtons();
        actionMenu = SwingSetup.createPanel();
        actionMenu.setLayout(new BorderLayout());

        JPanel accountPanel = createPanel();
        accountPanel.setLayout(new GridLayout(5,1,0,10));
        accountPanel.setBorder(new EmptyBorder(20,40,20,20));
        accountPanel.add(createBigLabel("Konton för 890206:",2));
        accountButtons.forEach(l -> accountPanel.add(l));

        loanPanel = createPanel();
        loanPanel.setLayout(new GridLayout(5,1,0,10));
        loanPanel.setBorder(new EmptyBorder(20,20,20,40));
        loanPanel.add(createBigLabel("Lån för 890206:",2));
        loanButtons.forEach(l -> loanPanel.add(l));


        JPanel overviewPanel = createPanel();
        overviewPanel.setLayout(new GridLayout(1,2));
        overviewPanel.add(accountPanel);
        overviewPanel.add(loanPanel);

        JPanel actionButtons = createPanel();
        actionButtons.setLayout(new GridLayout(2,2, 40, 10));
        actionButtons.setBorder(new EmptyBorder(0,40,40,40));


        viewAccount = SwingSetup.createButton("Bevilja lån");
        actionButtons.add(viewAccount);

        createAccount = SwingSetup.createButton("Skapa konto");
        actionButtons.add(createAccount);

        manageCustomer = SwingSetup.createButton("Hantera kund");
        actionButtons.add(manageCustomer);

        backButton = SwingSetup.createButton("Tillbaka");
        actionButtons.add(backButton);

        addMainmenuButtonListener(adminMenuListener);

        actionMenu.add(createLogo(),BorderLayout.NORTH);
        actionMenu.add(overviewPanel,BorderLayout.CENTER);
        actionMenu.add(actionButtons, BorderLayout.SOUTH);
        this.panelHandler.setAdminMenuPanel(actionMenu);
    }

    public void addLoanListeners(ActionListener loanListener) {
        loanButtons.forEach(l -> l.addActionListener(loanListener));
    }

    public void addAccountListeners(ActionListener accountListener) {
        accountButtons.forEach(l -> l.addActionListener(accountListener));
    }

    public void createLoanButtons() {

        loanButtons.add(createButton("Lånenummer: 9172772663"));
        loanButtons.add(createButton("Lånenummer: 345345345345"));

//        for(Loan loan:loans) {
//            JButton button = createTwoLineButton(loan.getLoanNumber(),loan.getAmount(),1);
//            button.setName(Integer.toString(loan.getId()));
//            loanButtons.add(button);
//        }
    }
    public void createAccountButtons() {
        accountButtons.add(createButton("Kontonummer: 9172772663"));
        accountButtons.add(createButton("Kontonummer: 345345345345"));

//        for(Account account:accounts) {
//            JButton button = createTwoLineButton(account.getAccountName(),account.getBalance(),0);
//            button.setName(Integer.toString(account.getId()));
//            accountButtons.add(button);
//        }
    }

    public void addMainmenuButtonListener(ActionListener adminMenuListener) {
        viewAccount.addActionListener(adminMenuListener);
        createAccount.addActionListener(adminMenuListener);
        manageCustomer.addActionListener(adminMenuListener);
        backButton.addActionListener(adminMenuListener);
    }

    public void setUserLabel(String text) {
        this.userLabel.setText(text);
    }

    public JLabel getUserLabel() {
        return userLabel;
    }

    public JButton getViewAccount() {
        return viewAccount;
    }

    public void setViewAccount(JButton viewAccount) {
        this.viewAccount = viewAccount;
    }

    public JButton getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(JButton createAccount) {
        this.createAccount = createAccount;
    }

    public JButton getManageCustomer() {
        return manageCustomer;
    }

    public void setManageCustomer(JButton manageCustomer) {
        this.manageCustomer = manageCustomer;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setLogOut(JButton logOut) {
        this.backButton = logOut;
    }
}
