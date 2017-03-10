package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public final static Logger LOGGER = Logger.getLogger(Server.class.getName());
    private static FileHandler fileHandle = null;

    public static void main(String[] args) throws IOException {
        fileHandle = new FileHandler("logs/logger.log", false);
        fileHandle.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandle);
        Socket clientSocket;

        ServerSettingsParser serverSettingsParser = new ServerSettingsParser();

        ServerSocket serverSocket = new ServerSocket(serverSettingsParser.getPort());
        LinkedList<String> memory = new LinkedList<>();

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                OutputStream output = clientSocket.getOutputStream();

                Server server = new Server(input, output, memory, serverSettingsParser.getDirectory());
                server.start();
                clientSocket.close();
            } catch (Exception exception) {
                LOGGER.log(Level.WARNING, exception.getMessage());
                System.out.println(exception.getMessage());
            }
        }
    }
}
