package tools;

import java.io.*;
import java.net.Socket;

public class MonThread extends Thread{
    Socket soc;
    FileWriter output = null;
    FileWriter output1 = null;

    MonThread(Socket soc){
        this.soc = soc;
    }

    @Override
    public void run() {
        InputStream flux1 = null;
        try {
            flux1 = soc.getInputStream();
            BufferedReader entree = new BufferedReader(new InputStreamReader(flux1));
            OutputStream flux2 = null;
            flux2 = soc.getOutputStream();
            OutputStreamWriter sortie = new OutputStreamWriter(flux2);
            String message = null;
            message = entree.readLine();
            System.out.println(message);
            output = new FileWriter("src/view/files/test.txt");
            entree.transferTo(output);
            output1 = new FileWriter("src/view/files/humidite1.txt");
            entree.transferTo(output1);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null){
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
