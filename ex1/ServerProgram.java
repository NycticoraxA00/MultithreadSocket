package tut6.ex1;
import java.io.*;
import java.net.*;
public class ServerProgram {
    public static void main(String[] args) {
        int port = 1234; 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is waiting to accept user...");
            int clientNumber = 0;
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept a new client connection
                new ServiceThread(clientSocket, clientNumber++).start(); // Start a new thread for the client
            }
        } catch (IOException e) {
            System.err.println("Error in server: " + e.getMessage());
        }
    }
}
