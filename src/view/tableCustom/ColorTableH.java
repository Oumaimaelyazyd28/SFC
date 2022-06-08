package view.tableCustom;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ColorTableH extends DefaultTableCellRenderer {
    public ColorTableH(){}

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Color background = Color.WHITE;

        Object object = table.getValueAt(row,0);

        try {
            float temp = Float.parseFloat(object.toString());
            if(temp>=40 || temp<=60){
                background = Color.getHSBColor(216,56,100);
            }
            if(temp<40 || temp>60){
                background = Color.lightGray;
            }

        }catch (Exception e){

        }


        label.setBackground(background);

        return label;
    }
}
