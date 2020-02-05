package se.nackademin.customer.view;

import se.nackademin.customer.model.Loan;
import se.nackademin.customer.model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static se.nackademin.customer.view.SwingSetup.*;

public class MainMenuView {


    private PanelHandler panelHandler;
    private JPanel mainMenu;
    private JLabel userLabel;
    private JPanel loanPanel;
    private List<Loan> loans;
    private List<Account> accounts;
    private List<JButton> loanButtons;
    private List<JButton> accountButtons;

    public MainMenuView(PanelHandler panelHandler, ActionListener accountListener,ActionListener loanListener, List<Loan> loans,List<Account> accounts) {
        this.panelHandler = panelHandler;
        loanButtons = new ArrayList<>();
        accountButtons = new ArrayList<>();
        userLabel = createLabel("",2);
        this.loans = loans;
        this.accounts = accounts;
        createAccountButtons();
        createLoanButtons();
        addAccountListeners(accountListener);
        addLoanListeners(loanListener);

        JPanel menuBar = createPanel();
        menuBar.setLayout(new GridLayout(1,4));
        menuBar.add(userLabel);

        JPanel accountPanel = createPanel();
        accountPanel.setLayout(new GridLayout(5,1,0,10));
        accountPanel.setBorder(new EmptyBorder(20,40,20,20));
        accountPanel.add(createBigLabel("Konton:",2));
        accountButtons.forEach(l -> accountPanel.add(l));

        loanPanel = createPanel();
        loanPanel.setLayout(new GridLayout(5,1,0,10));
        loanPanel.setBorder(new EmptyBorder(20,20,20,40));
        loanPanel.add(createBigLabel("Lån:",2));
        loanButtons.forEach(l -> loanPanel.add(l));


        JPanel overviewPanel = createPanel();
        overviewPanel.setLayout(new GridLayout(1,2));
        overviewPanel.add(accountPanel);
        overviewPanel.add(loanPanel);

        mainMenu = createPanel();
        mainMenu.setLayout(new BorderLayout());
        mainMenu.add(createLogo(),BorderLayout.NORTH);
        mainMenu.add(menuBar ,BorderLayout.SOUTH);
        mainMenu.add(overviewPanel,BorderLayout.CENTER);

        panelHandler.setMainMenu(mainMenu);

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
            loanButtons.add(button);
        }
    }
    public void createAccountButtons() {
        for(Account account:accounts) {
            JButton button = createTwoLineButton(account.getAccountName(),account.getBalance(),0);
            button.setName(Integer.toString(account.getId()));
            accountButtons.add(button);
        }
    }

    public JLabel getUserLabel() {
        return userLabel;
    }

}
