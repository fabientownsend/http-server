package com.server;

import com.server.Routes.Memory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerLogger.initialise();

        ServerSettingsParser serverSettingsParser = new ServerSettingsParser(args);

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
                    ServerLogger.logWarning(exception.getMessage());
                    System.out.println(exception.getMessage());
                }
            });
        }
    }
}
