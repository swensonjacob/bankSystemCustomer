package se.nackademin.customer.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.customer.view.SwingSetup.*;

public class LoginView {

    private JTextField username = createTextField();
    private JPasswordField password= createPasswordField();
    private PanelHandler panelHandler;
    private JLabel errorText;
    private JButton loginButton;

    public LoginView(PanelHandler panelHandler) {

        this.panelHandler = panelHandler;

        JPanel loginCenterPanel = SwingSetup.createPanel();
        loginButton = createButton("Logga in");
        loginButton.setBackground(new Color(16, 123, 214));
        loginButton.setForeground(new Color(208, 228, 255));

        loginCenterPanel.setLayout(new GridLayout(3, 2,20,20));
        loginCenterPanel.add(createLabel("Personnummer",2));
        loginCenterPanel.add(createLabel("Pinkod",2));
        loginCenterPanel.add(username);
        loginCenterPanel.add(password);
        loginCenterPanel.add(createLabel("",0));
        loginCenterPanel.add(loginButton);
        loginCenterPanel.setBorder(new EmptyBorder(10, 180, 210, 180));

        JPanel loginPanel = createPanel();
        loginPanel.setLayout(new BorderLayout());
        errorText= createLabel("",0);
        errorText.setVerticalAlignment(JLabel.CENTER);
        loginPanel.setLayout(new BorderLayout());
        loginPanel.add(createLogo(), BorderLayout.NORTH);
        loginPanel.add(errorText, BorderLayout.CENTER);
        loginPanel.add(loginCenterPanel, BorderLayout.SOUTH);

        panelHandler.setLoginPanel(loginPanel);
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
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

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }
}
