package se.nackademin.customer.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SwingSetup {

    private static String bankPrinter = "src/se/nackademin/resources/F25BankPrinter.ttf";
    private static String Raleway = "src/se/nackademin/resources/Raleway.ttf";

    public static JButton createTwoLineButton(String numberId, double balance, int type) {

        String label1;
        String label2;

        if (type == 0) {
            label1 = "Kontonummer:";
            label2 = "Saldo:";
        } else {
            label1 = "Lånenummer:";
            label2 = "Lånebelopp:";
        }

        String btnText ="<html>" + label1+ " - " + numberId + "<br>" + label2 + " - " + Math.round(balance) + " kr" + "</html>";
        JButton button = new JButton(btnText);
        button.setForeground(new Color(16, 123, 214));
        button.setBackground(new Color(208, 228, 255));
        button.setOpaque(true);
        Border line = new LineBorder(new Color(16, 123, 214));
        Border margin = new EmptyBorder(5, 10, 5, 10);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        button.setPreferredSize(new Dimension(30, 20));
        button.setFont(applyFont(bankPrinter));
        return button;
    }

    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(new Color(208, 228, 255));
        button.setBackground(new Color(16, 123, 214));
        button.setOpaque(true);
        Border line = new LineBorder(new Color(16, 123, 214));
        Border margin = new EmptyBorder(5, 10, 5, 10);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        button.setPreferredSize(new Dimension(30, 20));
        button.setFont(applyFont(Raleway));
        return button;
    }

    public static JTextField createTextField()  {
        JTextField textField = new JTextField(20);
        textField.setBackground(new Color(208, 228, 255));
        textField.setForeground(new Color(16, 123, 214));
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(16, 123, 214)));
        return textField;
    }

    public static JPasswordField createPasswordField()  {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(208, 228, 255));
        passwordField.setForeground(new Color(16, 123, 214));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(16, 123, 214)));
        return passwordField;
    }


    public static JList createJList(DefaultListModel list) {
        JList jlist = new JList(list);
        jlist.setBackground(new Color(16, 123, 214));
        jlist.setForeground(new Color(208, 228, 255));
        Border line = new LineBorder(new Color(16, 123, 214));
        Border margin = new EmptyBorder(5, 10, 5, 10);
        Border compound = new CompoundBorder(line, margin);
        jlist.setBorder(compound);
        jlist.setFont(applyFont(bankPrinter));
        return jlist;
    }

    public static JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(208, 228, 255));
        return panel;
    }


    public static JLabel createLabel(String text, int alignment) {
        JLabel jLabel = new JLabel(text, alignment);
        jLabel.setForeground(new Color(16, 123, 214));
        jLabel.setFont(applyFont(Raleway));
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return jLabel;
    }

    public static JLabel createBigLabel(String text, int alignment) {
        JLabel jLabel = new JLabel(text, alignment);
        jLabel.setForeground(new Color(16, 123, 214));
        Font font = applyFont(Raleway);
        Font sizedFont = font.deriveFont(18f);
        jLabel.setFont(sizedFont);
        return jLabel;
    }

    private static Font applyFont(String path) {
        File font_file = new File(path);
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        Font sizedFont = font.deriveFont(14f);
        return sizedFont;
    }

    public static JLabel createLogo() {
        String logoPath = "src/se/nackademin/resources/logo.png";
        JLabel logoShoe = new JLabel(new ImageIcon(logoPath));
        logoShoe.setBorder(new EmptyBorder(10, 10, 10, 10));
        return logoShoe;
    }

    public static Border getEmptyBorder() {
        return new EmptyBorder(10, 10, 10, 10);
    }

    public static JButton createHomeButton() {
        return createIconButton("homeIcon.png");
    }

    public static JButton createBackButton() {
        return createIconButton("backIcon.png");
    }

    public static JButton createLogOutButton() {
        return createIconButton("logout.png");
    }

    public static JButton createModelButton(String filename) {
        return createIconButton(filename);
    }
    private static JButton createIconButton(String fileName) {
        Icon icon = new ImageIcon("src/se/nackademin/resources/" + fileName);
        JButton button = new JButton(icon);
        //button.setForeground(new Color(200, 57, 65));
        //button.setBackground(Color.white);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
//        Border line = new LineBorder(new Color(200, 57, 65));
//        Border margin = new EmptyBorder(5, 15, 5, 15);
//        Border compound = new CompoundBorder(line, margin);
//        button.setBorder(compound);
        button.setPreferredSize(new Dimension(70, 30));
        //button.setFont(applyFont(Raleway));
        return button;
    }
}
