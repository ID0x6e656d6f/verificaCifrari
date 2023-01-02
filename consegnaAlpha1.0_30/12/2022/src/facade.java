import java.net.*;
import java.util.*;

public class facade {
    public static int port = 64258;

    public static void main(String[] args) throws Exception {
        System.out.println("Server loading...");
        ArrayList<ClientHandler> handlers = new ArrayList<ClientHandler>();
        try (ServerSocket sSocket = new ServerSocket(port)) {
            System.out.format("Server started: %s, waiting at %s\n", sSocket.getInetAddress().getHostAddress(),
                    sSocket.getLocalPort());
            try {
                Socket cSocket = sSocket.accept();
                System.out.format("Client connected: %s\n", cSocket.getInetAddress().getHostAddress());
                ClientHandler cH = new ClientHandler(cSocket);
                cH.start();
                handlers.add(cH);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
