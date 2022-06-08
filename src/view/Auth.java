package view;

import view.admin.Dashboard;
import view.assistant.DashboardAss;
import view.assistant.LoginAss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Auth extends JFrame{
    private JPanel authPanel;
    private JLabel labelPassword;
    private JButton assistantButton;
    private JButton administrateurButton;

    public Auth() {
        setTitle("Login");
        setContentPane(authPanel);
        setMinimumSize(new Dimension(900,650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);


        administrateurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard dashboard = new Dashboard();
                dispose();
            }
        });
        assistantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardAss dashboardAss = new DashboardAss();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Auth auth = new Auth();
    }
}
