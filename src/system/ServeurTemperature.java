package system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTemperature {

    public static void main(String args[]){
        String hote = "127.00.0.2";
        int port = 1400;
        FileReader input = null;
        //BufferedReader input = null;
        File file = new File("src/view/files/temperature2.txt");
        Socket soc = null;
        System.out.println("Serveur");
        try {
            soc = new Socket(hote,port);
            OutputStream flux = soc.getOutputStream();
            OutputStreamWriter sortie = new OutputStreamWriter(flux);
            try {
                input = new FileReader("src/view/files/temperature2.txt");

                char c;

                    input.transferTo(sortie);

            } finally {
                if (input != null){
                    input.close();
                }
            }

            //sortie.write("msg\n");
            sortie.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}


