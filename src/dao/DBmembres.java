package dao;
import net.proteanit.sql.DbUtils;
import model.*;


import javax.swing.*;
import java.sql.*;

import static java.lang.Thread.sleep;

public class DBmembres {

    public static Member addMemberToDataBase(String nom, String prenom, String cin, String naissance, String abonnement) {
        Member member = null;

        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO client(nom, prenom, cin, naissance, abonnement)"+"VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,nom);
            preparedStatement.setString(2,prenom);
            preparedStatement.setString(3, cin);
            preparedStatement.setString(4,naissance);
            preparedStatement.setString(5, abonnement);

            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                member = new Member(nom,prenom,cin,naissance,abonnement);
                member.setNom(nom);
                member.setPrenom(prenom);
                member.setCin(cin);
                member.setNaissance(naissance);
                member.setAbonnement(abonnement);
            }
            JOptionPane.showMessageDialog(null,"Membre bien ajouter !");

        }catch (Exception e){
            e.printStackTrace();
        }

        return member;
    }

    public static void modifierMembre(JTable table,JTextField tf1,JTextField tf2,JTextField tf3,JTextField tf4,JComboBox jc){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if (ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez un membre !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "UPDATE client set nom=?,prenom=?,cin=?,naissance=?,abonnement=? where id='"+id+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,tf1.getText().toString());
                preparedStatement.setString(2,tf2.getText().toString());
                preparedStatement.setString(3,tf3.getText().toString());
                preparedStatement.setString(4,tf4.getText().toString());
                preparedStatement.setString(5,jc.getSelectedItem().toString());

                //preparedStatement.setString(8, String.valueOf(table1.getSelectedRow()));
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Modification réussi !");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void supprimerMembre(JTable table){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if(ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez un membre !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM client where id='"+id+"'";
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
        String sql = "SELECT id,nom,prenom,cin,naissance,abonnement,date_inscription FROM client";
        ResultSet resultSet = DBinteraction.select(sql);

        table1.setModel(DbUtils.resultSetToTableModel(resultSet));
    }

    public static void updateCombo(JComboBox comboBox1) throws SQLException {
        DBinteraction.Connect();
        String sql = "SELECT abonnement FROM abon";
        //PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = DBinteraction.select(sql);

        while(resultSet.next()){
            //comboBox1.removeItemAt(0);
            comboBox1.addItem(resultSet.getString("abonnement"));
            //comboBox1.setSelectedItem(resultSet.getString("abonnement"));
        }




    }

    public static void chercherMember(JTable table,JTextField tf){
        DBinteraction.Connect();
        String carte = tf.getText().toString();
        String sql1 = "SELECT id,nom,prenom,cin,naissance,abonnement,date_inscription FROM client WHERE cin='"+carte+"' OR nom='"+carte+"' OR prenom='"+carte+"' OR abonnement='"+carte+"'";
        ResultSet resultSet = DBinteraction.select(sql1);

        table.setModel(DbUtils.resultSetToTableModel(resultSet));
    }


    public static void countMembre(JLabel jb) throws SQLException {
        DBinteraction.Connect();
        String sql = "SELECT cin FROM client";
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

    public static int countMembre2() {
        DBinteraction.Connect();
        String sql = "SELECT cin FROM client";
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
