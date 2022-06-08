package dao;
import net.proteanit.sql.DbUtils;
import model.*;

import javax.swing.*;
import java.sql.*;

import static java.lang.Thread.sleep;

public class DBsport {
    public static Sport addSportToDataBase(String sport, String salle) {
        Sport sport1 = null;

        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO sports(sport, salle)"+"VALUES (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,sport);
            preparedStatement.setString(2,salle);

            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                sport1 = new Sport(sport,salle);
                sport1.setSport(sport);
                sport1.setSalle(salle);
            }
            JOptionPane.showMessageDialog(null,"Sport bien ajouter !");

        }catch (Exception e){
            e.printStackTrace();
        }

        return sport1;
    }

    public static void modifierSport(JTable table, JTextField tf1, JComboBox jc){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if (ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez une sport !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "UPDATE sports set sport=?,salle=? where id='"+id+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,tf1.getText().toString());
                preparedStatement.setString(2,jc.getSelectedItem().toString());

                //preparedStatement.setString(8, String.valueOf(table1.getSelectedRow()));
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Modification réussi !");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void supprimerSport(JTable table){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if(ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez une sport !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM sports where id='"+id+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null,"Supprission réussite !");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void tableLoad(JTable table1){
        DBinteraction.Connect();
        String sql = "SELECT * FROM sports";
        ResultSet resultSet = DBinteraction.select(sql);

        table1.setModel(DbUtils.resultSetToTableModel(resultSet));
    }

    public static void chercherSport(JTable table,JTextField tf){
        DBinteraction.Connect();
        String sport = tf.getText().toString();
        String sql1 = "SELECT * FROM sports WHERE sport='"+sport+"' OR salle='"+sport+"'";
        ResultSet resultSet = DBinteraction.select(sql1);

        table.setModel(DbUtils.resultSetToTableModel(resultSet));

    }

    public static void countSport(JLabel jb) throws SQLException {
        DBinteraction.Connect();
        String sql = "SELECT sport FROM sports";
        ResultSet resultSet = DBinteraction.select(sql);
        int i = 0;
        while (resultSet.next()){
            i++;
        }
        for (int t = 0; t <= i; t++) {
            int n = 0;
            jb.setText(String.valueOf(n+t));
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //break;
        }

    }

    public static int countSport2(){
        DBinteraction.Connect();
        String sql = "SELECT sport FROM sports";
        ResultSet resultSet = DBinteraction.select(sql);
        int i = 0;
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            i++;
        }
        return i;
    }

}
