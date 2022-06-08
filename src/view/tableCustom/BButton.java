package view.tableCustom;

import javax.swing.*;
import java.awt.*;

public class BButton extends JFrame {

    Container c;
    JPanel pan = new JPanel();
    private JButton button12;
    SteelCheckBox b1 = new SteelCheckBox();
    //SteelCheckBox b2 = new SteelCheckBox();
    JLabel label = new JLabel();

    public BButton(){
        c = getContentPane();
        setSize(300,300);

        b1.setSelectedColor(ColorDef.MAGENTA);
        b1.setRised(false);
        b1.setText(" ");
        label.setText(" ");
        pan.add(b1);
        c.add(label);
        c.add(pan);
    }

    public static void main(String[] args){
        BButton frame = new BButton();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setForeground(Color.BLUE);
        frame.setVisible(true);

    }

}
