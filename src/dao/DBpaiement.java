package dao;

import net.proteanit.sql.DbUtils;
import model.*;

import javax.swing.*;
import java.sql.*;

public class DBpaiement {
    public static Paiement addPaiementToDataBase(String cin, String membre, String mois, String frais, String verse, String reste) {
        Paiement paiement = null;

        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO paiement(cin,membre,mois,frais,verse,reste)"+"VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,cin);
            preparedStatement.setString(2,membre);
            preparedStatement.setString(3, mois);
            preparedStatement.setString(4,frais);
            preparedStatement.setString(5, verse);
            preparedStatement.setString(6, reste);

            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                paiement = new Paiement(cin,membre,mois,frais,verse,reste);
                paiement.setCin(cin);
                paiement.setMembre(membre);
                paiement.setMois(mois);
                paiement.setFrais(frais);
                paiement.setVerse(verse);
                paiement.setReste(reste);
            }
            JOptionPane.showMessageDialog(null,"Membre bien ajouter !");

        }catch (Exception e){
            e.printStackTrace();
        }

        return paiement;
    }

    public static void modifierPaiement(JTable table,JTextField tf1,JTextField tf2,JComboBox jc,JTextField tf3,JTextField tf4,JTextField tf5){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if (ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez un paiement !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "UPDATE paiement set cin=?,membre=?,mois=?,frais=?,verse=?,reste=? where id='"+id+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,tf1.getText().toString());
                preparedStatement.setString(2,tf2.getText().toString());
                preparedStatement.setString(3,jc.getSelectedItem().toString());
                preparedStatement.setString(4,tf3.getText().toString());
                preparedStatement.setString(5,tf4.getText().toString());
                preparedStatement.setString(6,tf5.getText().toString());

                //preparedStatement.setString(8, String.valueOf(table1.getSelectedRow()));
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Modification réussi !");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void supprimerPaiement(JTable table){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if(ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez un paiement !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM paiement where id='"+id+"'";
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
        String sql = "SELECT * FROM paiement";
        ResultSet resultSet = DBinteraction.select(sql);

        table1.setModel(DbUtils.resultSetToTableModel(resultSet));
    }


    public static void chercherPaiement(JTable table,JTextField tf){
        DBinteraction.Connect();
        String carte = tf.getText().toString();
        String sql1 = "SELECT * FROM paiement WHERE cin='"+carte+"' OR membre='"+carte+"'";
        ResultSet resultSet = DBinteraction.select(sql1);

        table.setModel(DbUtils.resultSetToTableModel(resultSet));
    }


    public static void remplirMembre(JTextField tf1, JTextField tf2){
        DBinteraction.Connect();
        String cin = tf1.getText().toString();
        String sql1 = "SELECT nom,prenom FROM client WHERE cin='"+cin+"'";
        ResultSet resultSet = DBinteraction.select(sql1);
        String mm = null;
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
            mm = resultSet.getString("nom")+" "+resultSet.getString("prenom");
        } catch (SQLException e) {
            e.printStackTrace();
        }}
        tf2.setText(mm);
    }

    public static void remplirFrais(JTextField tf1, JTextField tf2){
        DBinteraction.Connect();
        String cin = tf1.getText().toString();
        String sql1 = "SELECT abonnement FROM client WHERE cin='"+cin+"'";
        ResultSet resultSet = DBinteraction.select(sql1);
        String mm = null;
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                mm = resultSet.getString("abonnement");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String sql2 = "SELECT prix FROM abon WHERE abonnement='"+mm+"'";
        ResultSet resultSet2 = DBinteraction.select(sql2);
        String mm1 = null;
        while (true){
            try {
                if (!resultSet2.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                mm1 = resultSet2.getString("prix");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        tf2.setText(mm1);

    }

}
