package view.admin;

import dao.DBcoach;
import dao.DBmembres;
import dao.DBsport;
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

public class Home extends JFrame{
    private JFrame frame;
    private JPanel homePanel;
    private JButton membresButton;
    private JButton seDeconnecteButton;
    private JButton alèrteButton;
    private JButton devicesButton;
    private JButton coachButton;
    private JButton sportsButton;
    private JButton abonnementButton;
    private JButton stockButton;
    private JLabel ctnSport;
    private JLabel ctnCoach;
    private JLabel ctnMembre;

    public Home() {
        setContentPane(homePanel);
        setMinimumSize(new Dimension(900,650));
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        //setVisible(true);
            setLocationRelativeTo(null);
            setVisible(true);
        ctnMembre.setText(String.valueOf(DBmembres.countMembre2()));
        ctnSport.setText(String.valueOf(DBsport.countSport2()));
        ctnCoach.setText(String.valueOf(DBcoach.countCoach2()));


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
        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaiementPage paiementPage = new PaiementPage();
                dispose();
            }
        });
        devicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Device device = new Device();
                Controle controle = new Controle();
                dispose();
            }
        });


        alèrteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null,"Alarme Déclanchée !");
            }
        });
    }



    public static void main(String[] args) {
            Home home = new Home();
        /*JFrame frame = new JFrame("Dashboard");
        frame.setContentPane(new Dashboard().dashboardPanel);
        frame.setMinimumSize(new Dimension(700,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);*/

    }
}
