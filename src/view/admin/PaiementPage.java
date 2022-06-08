package view.admin;

import dao.DBpaiement;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaiementPage extends JFrame{
    private JTextField tfMembre;
    private JTextField tfPrix;
    private JTextField tfVerse;
    private JTextField tfReste;
    private JComboBox comboBox1;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JButton ajouterButton;
    private JTable table1;
    private JPanel paiementPanel;
    private JTextField tfChercher;
    private JButton chercherButton;
    private JButton retourButton;
    private JTextField tfCin;
    private JButton refresh;

    public PaiementPage() {
        setContentPane(paiementPanel);
        setMinimumSize(new Dimension(800,650));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        DBpaiement.tableLoad(table1);

        retourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Home home = new Home();
                dispose();
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int ligne = table1.getSelectedRow();
                //JOptionPane.showMessageDialog(null,"you have chose this bock !"+ligne);
                String cin = table1.getModel().getValueAt(ligne,1).toString();
                String membre = table1.getModel().getValueAt(ligne,2).toString();
                String mois = table1.getModel().getValueAt(ligne,3).toString();
                String frais = table1.getModel().getValueAt(ligne,4).toString();
                String verse = table1.getModel().getValueAt(ligne,5).toString();
                String reste = table1.getModel().getValueAt(ligne,6).toString();
                tfCin.setText(cin);
                tfMembre.setText(membre);
                comboBox1.setSelectedItem(mois);
                tfPrix.setText(frais);
                tfVerse.setText(verse);
                tfReste.setText(reste);
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterPaiement();
                DBpaiement.tableLoad(table1);
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBpaiement.modifierPaiement(table1,tfCin,tfMembre,comboBox1,tfPrix,tfVerse,tfReste);
                DBpaiement.tableLoad(table1);
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBpaiement.supprimerPaiement(table1);
                DBpaiement.tableLoad(table1);
            }
        });
        chercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBpaiement.chercherPaiement(table1,tfChercher);
            }
        });
        tfCin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBpaiement.remplirMembre(tfCin,tfMembre);
                DBpaiement.remplirFrais(tfCin,tfPrix);
            }
        });
        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DBpaiement.tableLoad(table1);
            }
        });
    }

    public void ajouterPaiement(){
        String cin = tfCin.getText().toString();
        String membre = tfMembre.getText();
        String mois = comboBox1.getSelectedItem().toString();
        String frais = tfPrix.getText();
        String verse = tfVerse.getText();
        String reste = tfReste.getText();

        if (cin.isEmpty() || membre.isEmpty() || mois.isEmpty() || frais.isEmpty() || verse.isEmpty() || reste.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Merci de remplir tous les champs",
                    "Réssayer",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Paiement paiement = DBpaiement.addPaiementToDataBase(cin,membre,mois,frais,verse,reste);
        if (paiement == null){
            JOptionPane.showMessageDialog(this,
                    "Echec",
                    "Essayer à nouveau",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        PaiementPage paiementPage = new PaiementPage();
    }
}
