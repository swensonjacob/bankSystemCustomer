package se.nackademin.admin.view;

import se.nackademin.admin.model.Account;
import se.nackademin.admin.model.Loan;

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
    private List<Loan> loans;
    private List<Account> accounts;
    private java.util.List<JButton> loanButtons;
    private List<JButton> accountButtons;
    private JButton grantLoan;
    private JButton createAccount;
    private JButton manageCustomer;
    private JButton backButton;
    private ActionListener loanListener;
    private ActionListener accountListener;


    private Dimension buttonBounds = new Dimension(50,65);

    public AdminMenu(PanelHandler panelHandler, ActionListener adminMenuListener,List<Loan> loans,List<Account> accounts,
                     ActionListener loanListener, ActionListener accountListener) {

        this.panelHandler = panelHandler;
        loanButtons = new ArrayList<>();
        accountButtons = new ArrayList<>();
        actionMenu = SwingSetup.createPanel();
        actionMenu.setLayout(new BorderLayout());
        this.loans = loans;
        this.accounts = accounts;
        this.loanListener = loanListener;
        this.accountListener = accountListener;
        createAccountButtons();
        createLoanButtons();

        JPanel accountPanel = createPanel();
        accountPanel.setLayout(new GridLayout(7,1,0,10));
        accountPanel.setBorder(new EmptyBorder(20,40,0,20));
        accountPanel.add(createBigLabel("Konton för 890206:",2));
        accountButtons.forEach(l -> accountPanel.add(l));

        loanPanel = createPanel();
        loanPanel.setLayout(new GridLayout(7,1,0,10));
        loanPanel.setBorder(new EmptyBorder(20,20,0,40));
        loanPanel.add(createBigLabel("Lån för 890206:",2));
        loanButtons.forEach(l -> loanPanel.add(l));


        JPanel overviewPanel = createPanel();
        overviewPanel.setLayout(new GridLayout(1,2));
        overviewPanel.add(accountPanel);
        overviewPanel.add(loanPanel);

        JPanel actionButtons = createPanel();
        actionButtons.setLayout(new GridLayout(2,2, 40, 10));
        actionButtons.setBorder(new EmptyBorder(0,40,40,40));


        grantLoan = SwingSetup.createButton("Bevilja lån");
        actionButtons.add(grantLoan);

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
        for(Loan loan:loans) {
            JButton button = createTwoLineButton(loan.getLoanNumber(),loan.getAmount(),1);
            button.setName(Integer.toString(loan.getId()));
            button.addActionListener(loanListener);
            loanButtons.add(button);
        }
    }
    public void createAccountButtons() {
        for(Account account:accounts) {
            JButton button = createTwoLineButton(account.getAccountName(),account.getBalance(),0);
            button.setName(Integer.toString(account.getId()));
            button.addActionListener(accountListener);
            accountButtons.add(button);
        }
    }

    public void addMainmenuButtonListener(ActionListener adminMenuListener) {
        grantLoan.addActionListener(adminMenuListener);
        createAccount.addActionListener(adminMenuListener);
        manageCustomer.addActionListener(adminMenuListener);
        backButton.addActionListener(adminMenuListener);
        grantLoan.addActionListener(adminMenuListener);
    }

    public void setUserLabel(String text) {
        this.userLabel.setText(text);
    }

    public JLabel getUserLabel() {
        return userLabel;
    }

    public JButton getGrantLoan() {
        return grantLoan;
    }

    public void setGrantLoan(JButton grantLoan) {
        this.grantLoan = grantLoan;
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
