package se.nackademin.view;

import se.nackademin.model.Loan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.view.SwingSetup.*;

public class LoanInfo {

    private JPanel loanPanel;
    private Loan currentLoan;
    private PanelHandler panelHandler;
    private DefaultListModel loanPayments;
    private JButton backButton;

    public LoanInfo(PanelHandler panelHandler, ActionListener listener) {

        this.panelHandler = panelHandler;
        loanPayments = new DefaultListModel();
        backButton = createBackButton();

        calculateDownPaymentPlan(10000,24,2.4);
        JList list = createJList(loanPayments);

        backButton.addActionListener(listener);
        JPanel btnPanel = createPanel();
        btnPanel.setLayout(new GridLayout(2,1,10,10));
        btnPanel.add(backButton);
        btnPanel.setBorder(new EmptyBorder(50, 20, 50, 0));
        btnPanel.setPreferredSize(new Dimension(80,60));

        JPanel loanInfoPanel = createPanel();
        loanInfoPanel.setLayout(new GridLayout(4,1));
        loanInfoPanel.add(createBigLabel("Lån 213123123123123",2));
        loanInfoPanel.add(createLabel("Kapitalskuld: 100 000" ,2));
        loanInfoPanel.add(createLabel("Räntesats: 2.3%" ,2));
        loanInfoPanel.add(createLabel("Amorteringsbelopp: 417 kr" ,2));
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
        double amortization = loanAmount/loanTerm;
        int count = 1;
        while (loanAmount>0) {
           loanPayments.addElement("Månad " + count + " - Ränta: " + Math.round(loanAmount/interestRate) + " Amortering:" + Math.round(amortization) + " Kapitalskuld: " + Math.round(loanAmount));
            loanAmount-=amortization;
            count++;
        }



    }
}
