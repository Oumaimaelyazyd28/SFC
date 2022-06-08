package view.admin;
import model.*;
import dao.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SportPage extends JFrame{
    private JButton ajouterButton;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JButton chercherButton;
    private JTextField tfChercher;
    private JTable table1;
    private JTextField tfSport;
    private JPanel sportPanel;
    private JButton retourButton;
    private JComboBox comboBox1;
    private JButton refresh;

    public SportPage() {
        setContentPane(sportPanel);
        setMinimumSize(new Dimension(900,650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        DBsport.tableLoad(table1);


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
                DBsport.tableLoad(table1);
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBsport.modifierSport(table1,tfSport,comboBox1);
                DBsport.tableLoad(table1);
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBsport.supprimerSport(table1);
                DBsport.tableLoad(table1);
            }
        });
        chercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBsport.chercherSport(table1,tfChercher);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int ligne = table1.getSelectedRow();
                //JOptionPane.showMessageDialog(null,"you have chose this bock !"+ligne);
                String sport = table1.getModel().getValueAt(ligne,1).toString();
                String salle = table1.getModel().getValueAt(ligne,2).toString();
                tfSport.setText(sport);
                comboBox1.setSelectedItem(salle);
            }
        });
        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DBsport.tableLoad(table1);
            }
        });
    }

    public void ajouterCoach() {
        String sport = tfSport.getText();
        String salle = comboBox1.getSelectedItem().toString();

        if (sport.isEmpty() || salle.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Merci de remplir tous les champs",
                    "Réssayer",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Sport sport1 = DBsport.addSportToDataBase(sport, salle);
        if (sport1 == null) {
            JOptionPane.showMessageDialog(this,
                    "Echec",
                    "Essayer à nouveau",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        SportPage sportPage = new SportPage();
    }
}
