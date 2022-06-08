package view.admin;

import dao.DBcoach;
import dao.DBmembres;
import dao.DBsport;
import model.*;
import view.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.lang.*;
import static java.lang.Thread.sleep;

public class Dashboard extends JFrame{
    private JFrame frame;
    private JPanel dashboardPanel;
    private JButton membresButton;
    private JButton seDeconnecteButton;
    private JButton devicesButton;
    private JButton coachButton;
    private JButton sportsButton;
    private JButton abonnementButton;
    private JButton paiementButton;
    private JPanel panel1;
    private JLabel cntSport;
    private JLabel cntCoach;
    private JLabel cntMembre;
    private JButton alerteButton;

    public Dashboard() {
        setContentPane(dashboardPanel);
        setMinimumSize(new Dimension(900,650));
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        //setVisible(true);

        //Auth auth = new Auth();

        Login login = new Login(this);
        Admin admin = login.admin;
        if (admin != null){
            //lbAdmin.setText("Admin : "+admin.getLogin());
            setLocationRelativeTo(null);
            setVisible(true);
        }
        else {
            dispose();
            System.out.println("false");
        }

        seDeconnecteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Auth auth = new Auth();
                dispose();
            }
        });

        abonnementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbonnementPage abonnementPage = new AbonnementPage();
                setLocationRelativeTo(null);
                dispose();
            }
        });

        membresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MembrePage membrePage = new MembrePage();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        coachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CoachPage coachPage = new CoachPage();
                dispose();
            }
        });
        sportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SportPage sportPage = new SportPage();
                dispose();
            }
        });
        paiementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaiementPage paiementPage = new PaiementPage();
                dispose();
            }
        });
        devicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controle controle = new Controle();
                dispose();
            }
        });

        try {
            DBmembres.countMembre(cntMembre);
            DBcoach.countCoach(cntCoach);
            DBsport.countSport(cntSport);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        alerteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null,"Alarme Déclanchée !");
            }
        });
    }



    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
        /*JFrame frame = new JFrame("Dashboard");
        frame.setContentPane(new Dashboard().dashboardPanel);
        frame.setMinimumSize(new Dimension(700,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);*/

    }



}
