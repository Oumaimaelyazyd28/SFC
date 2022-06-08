package tools;

import dao.DBinteraction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class DBtemperature {
    private static String temp;
    private static String salle;

    public static void readData(JButton j){
        try(Scanner input = new Scanner(new File("src/view/files/temperature1.txt"))){
            while (input.hasNextLine()){
                temp = "";
                String line;

                line = input.nextLine();

                try(Scanner data = new Scanner(line)) {
                    while (!data.hasNextInt()){
                        temp += data.next()+" ";
                    }
                    temp = temp.trim();
                }
            }

            System.out.println(temp+"\t");
        }
        catch (IOException e){
            System.out.println("echec !");
        }
        saveData(j);
    }

    public static void saveData(JButton jb){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        salle = jb.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO temperature(temperature, salle)"+"VALUES (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,temp);
            preparedStatement.setString(2,salle);

            //Insert row into the table
            preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void tableLoad(JTable table1, String x){
        DBinteraction.Connect();
        String filePath = x;
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String firstLine = br.readLine().trim();
            String columnsName = firstLine;
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setColumnIdentifiers(new String[]{columnsName});

            Object[] tableLines = br.lines().toArray();
            for (int i=0; i<tableLines.length; i++){
                String line = tableLines[i].toString().trim();
                String dataRow = line;
                model.addRow(new String[]{dataRow});

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static void fileC(String path,String path2){
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            in = new BufferedReader(new FileReader(path));
            out = new BufferedWriter(new FileWriter(path2));
            String c;
            while ((c = in.readLine()) != null){
                out.write(c+"\n");
                out.write("b");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
