package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        ServerSettingsParser serverSettingsParser = new ServerSettingsParser();
        serverSettingsParser.parse(args);

        try {
            ServerSocket serverSocket = new ServerSocket(serverSettingsParser.getPort());
            while (true) {
                System.out.println("Socked waiting");
                Socket clientSocket = serverSocket.accept();

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                Server server = new Server(input, output);
                server.run();
                clientSocket.close();
                System.out.println("Socked closed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
