package view.assistant;

import view.salle.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControleAss extends JFrame{
    private JPanel controleAssPanel;
    private JButton salle1Button;
    private JButton salle2Button;
    private JButton salle3Button;
    private JButton salle4Button;
    private JButton salle5Button;
    private JButton salle6Button;
    private JButton salle7Button;
    private JButton salle8Button;
    private JButton button1;

    public ControleAss() {
        setContentPane(controleAssPanel);
        setMinimumSize(new Dimension(900,650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                HomeAss homeAss = new HomeAss();
                dispose();
            }
        });
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
        salle3Button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
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
        salle8Button.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                SalleHuit salleHuit = new SalleHuit();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        ControleAss controleAss = new ControleAss();
    }
}
