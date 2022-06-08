package view.assistant;

import dao.DBcoach;
import dao.DBmembres;
import dao.DBsport;
import view.Auth;
import view.admin.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.lang.*;
import static java.lang.Thread.sleep;

public class HomeAss extends JFrame{
    private JFrame frame;
    private JPanel homeAssPanel;
    private JButton membresButton;
    private JButton seDeconnecteButton;
    private JButton controleButton;
    private JButton coachButton;
    private JButton sportsButton;
    private JButton abonnementButton;
    private JButton paiementButton;
    private JPanel panel1;
    private JLabel cntSport;
    private JLabel cntCoach;
    private JLabel cntMembre;
    private JButton alerteButton;

    public HomeAss() {
        setContentPane(homeAssPanel);
        setMinimumSize(new Dimension(900,650));
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        seDeconnecteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Auth auth = new Auth();
                dispose();
                //System.exit(0);
            }
        });

        abonnementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbonnementAssPage abonnementAssPage = new AbonnementAssPage();
                setLocationRelativeTo(null);
                dispose();
            }
        });

        membresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MembreAssPage membreAssPage = new MembreAssPage();
                dispose();
            }
        });
        coachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CoachAssPage coachAssPage = new CoachAssPage();
                dispose();
            }
        });
        sportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SportAssPage sportAssPage = new SportAssPage();
                dispose();
            }
        });
        paiementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaiementAssPage paiementAssPage = new PaiementAssPage();
                dispose();
            }
        });
        controleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControleAss controleAss = new ControleAss();
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
        HomeAss homeAss = new HomeAss();
        /*JFrame frame = new JFrame("Dashboard");
        frame.setContentPane(new Dashboard().dashboardPanel);
        frame.setMinimumSize(new Dimension(700,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);*/

    }



}
