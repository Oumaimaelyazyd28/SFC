package tools;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurPrincipale {
    public static void main(String[] args){
        int port = 1000;
        ServerSocket sersoc = null;
        try {
            sersoc = new ServerSocket(port);
            System.out.println("Serveur Principale");
            while (true){
                Socket soc = sersoc.accept();
                Thread mt = new MonThread(soc);
                mt.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
