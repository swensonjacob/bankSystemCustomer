package se.nackademin.admin.view;

import javax.swing.*;
import java.awt.*;

import static se.nackademin.admin.view.SwingSetup.*;

public class CustomerView {

    private PanelHandler panelHandler;
    private JPanel actionMenu;
    private JLabel userLabel;

    private Dimension buttonBounds = new Dimension(50,65);

    public CustomerView(PanelHandler panelHandler) {

        this.panelHandler = panelHandler;

        actionMenu = SwingSetup.createPanel();
        actionMenu.setLayout(new BorderLayout());

        actionMenu.add(createLogo(),BorderLayout.NORTH);

        JPanel actionButtons = createPanel();
        actionMenu.add(actionButtons, BorderLayout.SOUTH);
        actionButtons.setLayout(new GridLayout(4,2, 5, 5));

        JButton update = SwingSetup.createButton("Uppdatera kunduppgifter");
        actionButtons.add(update);
        update.setPreferredSize(buttonBounds);

        JButton viewAccount = SwingSetup.createButton("Gå in på konton");
        actionButtons.add(viewAccount);
        viewAccount.setPreferredSize(buttonBounds);

        JButton createAccount = SwingSetup.createButton("Skapa konto");
        actionButtons.add(createAccount);
        createAccount.setPreferredSize(buttonBounds);

        JButton payLoan = SwingSetup.createButton("Bevilja lån");
        actionButtons.add(payLoan);
        payLoan.setPreferredSize(buttonBounds);

        JButton viewLoans = SwingSetup.createButton("Gå in på lån");
        actionButtons.add(viewLoans);
        viewLoans.setPreferredSize(buttonBounds);

        JButton deleate = SwingSetup.createButton("Radera kund");
        actionButtons.add(deleate);
        deleate.setPreferredSize(buttonBounds);

        userLabel = SwingSetup.createLabel("", 2);
        actionButtons.add(userLabel);

        this.panelHandler.setCustomerPanel(actionMenu);
    }

    public void setUserLabel(String text) {
        this.userLabel.setText(text);
    }

    public JLabel getUserLabel() {
        return userLabel;
    }
}