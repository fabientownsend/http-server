package com.server;

import com.server.Routes.Memory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public final static Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) throws IOException {
        File dir = new File("logs");
        dir.mkdir();
        File yourFile = new File("logs/score.txt");
        yourFile.createNewFile();

        FileHandler fileHandle = new FileHandler("logs/logger.log", false);
        fileHandle.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandle);

        ServerSettingsParser serverSettingsParser = new ServerSettingsParser();

        ServerSocket serverSocket = new ServerSocket(serverSettingsParser.getPort());
        Memory memory = new Memory();
        ExecutorService executor = Executors.newFixedThreadPool(100);

        while (true) {
            final Socket clientSocket = serverSocket.accept();
            executor.submit(() -> {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    OutputStream output = clientSocket.getOutputStream();
                    Server server = new Server(input, output, memory, serverSettingsParser.getDirectory());
                    server.start();
                    clientSocket.close();
                } catch (Exception exception) {
                    LOGGER.log(Level.WARNING, exception.getMessage());
                    System.out.println(exception.getMessage());
                }
            });
        }
    }
}
