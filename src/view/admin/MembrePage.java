package view.admin;
import dao.*;

import model.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class MembrePage extends JFrame{
    private JTextField tfNom;
    private JTextField tfPrenom;
    private JTextField tfCin;
    private JTextField tfNaissance;
    private JPanel membrePanel;
    private JButton ajouterButton;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JButton chercherButton;
    private JTextField tfChercher;
    private JTable table1;
    private JComboBox comboBox1;
    private JButton retourButton;
    private JComboBox comboBox2;
    private JButton refresh;
    private ConnectDB cnx;
    private DBinteraction db;
    Connection con = null;
    ResultSet resultSet = null;
    PreparedStatement prepared = null;


    public MembrePage() throws SQLException {
        setContentPane(membrePanel);
        setMinimumSize(new Dimension(900,650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

        DBmembres.tableLoad(table1);
        DBmembres.updateCombo(comboBox1);

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
                String abonnement = table1.getModel().getValueAt(ligne,5).toString();
                tfNom.setText(nom);
                tfPrenom.setText(prenom);
                tfCin.setText(cin);
                tfNaissance.setText(naissance);
                comboBox1.setSelectedItem(abonnement);
            }
        });
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterMembre();
                DBmembres.tableLoad(table1);
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBmembres.modifierMembre(table1,tfNom,tfPrenom,tfCin,tfNaissance,comboBox1);
                DBmembres.tableLoad(table1);
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBmembres.supprimerMembre(table1);
                DBmembres.tableLoad(table1);
            }
        });
        chercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBmembres.chercherMember(table1,tfChercher);

            }
        });

        retourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Home home = new Home();
                dispose();
            }
        });

        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DBmembres.tableLoad(table1);
            }
        });
    }

    public void ajouterMembre(){
        String nom = tfNom.getText().toString();
        String prenom = tfPrenom.getText();
        String cin = tfCin.getText();
        String naissance = tfNaissance.getText();
        String abonnement = comboBox1.getSelectedItem().toString();

        if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || naissance.isEmpty() || abonnement.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Merci de remplir tous les champs",
                    "Réssayer",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Member member = DBmembres.addMemberToDataBase(nom,prenom,cin,naissance,abonnement);
        if (member == null){
            JOptionPane.showMessageDialog(this,
                    "Echec",
                    "Essayer à nouveau",
                    JOptionPane.ERROR_MESSAGE);
        }
    }




    public static void main(String[] args) throws SQLException {
        MembrePage membrePage = new MembrePage();

    }
}
