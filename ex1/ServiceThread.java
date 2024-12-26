package tut6.ex1;

import java.io.*;
import java.net.*;

public class ServiceThread extends Thread{
    private int clientNumber;
    private Socket clientSocket;

    public ServiceThread(Socket clientSocket, int clientNumber) {
        this.clientNumber = clientNumber;
        this.clientSocket=clientSocket;
        System.out.println("New connection with client number "
            + this.clientNumber + " at "+ clientSocket);
    }
    @Override
    public void run() {
        try (
            BufferedReader input = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(clientSocket.getOutputStream()))) {
            String line;
            while ((line = input.readLine()) != null) {
                output.write(">> " + line);
                output.newLine();
                output.flush();
                if ("QUIT".equalsIgnoreCase(line)) {
                    System.out.println("Client number: " + clientNumber + " quit!");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client number: " + clientNumber + ": " + e.getMessage());
        }
    }
    
}
