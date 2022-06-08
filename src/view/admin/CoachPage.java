package view.admin;
import dao.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class CoachPage extends JFrame{
    private JPanel coachPanel;
    private JTextField tfNom;
    private JTextField tfPrenom;
    private JTextField tfCin;
    private JTextField tfNaissance;
    private JComboBox comboBox1;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JButton ajouterButton;
    private JTable table1;
    private JTextField tfChercher;
    private JButton chercherButton;
    private JButton retourButton;
    private JTextField tfTelephone;
    private JButton refresh;

    public CoachPage() {
        setContentPane(coachPanel);
        setMinimumSize(new Dimension(900,650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        DBinteraction.Connect();
        DBcoach.tableLoad(table1);
        try {
            DBcoach.updateCombo(comboBox1);
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
                ajouterCoach();
                DBcoach.tableLoad(table1);
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBcoach.modifierCoach(table1,tfNom,tfPrenom,tfCin,tfNaissance,tfTelephone,comboBox1);
                DBcoach.tableLoad(table1);
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBcoach.supprimerCoach(table1);
                DBcoach.tableLoad(table1);
            }
        });
        chercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBcoach.chercherMember(table1,tfChercher);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int ligne = table1.getSelectedRow();
                //JOptionPane.showMessageDialog(null,"you have chose this bock !"+ligne);
                String nom = table1.getModel().getValueAt(ligne,1).toString();
                String prenom = table1.getModel().getValueAt(ligne,2).toString();
                String cin = table1.getModel().getValueAt(ligne,3).toString();
                String naissance = table1.getModel().getValueAt(ligne,4).toString();
                String telephone = table1.getModel().getValueAt(ligne,5).toString();
                String sport = table1.getModel().getValueAt(ligne,6).toString();
                tfNom.setText(nom);
                tfPrenom.setText(prenom);
                tfCin.setText(cin);
                tfNaissance.setText(naissance);
                tfTelephone.setText(telephone);
                comboBox1.setSelectedItem(sport);
            }
        });
        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DBcoach.tableLoad(table1);
            }
        });
    }

    public void ajouterCoach(){
        String nom = tfNom.getText().toString();
        String prenom = tfPrenom.getText();
        String cin = tfCin.getText();
        String naissance = tfNaissance.getText();
        String telephone = tfTelephone.getText();
        String sport = comboBox1.getSelectedItem().toString();

        if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || naissance.isEmpty() || sport.isEmpty() || telephone.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Merci de remplir tous les champs",
                    "Réssayer",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Coach coach = DBcoach.addCoachToDataBase(nom,prenom,cin,naissance,telephone,sport);

        if (coach == null){
            JOptionPane.showMessageDialog(this,
                    "Echec",
                    "Essayer à nouveau",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        CoachPage coachPage = new CoachPage();
    }
}
