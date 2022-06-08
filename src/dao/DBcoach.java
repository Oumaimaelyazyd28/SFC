package dao;

import net.proteanit.sql.DbUtils;
import model.Coach;

import javax.swing.*;
import java.sql.*;

import static java.lang.Thread.sleep;

public class DBcoach {
    public static Coach addCoachToDataBase(String nom, String prenom, String cin, String naissance, String telephone, String sport) {
        Coach coach= null;

        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO coach(nom,prenom,cin,naissance,telephone,sport)"+"VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,nom);
            preparedStatement.setString(2,prenom);
            preparedStatement.setString(3, cin);
            preparedStatement.setString(4,naissance);
            preparedStatement.setString(5,telephone);
            preparedStatement.setString(6, sport);


            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                coach = new Coach(nom,prenom,cin,naissance,telephone,sport);
                coach.setNom(nom);
                coach.setPrenom(prenom);
                coach.setCin(cin);
                coach.setNaissance(naissance);
                coach.setTelephone(telephone);
                coach.setSport(sport);
            }
            JOptionPane.showMessageDialog(null,"Coach bien ajouter !");

        }catch (Exception e){
            e.printStackTrace();
        }

        return coach;
    }

    public static void modifierCoach(JTable table,JTextField tf1,JTextField tf2,JTextField tf3,JTextField tf4,JTextField tf5,JComboBox jc){
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
                String sql = "UPDATE coach set nom=?,prenom=?,cin=?,naissance=?,telephone=?,sport=? where id='"+id+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,tf1.getText().toString());
                preparedStatement.setString(2,tf2.getText().toString());
                preparedStatement.setString(3,tf3.getText().toString());
                preparedStatement.setString(4,tf4.getText().toString());
                preparedStatement.setString(5,tf5.getText().toString());
                preparedStatement.setString(6,jc.getSelectedItem().toString());

                //preparedStatement.setString(8, String.valueOf(table1.getSelectedRow()));
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Modification réussi !");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void supprimerCoach(JTable table){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if(ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez un coach !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM coach where id='"+id+"'";
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
        String sql = "SELECT * FROM coach";
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

    public static void chercherMember(JTable table,JTextField tf){
        DBinteraction.Connect();
        String carte = tf.getText().toString();
        String sql1 = "SELECT * FROM coach WHERE cin='"+carte+"' OR nom='"+carte+"' OR prenom='"+carte+"' OR sport='"+carte+"'";
        ResultSet resultSet = DBinteraction.select(sql1);

        table.setModel(DbUtils.resultSetToTableModel(resultSet));



    }

    public static void countCoach(JLabel jb) throws SQLException {
        DBinteraction.Connect();
        String sql = "SELECT cin FROM coach";
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

    public static int countCoach2() {
        DBinteraction.Connect();
        String sql = "SELECT cin FROM coach";
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
