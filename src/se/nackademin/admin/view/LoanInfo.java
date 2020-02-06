package se.nackademin.admin.view;

import se.nackademin.admin.model.Loan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.admin.view.SwingSetup.*;

public class LoanInfo {

    private JPanel loanPanel;
    private Loan currentLoan;
    private PanelHandler panelHandler;
    private DefaultListModel loanPayments;
    private JButton backButton;
    private JTextField rentInput;
    private JButton editRent;
    private JTextField loanTermInput;
    private JButton editTerm;

    public LoanInfo(PanelHandler panelHandler, ActionListener listener, Loan loan) {

        this.panelHandler = panelHandler;
        currentLoan = loan;
        loanPayments = new DefaultListModel();


        calculateDownPaymentPlan(currentLoan.getAmount(),currentLoan.getLoanTime(),currentLoan.getInterest().getInterestRate());
        JList list = createJList(loanPayments);

        backButton = createBackButton();
        backButton.addActionListener(listener);
        JPanel btnPanel = createPanel();
        btnPanel.setLayout(new GridLayout(2,1,10,10));
        btnPanel.add(backButton);
        btnPanel.setBorder(new EmptyBorder(50, 20, 50, 0));
        btnPanel.setPreferredSize(new Dimension(80,60));

        JPanel loanInfoPanel = createPanel();
        loanInfoPanel.setLayout(new GridLayout(2, 1));


        JPanel infoContainer = SwingSetup.createPanel();
        loanInfoPanel.add(infoContainer);
        infoContainer.setLayout(new GridLayout(1,2, 10, 10));

        infoContainer.add(createBigLabel("Lån: " + currentLoan.getLoanNumber(), 2));
        infoContainer.add(createLabel("Kapitalskuld: " + currentLoan.getAmount(), 2 ));

        JPanel inputPanel = SwingSetup.createPanel();
        loanInfoPanel.add(inputPanel);
        inputPanel.setLayout(new GridLayout(2, 3, 10, 10));

        rentInput = SwingSetup.createTextField();
        editRent = SwingSetup.createButton("Verkställ");
        editRent.addActionListener(listener);

        inputPanel.add(SwingSetup.createLabel("Ändra ränta:",0));
        rentInput.setText(Double.toString(currentLoan.getInterest().getInterestRate()));
        inputPanel.add(rentInput);
        inputPanel.add(editRent);

        loanTermInput = SwingSetup.createTextField();
        editTerm = SwingSetup.createButton("Verkställ");
        editTerm.addActionListener(listener);
        loanTermInput.setText(Integer.toString(currentLoan.getLoanTime()));

        inputPanel.add(SwingSetup.createLabel("Ändra lånetid:",0));
        inputPanel.add(loanTermInput);
        inputPanel.add(editTerm);


        loanInfoPanel.setBorder(new EmptyBorder(50,60,50,60));

        JScrollPane paymentList = new JScrollPane(list);

        paymentList.setBackground(new Color(208, 228, 255));
        paymentList.setBorder(new EmptyBorder(10,10,10,10));

        loanPanel = createPanel();
        loanPanel.setLayout(new BorderLayout());
        loanPanel.add(createLogo(),BorderLayout.NORTH);
        loanPanel.add(loanInfoPanel,BorderLayout.CENTER);
        loanPanel.add(btnPanel,BorderLayout.WEST);
        loanPanel.add(paymentList,BorderLayout.SOUTH);

        panelHandler.setLoanPanel(loanPanel);
    }

    public void calculateDownPaymentPlan(double loanAmount, int loanTerm, double interestRate) {
        loanPayments.addElement("Avbetalning:");
        double amortization = loanAmount/loanTerm;
        int count = 1;
        while (loanAmount>0) {
           loanPayments.addElement("Månad " + count + " - Ränta: " + Math.round(loanAmount/interestRate) + " Amortering:" + Math.round(amortization) + " Kapitalskuld: " + Math.round(loanAmount));
            loanAmount-=amortization;
            count++;
        }

    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JTextField getRentInput() {
        return rentInput;
    }

    public void setRentInput(JTextField rentInput) {
        this.rentInput = rentInput;
    }

    public JButton getEditRent() {
        return editRent;
    }

    public void setEditRent(JButton editRent) {
        this.editRent = editRent;
    }

    public JTextField getLoanTermInput() {
        return loanTermInput;
    }

    public void setLoanTermInput(JTextField loanTermInput) {
        this.loanTermInput = loanTermInput;
    }

    public JButton getEditTerm() {
        return editTerm;
    }

    public void setEditTerm(JButton editTerm) {
        this.editTerm = editTerm;
    }

    public Loan getCurrentLoan() {
        return currentLoan;
    }

    public void setCurrentLoan(Loan currentLoan) {
        this.currentLoan = currentLoan;
    }

    public PanelHandler getPanelHandler() {
        return panelHandler;
    }

    public void setPanelHandler(PanelHandler panelHandler) {
        this.panelHandler = panelHandler;
    }
}
