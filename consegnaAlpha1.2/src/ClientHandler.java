import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket cSocket;
    private helper hp;
    private String regex;
    private final String letters = new String("abcdefghijklmnopqrstuvwxyz");// international letters

    public ClientHandler(Socket socket) {
        this.cSocket = socket;
        hp = new helper();
        this.regex = "-";
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.cSocket.getInputStream()));
            while (this.cSocket.isConnected()) {
                /*
                 * questa è l'unica parte che ho copiato dal suo video spiccicata dal
                 * suo video isto che con le metodologie usate in precedenza da me non
                 * funzionavano correttamente e attualmente rischiavo solo di incasinarmi
                 */

                char[] buf = new char[512];
                int readChars = reader.read(buf);
                if (readChars > 0) {
                    String msg = new String(buf, 0, readChars);
                    System.out.println(msg);
                    String[] protocolParts = msg.split(this.regex);
                    if (protocolParts[1].equals("?help")) {
                        answer(this.hp.solve(protocolParts[2]));
                    } else {
                        String method = protocolParts[0]; // protocol used, default hardcoded is GET
                        String text = protocolParts[1];// input text
                        int key = Integer.parseInt(protocolParts[2]);// key
                        String cipher = protocolParts[3];// cesar o vigenere
                        Boolean isCif = protocolParts[4].equals("T") ? true : false; // cifer or decifer
                        System.out.println(protocolParts[4]);

                        if (cipher.equals("c")) {
                            cesar(text, key, isCif);
                        } else {
                            vigenere(text, key, isCif);
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

    private void cesar(String message, int Key, Boolean isCif) {
        // operation, it's an alpha version so...it's okay so
        // answer((message + "-" + Key + "--with cesar"));
        String out = "";
        if (isCif) {
            int index;
            int j = message.length();
            char at;
            for (int i = 0; i < j; i++) {
                at = message.charAt(i);
                index = this.letters.indexOf(at);
                if (index > 18) {
                    at = this.letters.charAt(index - 23);
                    out = out + at;
                } else {
                    at = this.letters.charAt(index + Key);
                    out = out + at;
                }
            }
        } else {
            int index;
            int j = message.length();
            char at;
            for (int i = 0; i < j; i++) {
                at = message.charAt(i);
                index = this.letters.indexOf(at);
                if (index > 18) {
                    at = this.letters.charAt(index + 23);
                    out = out + at;
                } else {
                    at = this.letters.charAt(index - Key);
                    out = out + at;
                }
            }
        }

        answer((out + " -- key used->" + Key + " -- (with cesar)"));

    }

    private void vigenere(String message, int Key, Boolean isCif) {
        // operation, it's an alpha version so...it's okay so
        // answer((out + " -- key used->" + Key + " -- (with vigenere)"));
    }
}
