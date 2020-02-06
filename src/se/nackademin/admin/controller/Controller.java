package se.nackademin.admin.controller;

import se.nackademin.admin.model.Account;
import se.nackademin.admin.model.Customer;
import se.nackademin.admin.model.Loan;
import se.nackademin.admin.repository.Repository;
import se.nackademin.admin.view.PanelHandler;

import java.util.List;

public class Controller {

    private PanelHandler panelHandler;
    private Repository repository;
    private Customer currentCustomer;
    private AccountInfoController accountInfoController;
    private AdminMenuController adminMenuController;
    private CreateCustomerController createCustomerController;
    private LoanInfoController loanInfoController;
    private LoginViewController loginViewController;
    private SearchController searchController;
    private UpdateCustomerController updateCustomerController;
    private CreateLoanController createLoanController;
    private CreateAccountController createAccountController;


    public Controller() {

        panelHandler = new PanelHandler();
        repository = new Repository();
        accountInfoController = new AccountInfoController(this);
        adminMenuController = new AdminMenuController(this);
        createCustomerController = new CreateCustomerController(this);
        loanInfoController = new LoanInfoController(this);
        loginViewController = new LoginViewController(this);
        searchController =new SearchController(this);
        updateCustomerController = new UpdateCustomerController(this);
        createLoanController = new CreateLoanController(this);
        createAccountController = new CreateAccountController(this);


    }
        private boolean personalNumberExists(String personalNr) {
            return repository.verifyPersonalNumber(personalNr);
        }

    public PanelHandler getPanelHandler() {
        return panelHandler;
    }

    public void setPanelHandler(PanelHandler panelHandler) {
        this.panelHandler = panelHandler;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public AccountInfoController getAccountInfoController() {
        return accountInfoController;
    }

    public void setAccountInfoController(AccountInfoController accountInfoController) {
        this.accountInfoController = accountInfoController;
    }

    public AdminMenuController getAdminMenuController() {
        return adminMenuController;
    }

    public void setAdminMenuController(AdminMenuController adminMenuController) {
        this.adminMenuController = adminMenuController;
    }

    public CreateCustomerController getCreateCustomerController() {
        return createCustomerController;
    }

    public void setCreateCustomerController(CreateCustomerController createCustomerController) {
        this.createCustomerController = createCustomerController;
    }

    public LoanInfoController getLoanInfoController() {
        return loanInfoController;
    }

    public void setLoanInfoController(LoanInfoController loanInfoController) {
        this.loanInfoController = loanInfoController;
    }

    public LoginViewController getLoginViewController() {
        return loginViewController;
    }

    public void setLoginViewController(LoginViewController loginViewController) {
        this.loginViewController = loginViewController;
    }

    public SearchController getSearchController() {
        return searchController;
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }

    public UpdateCustomerController getUpdateCustomerController() {
        return updateCustomerController;
    }

    public void setUpdateCustomerController(UpdateCustomerController updateCustomerController) {
        this.updateCustomerController = updateCustomerController;
    }

    public List<Loan> getLoansFromCurrentCustomer() {
       return repository.getLoansFromCustomer(currentCustomer);
    }
    public List<Account> getAccountsFromCurrentCustomer() {
        return repository.getAccountFromCustomer(currentCustomer);
    }
}
