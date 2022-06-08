package view.assistant;

import dao.DBpaiement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaiementAssPage extends JFrame{
    private JPanel paiementAssPanel;
    private JButton retourButton;
    private JButton listeButton;
    private JTextField tfChercher;
    private JButton chercherButton;
    private JTable table1;
    private JButton refresh;

    public PaiementAssPage() {
        setContentPane(paiementAssPanel);
        setMinimumSize(new Dimension(900,650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
                DBpaiement.tableLoad(table1);
            }
        });
        chercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBpaiement.chercherPaiement(table1,tfChercher);
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

    public static void main(String[] args) {
        PaiementAssPage paiementAssPage = new PaiementAssPage();
    }
}
