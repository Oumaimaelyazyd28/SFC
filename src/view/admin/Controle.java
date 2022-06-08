package view.admin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.String;

import view.salle.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
//import javax.*;

public class Controle extends JFrame {
    private JPanel controlePanel;
    private JButton salle1Button;
    private JButton salle2Button;
    private JButton salle3Button;
    private JButton salle4Button;
    private JButton salle5Button;
    private JButton salle6Button;
    private JButton salle7Button;
    private JButton salle8Button;
    private JButton button1;
    private JButton draw;
    private JPanel graphPanel;
    //private JPanel chartPanel;

    public Controle(){
        setContentPane(controlePanel);
        setMinimumSize(new Dimension(850,650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);


        salle1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalleUne salleUne = new SalleUne();
                dispose();
            }
        });
        salle2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalleDeux salleDeux = new SalleDeux();
                dispose();
            }
        });
        salle3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalleTrois salleTrois = new SalleTrois();
                dispose();
            }
        });
        salle4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalleQuatre salleQuatre = new SalleQuatre();
                dispose();
            }
        });
        salle5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalleCinq salleCinq = new SalleCinq();
                dispose();
            }
        });
        salle6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalleSix salleSix = new SalleSix();
                dispose();
            }
        });
        salle7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalleSept salleSept = new SalleSept();
                dispose();
            }
        });
        salle8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalleHuit salleHuit = new SalleHuit();
                dispose();
            }
        });
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               Home home = new Home();
                dispose();
            }
        });
    }



    public static void main(String[] args) throws SQLException {
        Controle controle = new Controle();
    }
}
