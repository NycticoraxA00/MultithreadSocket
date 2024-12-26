package tut6.ex2;

import java.io.*;
import java.net.*;

public class TCPMulClient{
    public static void main(String[] args) throws Exception{
        String input;
        String modifiedOutput;
        Socket clientSocket = new Socket("localhost", 6789);
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                clientSocket.getOutputStream()));
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                clientSocket.getInputStream()));
        BufferedReader userInput = new BufferedReader(
            new InputStreamReader(System.in));

        while(true){
            System.out.println("Please enter your message");
            input = userInput.readLine();
            writer.write(input+"\n");
            writer.newLine();
            writer.flush();
            modifiedOutput = reader.readLine();
            System.out.println("FROM SERVER: "+ modifiedOutput);
        }
    }
}