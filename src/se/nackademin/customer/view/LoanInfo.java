package se.nackademin.customer.view;

import se.nackademin.customer.model.Loan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.customer.view.SwingSetup.*;

public class LoanInfo {

    private JPanel loanPanel;
    private Loan currentLoan;
    private PanelHandler panelHandler;
    private DefaultListModel loanPayments;
    private JButton backButton;

    public LoanInfo(PanelHandler panelHandler, ActionListener listener, Loan loan) {

        this.panelHandler = panelHandler;
        this.currentLoan = loan;
        loanPayments = new DefaultListModel();
        backButton = createBackButton();

        calculateDownPaymentPlan(currentLoan.getAmount(),currentLoan.getLoanTime(),currentLoan.getInterest().getInterestRate());
        JList list = createJList(loanPayments);

        backButton.addActionListener(listener);
        JPanel btnPanel = createPanel();
        btnPanel.setLayout(new GridLayout(2,1,10,10));
        btnPanel.add(backButton);
        btnPanel.setBorder(new EmptyBorder(50, 20, 50, 0));
        btnPanel.setPreferredSize(new Dimension(80,60));

        JPanel loanInfoPanel = createPanel();
        loanInfoPanel.setLayout(new GridLayout(4,1));
        loanInfoPanel.add(createBigLabel("Lån " + currentLoan.getLoanNumber(),2));
        loanInfoPanel.add(createLabel("Kapitalskuld: " + Math.round(currentLoan.getAmount()) + " kr" ,2));
        loanInfoPanel.add(createLabel("Räntesats: " + currentLoan.getInterest().getInterestRate() + " %",2));
        loanInfoPanel.add(createLabel("Amorteringsbelopp: " + Math.round(calculateAmortization(currentLoan.getAmount(),currentLoan.getLoanTime())) + " kr" ,2));
        loanInfoPanel.setBorder(new EmptyBorder(0,160,0,0));

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
        double amortization =calculateAmortization(loanAmount,loanTerm);
        int count = 1;
        while (loanAmount > 0) {
            loanPayments.addElement("Månad " + count + " - Ränta: " + Math.round(loanAmount / interestRate/12) + " Amortering:" + Math.round(amortization) + " Kapitalskuld: " + Math.round(loanAmount));
            loanAmount -= amortization;
            count++;
        }
    }
    public double calculateAmortization(double loanAmount, int loanTerm) {
        return loanAmount / loanTerm;

    }
}
