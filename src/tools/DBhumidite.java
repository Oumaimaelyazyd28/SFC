package tools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

import static java.lang.Thread.sleep;

public class DBhumidite {

    public static void importData(JTable jt, String x, String nom){
        String filePath = x;
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //String firstLine = br.readLine().trim();
            String columnsName = nom;
            DefaultTableModel model = (DefaultTableModel) jt.getModel();
            model.setColumnIdentifiers(new String[]{columnsName});

            Object[] tableLines = br.lines().toArray();
            for (int i=0; i<tableLines.length; i++){
                String line = tableLines[i].toString().trim();
                String dataRow = line;

                model.addRow(new String[]{dataRow});
                sleep(200);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
