package com.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public final static Logger LOGGER = Logger.getLogger(Server.class.getName());
    private static FileHandler fileHandle = null;

    public static void main(String[] args) throws IOException {
        fileHandle = new FileHandler("logs/logger.log", false);
        fileHandle.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandle);

        ServerSocket serverSocket = new ServerSocket(5000);
        LinkedList<String> memory = new LinkedList<>();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader input = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            OutputStream output = clientSocket.getOutputStream();

            Server server = new Server(input, output, memory);
            server.start();

            clientSocket.close();
        }
    }
}
