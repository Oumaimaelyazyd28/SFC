package system;

import java.io.*;
import java.net.Socket;

public class ServeurHumidite {
    public static void main(String args[]){
        String hote = "127.00.0.1";
        int port = 2000;
        FileReader input = null;
        Socket soc = null;
        System.out.println("Serveur");
        try {
            soc = new Socket(hote,port);
            OutputStream flux = soc.getOutputStream();
            OutputStreamWriter sortie = new OutputStreamWriter(flux);
            try {
                input = new FileReader("src/view/files/humidite.txt");
                char c;
                input.transferTo(sortie);
            } finally {
                if (input != null){
                    input.close();
                }
            }
            sortie.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}