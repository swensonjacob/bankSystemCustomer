package se.nackademin.admin.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.admin.view.SwingSetup.*;

public class SearchPanel {

    private JTextField searchField;
    private PanelHandler panelHandler;
    private JLabel errorText;
    private JButton searchButton;
    private JButton newCustomerButton;

    public SearchPanel(PanelHandler panelHandler) {

        this.panelHandler = panelHandler;

        JPanel searchPanel = createPanel();
        searchPanel.setLayout(new BorderLayout());

        searchPanel.add(createLogo(), BorderLayout.NORTH);

        errorText = createLabel("",0);
        searchPanel.add(errorText, BorderLayout.CENTER);
        errorText.setVerticalAlignment(JLabel.CENTER);

        JPanel loginCenterPanel = SwingSetup.createPanel();
        searchPanel.add(loginCenterPanel, BorderLayout.SOUTH);
        loginCenterPanel.setLayout(new GridLayout(2, 2,20,20));
        loginCenterPanel.setBorder(new EmptyBorder(10, 180, 210, 180));

        loginCenterPanel.add(createBigLabel("Sök kund:", 0));

        searchField = SwingSetup.createTextField();
        loginCenterPanel.add(searchField);

        searchButton = createButton("Sök");
        newCustomerButton = createButton("Ny kund");

        loginCenterPanel.add(newCustomerButton);
        loginCenterPanel.add(searchButton);
        searchButton.setBackground(new Color(16, 123, 214));
        searchButton.setForeground(new Color(208, 228, 255));
        searchButton.setPreferredSize(new Dimension(30,30));


        panelHandler.setSearchPanel(searchPanel);
    }


    public String getSearchField() {
        return this.searchField.getText();
    }

    public void setSearchFieldText(String text) {
        this.searchField.setText(text);
    }

    public PanelHandler getPanelHandler() {
        return panelHandler;
    }

    public void setPanelHandler(PanelHandler panelHandler) {
        this.panelHandler = panelHandler;
    }

    public JLabel getErrorText() {
        return errorText;
    }

    public void setErrorText(JLabel errorText) {
        this.errorText = errorText;
    }

    public JButton getSearchButton() {
        return this.searchButton;
    }

    public void setSearchButton(JButton search) {
        this.searchButton = search;
    }

    public void addListener(ActionListener searchlistener, ActionListener newCustomerListener) {
        searchButton.addActionListener(searchlistener);
        newCustomerButton.addActionListener(newCustomerListener);
    }
}