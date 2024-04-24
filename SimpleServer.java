import java.io.*;
import java.net.*;

/*
 * This class represents a simple server program that listens for incoming connections
 * from clients over a TCP socket and communicates with them.
 */
public class SimpleServer {
	
	/**
     * The main method of the SimpleServer class.
     * This method listens for incoming client connections, accepts them, and communicates with each client individually.
     * 
     */
    public static void main(String[] args) {
        final int PORT = 1254;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());

                try (DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    out.writeUTF("Hello from server!\n");

                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Message from client: " + message);
                        if (message.equals("finish")) {
                            break; // Break out of the loop if "finish" message received
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
