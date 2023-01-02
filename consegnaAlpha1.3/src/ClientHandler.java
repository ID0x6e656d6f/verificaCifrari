import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket cSocket;
    private messageHandler mHandler;
    private cypher c;

    public ClientHandler(Socket socket) {
        this.cSocket = socket;
        mHandler = new messageHandler();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.cSocket.getInputStream()));
            while (this.cSocket.isConnected()) {

                char[] buf = new char[512];
                int readChars = reader.read(buf);
                if (readChars > 0) {
                    String msg = new String(buf, 0, readChars);
                    mHandler = new messageHandler();
                    answer(mHandler.hand(msg));
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
}
