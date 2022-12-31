import java.io.*;
import java.net.*;

import javax.xml.transform.Source;

public class ClientHandler extends Thread {
    private Socket cSocket;
    private helper hp;
    private String regex;

    public ClientHandler(Socket socket) {
        this.cSocket = socket;
        hp = new helper();
        this.regex = "|";
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.cSocket.getInputStream()));
            while (this.cSocket.isConnected()) {
                // questa è l'unica parte che ho copiato dal suo video spiccicata dal
                // suo video isto che con le metodologie usate in precedenza da me non
                // funzionavano
                // correttamente e attualmente rischiavo solo di incasinarmi

                char[] buf = new char[512];
                int readChars = reader.read(buf);
                if (readChars > 0) {
                    String msg = new String(buf, 0, readChars);
                    System.out.println(msg);
                    if (msg.equals("?help")) {
                        answer(this.hp.solve(msg));
                    } else {
                        String[] protocolParts = msg.split("-");
                        String method = protocolParts[0]; // protocol used, default hardcoded is GET
                        String text = protocolParts[1];// input text
                        String key = protocolParts[2];// key
                        String cipher = protocolParts[3];// cesar o vigenere
                        Boolean isCif = protocolParts[4] == "T" ? true : false; // cifer or decifer

                        if (cipher.equals("c")) {
                            System.out.println("cesare");
                            cesar(text, key, isCif);
                        } else {
                            System.out.println("vigenere");
                            vignere(text, key, isCif);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void answer(String message) {
        if (this.cSocket.isConnected()) {
            try {
                PrintWriter out = new PrintWriter(this.cSocket.getOutputStream(), true);
                out.println(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void cesar(String message, String Key, Boolean isCif) {
        // operation, it's an alpha version so...it's okay so
        answer(message);

    }

    private void vignere(String message, String Key, Boolean isCif) {
        // operation, it's an alpha version so...it's okay so
        answer(message);
    }
}
