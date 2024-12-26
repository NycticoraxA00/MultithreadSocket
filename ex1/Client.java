package tut6.ex1;

import java.io.*;
import java.net.*;
import java.util.Date;

public class Client {
    public static void main(String[] args) {
        String serverHost = "localhost";
        int port = 1234;
        try {
            Socket clientSocket = new Socket(serverHost, port);
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    clientSocket.getInputStream()));
            BufferedReader userInput = new BufferedReader(
                new InputStreamReader(System.in));
            //Greeting user
            writer.write("Hello, Today is "+ new Date());
            writer.newLine();
            writer.flush();
            String response = reader.readLine();
            System.out.println("Server: "+ response);

            //Send another message
            writer.write("I am your machine");
            writer.newLine();
            writer.flush();
            response = reader.readLine();
            System.out.println("Server: "+ response);

            //Message loop
            String userMessage;
            while(true){
                System.out.println("Enter your message: ");
                userMessage = userInput.readLine();
                writer.write(userMessage);
                writer.newLine();
                writer.flush();
                response = reader.readLine();
                System.out.println("Server: "+response);
                if ("QUIT".equalsIgnoreCase(userMessage) || response == null) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error in client: " + e.getMessage());
        }
    }
}
