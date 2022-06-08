package view.assistant;

import dao.DBabonnement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbonnementAssPage extends JFrame{
    private JPanel abonnementPanel;
    private JTextField tfAbonnement;
    private JTextField tfHeure;
    private JTextField tfPrix;
    private JComboBox comboBox2;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JButton listeButton;
    private JTextField tfChercher;
    private JButton chercherButton;
    private JTable table1;
    private JButton retourButton;
    private JButton refresh;
    private JComboBox comboBox1;

    public AbonnementAssPage() {
        //setTitle("Login");
        setContentPane(abonnementPanel);
        setMinimumSize(new Dimension(800,650));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);


        retourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                HomeAss homeAss = new HomeAss();
                dispose();
            }
        });
        listeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBabonnement.tableLoad(table1);
            }
        });
        chercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBabonnement.chercherAbonnement(table1,tfChercher);
            }
        });
        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DBabonnement.tableLoad(table1);
            }
        });
    }


    public static void main(String[] args) {
        AbonnementAssPage abonnementAssPage = new AbonnementAssPage();
    }
}
