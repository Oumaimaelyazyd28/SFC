package tools;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class ClientTemp {
    public static void main(String ars[]){
        int port = 1400;
        FileWriter output = null;
        try {
            ServerSocket sercoc = new ServerSocket(port);
            System.out.println("Client");
            while (true){
                Socket soc = sercoc.accept();
                InputStream flux = soc.getInputStream();
                BufferedReader entree = new BufferedReader(new InputStreamReader(flux));
                String message = entree.readLine();
                System.out.println(message);
                try {
                    output = new FileWriter("src/view/files/test.txt");
                    entree.transferTo(output);

                } finally {
                    if (output != null){
                        output.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
