package view.admin;
import model.*;
import dao.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class AbonnementPage extends JFrame{
    private JPanel abonnementPanel;
    private JTextField tfAbonnement;
    private JTextField tfHeure;
    private JTextField tfPrix;
    private JComboBox comboBox2;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JButton ajouterButton;
    private JTextField tfChercher;
    private JButton chercherButton;
    private JTable table1;
    private JButton retourButton;
    private JComboBox comboBox1;
    private JButton refresh;

    public AbonnementPage() {
        //setTitle("Login");
        setContentPane(abonnementPanel);
        setMinimumSize(new Dimension(800,650));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        DBabonnement.tableLoad(table1);
        try {
            DBabonnement.updateCombo(comboBox1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        retourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Home home = new Home();
                dispose();
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterAbonnement();
                DBabonnement.tableLoad(table1);
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBabonnement.modifierAbonnement(table1,tfAbonnement, comboBox1, comboBox2,tfHeure,tfPrix);
                DBabonnement.tableLoad(table1);
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBabonnement.supprimerAbonnement(table1);
                DBabonnement.tableLoad(table1);
            }
        });
        chercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBabonnement.chercherAbonnement(table1,tfChercher);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int ligne = table1.getSelectedRow();
                //JOptionPane.showMessageDialog(null,"you have chose this bock !"+ligne);
                String abonnement = table1.getModel().getValueAt(ligne,1).toString();
                String sport = table1.getModel().getValueAt(ligne,2).toString();
                String type = table1.getModel().getValueAt(ligne,3).toString();
                String heure = table1.getModel().getValueAt(ligne,4).toString();
                String prix = table1.getModel().getValueAt(ligne,5).toString();
                tfAbonnement.setText(abonnement);
                comboBox1.setSelectedItem(sport);
                comboBox2.setSelectedItem(type);
                tfHeure.setText(heure);
                tfPrix.setText(prix);
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

    public void ajouterAbonnement(){
        String abonnement = tfAbonnement.getText().toString();
        String sport = comboBox1.getSelectedItem().toString();
        String type = comboBox2.getSelectedItem().toString();
        String heure = tfHeure.getText();
        String prix = tfPrix.getText();

        if (abonnement.isEmpty() || sport.isEmpty() || type.isEmpty() || heure.isEmpty() || prix.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Merci de remplir tous les champs",
                    "Réssayer",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Abonnement abonnement1 = DBabonnement.addAbonnementToDataBase(abonnement,sport,type,heure,prix);
        if (abonnement1 == null){
            JOptionPane.showMessageDialog(this,
                    "Echec",
                    "Essayer à nouveau",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        AbonnementPage abonnementPage = new AbonnementPage();
    }
}
