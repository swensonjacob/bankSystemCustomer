package se.nackademin.admin.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelHandler extends JFrame{

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel customerPanel;
    private JPanel loanPanel;
    private JPanel accountPanel;
    private JPanel loginPanel;
    private JPanel searchPanel;

    private SearchPanel searchView;
    private CustomerView customerView;
    private JPanel createCustomerPanel;
    private JPanel updateCustomerPanel;
    private MainMenuView mainMenuView;
    private LoanInfo loanInfo;
    private AccountInfo accountInfo;
    private LoginView loginView;
    private CreateCustomer createCustomerView;
    private UpdateCustomer updateCustomerView;

    public PanelHandler() {

        loginView = new LoginView(this);
        createCustomerView = new CreateCustomer(this);
        updateCustomerView = new UpdateCustomer(this);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(updateCustomerPanel,"updateCustomer");
        mainPanel.add(createCustomerPanel,"createCustomer");
        mainPanel.add(loginPanel,"login");



        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setVisible(true);
        setSize(new Dimension(800, 600));
    }

    public JPanel getCustomerPanel() {
        return this.customerPanel;
    }

    public void setCustomerPanel(JPanel customerPanel) {
        this.customerPanel = customerPanel;
    }

    public JPanel getLoanPanel() {
        return loanPanel;
    }

    public void setLoanPanel(JPanel loanPanel) {
        this.loanPanel = loanPanel;
    }
    private void changePanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public void changeToLoanPanel(ActionListener listener) {
        loanInfo = new LoanInfo(this, listener);
        mainPanel.add(loanPanel, "loan");
        changePanel("loan");
    }

    public void changeToAccountPanel(ActionListener listener) {
        this.accountInfo = new AccountInfo(this, listener);
        mainPanel.add(accountPanel, "account");
        changePanel("account");
    }

   public void changeToCustomerView() {
       this.customerView = new CustomerView(this);
       mainPanel.add(customerPanel, "customerView");
       changePanel("customerView");
    }

    public void changeToSearchPanel(ActionListener listener) {
        searchView = new SearchPanel(this, listener);
        mainPanel.add(searchPanel, "search");
        changePanel("search");
    }

    public void changeToChangeCustomerPanel() {
        changePanel("createCustomer");
    }
    public void changeToUpdateCustomerPanel() {
        changePanel("updateCustomer");
    }

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    public CustomerView getCustomerView() {
        return this.customerView;
    }

    public void setCustomerView(CustomerView customerView) {
        this.customerView = customerView;
    }

    public LoanInfo getLoanInfo() {
        return loanInfo;
    }

    public void setLoanInfo(LoanInfo loanInfo) {
        this.loanInfo = loanInfo;
    }

    public JPanel getAccountPanel() {
        return accountPanel;
    }

    public void setAccountPanel(JPanel accountPanel) {
        this.accountPanel = accountPanel;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(JPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void setSearchPanel(JPanel searchPanel) {
        this.searchPanel = searchPanel;
    }

    public JPanel getSearchPanel() {
        return this.searchPanel;
    }

    public JPanel getCreateCustomerPanel() {
        return createCustomerPanel;
    }

    public void setCreateCustomerPanel(JPanel createCustomerPanel) {
        this.createCustomerPanel = createCustomerPanel;
    }

    public CreateCustomer getCreateCustomerView() {
        return createCustomerView;
    }

    public void setCreateCustomerView(CreateCustomer createCustomerView) {
        this.createCustomerView = createCustomerView;
    }

    public JPanel getUpdateCustomerPanel() {
        return updateCustomerPanel;
    }

    public void setUpdateCustomerPanel(JPanel updateCustomerPanel) {
        this.updateCustomerPanel = updateCustomerPanel;
    }

    public UpdateCustomer getUpdateCustomerView() {
        return updateCustomerView;
    }

    public void setUpdateCustomerView(UpdateCustomer updateCustomerView) {
        this.updateCustomerView = updateCustomerView;
    }
}

    public void setSearchView(SearchPanel searchview) {
        this.searchView = searchview;
    }

    public SearchPanel getSearchView() {
        return this.searchView;
    }
}