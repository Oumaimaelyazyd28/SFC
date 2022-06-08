package dao;
import net.proteanit.sql.DbUtils;
import model.*;

import javax.swing.*;
import java.sql.*;

public class DBabonnement {
    public static Abonnement addAbonnementToDataBase(String abonnement, String sport, String type, String heure, String prix) {
        Abonnement abonnement1 = null;

        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO abon(abonnement, sport, type, heure, prix)"+"VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,abonnement);
            preparedStatement.setString(2,sport);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4,heure);
            preparedStatement.setString(5, prix);

            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                abonnement1 = new Abonnement(abonnement,sport,type,heure,prix);
                abonnement1.setAbonnement(abonnement);
                abonnement1.setSport(sport);
                abonnement1.setType(type);
                abonnement1.setHeure(heure);
                abonnement1.setPrix(prix);
            }
            JOptionPane.showMessageDialog(null,"Abonnement bien ajouter !");

        }catch (Exception e){
            e.printStackTrace();
        }

        return abonnement1;
    }

    public static void modifierAbonnement(JTable table,JTextField tf1,JComboBox jc,JComboBox jc2,JTextField tf2,JTextField tf3){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if (ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez un abonnement !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "UPDATE abon set abonnement=?,sport=?,type=?,heure=?,prix=? where id='"+id+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,tf1.getText().toString());
                preparedStatement.setString(2,jc.getSelectedItem().toString());
                preparedStatement.setString(3,jc2.getSelectedItem().toString());
                preparedStatement.setString(4,tf2.getText().toString());
                preparedStatement.setString(5,tf3.getText().toString());

                //preparedStatement.setString(8, String.valueOf(table1.getSelectedRow()));
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Modification réussite !");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void supprimerAbonnement(JTable table){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if(ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez un abonnement !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM abon where id='"+id+"'";
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
        String sql = "SELECT * FROM abon";
        ResultSet resultSet = DBinteraction.select(sql);

        table1.setModel(DbUtils.resultSetToTableModel(resultSet));
    }

    public static void updateCombo(JComboBox comboBox1) throws SQLException {
        DBinteraction.Connect();
        String sql = "SELECT sport FROM sports";
        //PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = DBinteraction.select(sql);

        while(resultSet.next()){
            comboBox1.addItem(resultSet.getString("sport"));
        }

    }

    public static void chercherAbonnement(JTable table,JTextField tf){
        DBinteraction.Connect();
        String abonn = tf.getText().toString();
        String sql1 = "SELECT * FROM abon WHERE abonnement='"+abonn+"' OR sport='"+abonn+"' OR type='"+abonn+"'";
        ResultSet resultSet = DBinteraction.select(sql1);

        table.setModel(DbUtils.resultSetToTableModel(resultSet));
    }


}
