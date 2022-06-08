package view.salle;

import tools.LineChart;
import tools.LineChart_AWT;
import tools.DBhumidite;
import tools.DBtemperature;
import view.admin.Controle;
import view.tableCustom.ColorTable;
import view.tableCustom.ColorTableH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class SalleSix extends JFrame{
    private JTable table1;
    private JButton activerButton;
    private JButton desactiverButton;
    private JPanel salle6Panel;
    private JTable table2;
    private JButton button5;
    private JButton graphHum;
    private JButton graphTemp;
    private JButton activerButton1;
    private JButton desactiverButton1;
    private JFrame frame;

    public SalleSix() {
        setContentPane(salle6Panel);
        setMinimumSize(new Dimension(900,650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        DBtemperature.importData(table1, "src/view/files/test.txt","Temperature de la salle 6");
        DBhumidite.importData(table2,"src/view/files/humidite1.txt","Humidité de la salle 6");
        table1.setDefaultRenderer(Object.class,new ColorTable());
        table2.setDefaultRenderer(Object.class,new ColorTableH());


        graphTemp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    LineChart_AWT lineChart_awt = new LineChart_AWT("Graph","Temperature pour chaque 5s");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        graphHum.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    LineChart lineChart = new LineChart("Graph","Humidité pour chaque 5s");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                //dispose();
            }
        });
        activerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                activerButton.setBackground(Color.green);
                desactiverButton.setBackground(Color.RED);

            }
        });
        desactiverButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                desactiverButton.setBackground(Color.green);
                activerButton.setBackground(Color.red);
            }
        });
        activerButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                activerButton1.setBackground(Color.green);
                desactiverButton1.setBackground(Color.RED);
            }
        });
        desactiverButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                desactiverButton1.setBackground(Color.green);
                activerButton1.setBackground(Color.red);
            }
        });
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Controle controle = new Controle();
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        SalleSix salleSix = new SalleSix();

    }

}


