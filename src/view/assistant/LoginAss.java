package view.assistant;

import model.*;
import view.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class LoginAss extends JDialog{
    public Assistant assistant;
    private JPanel loginAssPanel;
    private JTextField tfLogin;
    private JPasswordField pfPassword;
    private JButton seConnecterButton;
    private JButton annulerButton;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JButton retourButton;


    public LoginAss(JFrame parent){
        setTitle("Login");
        setContentPane(loginAssPanel);
        setMinimumSize(new Dimension(900,650));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();


        annulerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });

        //imagePanel.setVisible(false);
        seConnecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = tfLogin.getText();
                String password = String.valueOf(pfPassword.getPassword());

                //cette fct nous permettra de checker si ces paramettres sont vrais ou non!
                assistant = getAuthentificateUser(login,password);

                if (assistant != null){
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(LoginAss.this,
                            "Login ou mot du passe incorrect",
                            "Réssayer",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        retourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Auth auth = new Auth();
                dispose();
            }
        });
        setVisible(true);
    }



    private Assistant getAuthentificateUser(String login, String password){
        Assistant Assistant = null;

        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM assistant WHERE login=? AND pass=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                assistant = new Assistant(login,password);
                assistant.setLogin(login);
                assistant.setPassword(password);

            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return assistant;
    }


    public static void main(String[] args) {
        LoginAss login = new LoginAss(null);
    }

}


