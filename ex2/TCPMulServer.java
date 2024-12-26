package tut6.ex2;

import java.io.*;
import java.net.*;

public class TCPMulServer {
    public static void main(String[] args) throws IOException {
        int clientNumber = 0;
        //ServerSocket initialization
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.err.println("Server is waiting to accept user...");
        
        while(true){
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Accept a client!");
            new ClientHandler(connectionSocket, clientNumber++).start();
        }
    }
}
class ClientHandler extends Thread {
    private Socket connectionSocket;
    private int clientNumber;
    public ClientHandler(Socket connectionSocket,int clientNumber) {
        this.connectionSocket = connectionSocket;
        this.clientNumber = clientNumber;
        System.out.println("New connection with client number "
            + this.clientNumber + " at "+ connectionSocket);
    }
    public void run() {
        try {
            String modifiedOutput;
            BufferedReader fromClient = new BufferedReader(
                new InputStreamReader(connectionSocket.getInputStream()));
            BufferedWriter toClient = new BufferedWriter(
                new OutputStreamWriter(connectionSocket.getOutputStream()));
            while(true){
                String clientInput = fromClient.readLine();
                if(clientInput == null){
                    System.out.println("Client number: " + clientNumber + " quit!");
                    break;
                }
                
                modifiedOutput = clientInput.toUpperCase()+"\n";
                toClient.write(modifiedOutput);
                toClient.newLine();
                toClient.flush();
            }
        } catch (Exception e) {
            System.err.println("Error handling client number: " + clientNumber + ": " + e.getMessage());
        } finally {
            try {
                connectionSocket.close();
            } catch (Exception e) {
                System.out.println("Error closing socket: "+e.getMessage());
            }
        }
    }
}
