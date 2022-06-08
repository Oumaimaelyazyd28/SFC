package view;

import tools.DBtemperature;
import view.salle.SalleUne;
import view.tableCustom.BButton;
import view.tableCustom.ColorDef;
import view.tableCustom.SteelCheckBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import static java.lang.Thread.sleep;

public class Device extends JFrame{
    private JButton salle1Button;
    private JButton salle3Button;
    private JButton salle2Button;
    private JButton salle4Button;
    private JButton salle5Button;
    private JButton salle6Button;
    private JButton salle7Button;
    private JButton salle8Button;
    private JButton salle1Button1;
    private JButton salle7Button1;
    private JButton salle4Button1;
    private JButton salle3Button1;
    private JButton salle2Button1;
    private JButton salle5Button1;
    private JButton salle6Button1;
    private JButton salle8Button1;
    private JTable table1;
    private JPanel devicePanel;
    private JTable table2;
    private JPanel switchPanel;
    Container c;
    JPanel pan = new JPanel();
    SteelCheckBox b1 = new SteelCheckBox();

    public Device() {
        setContentPane(devicePanel);
        setMinimumSize(new Dimension(900,650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        //Client client = new Client();
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
                DBtemperature.importData(table1,"src/view/files/test.txt","Temperature de "+salle2Button.getText());
            }
        });
        salle3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBtemperature.importData(table1, "src/view/files/test.txt","Temperature de "+salle3Button.getText());

            }
        });
        salle4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBtemperature.importData(table1,"src/view/files/test.txt","Temperature de "+salle4Button.getText());
            }
        });
        salle5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBtemperature.importData(table1,"src/view/files/test.txt","Temperature de "+salle5Button.getText());
            }
        });
        salle6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBtemperature.importData(table1,"src/view/files/test.txt","Temperature de "+salle6Button.getText());
            }
        });
        salle7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBtemperature.importData(table1,"src/view/files/test.txt","Temperature de "+salle7Button.getText());
            }
        });
        salle8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBtemperature.importData(table1,"src/view/files/test.txt","Temperature de "+salle8Button.getText());
            }
        });


        switchPanel.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);

            }
        });

        BButton b = new BButton();
        b.setVisible(true);

    }

    public void BButtonn(JPanel jp){
        c = getContentPane();
        setSize(300,300);

        b1.setSelectedColor(ColorDef.YELLOW);
        b1.setRised(false);
        b1.setText("yellow");

        jp.add(b1);

        c.add(jp);
        b1.setVisible(true);
    }

    public static void main(String[] args) {
        Device device = new Device();
    }
}
