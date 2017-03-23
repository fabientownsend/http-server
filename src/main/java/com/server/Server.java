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

public class Server {
    private final ServerSocket serverSocket;
    private final Memory memory;
    private final ExecutorService executor;
    private final String pathPublicDirectory;

    public Server(int port, String pathPublicDirectory) throws IOException {
        this.pathPublicDirectory = pathPublicDirectory;
        this.serverSocket = new ServerSocket(port);
        this.memory = new Memory();
        this.executor =  Executors.newFixedThreadPool(100);
    }

    public void start() throws IOException {
        while (true) {
            final Socket clientSocket = serverSocket.accept();
            executor.submit(() -> {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    OutputStream output = clientSocket.getOutputStream();
                    SocketHandler socketHandler = new SocketHandler(input, output, memory, pathPublicDirectory);
                    socketHandler.start();
                    clientSocket.close();
                } catch (Exception exception) {
                    ServerLogger.logWarning(exception.getMessage());
                    System.out.println(exception.getMessage());
                }
            });
        }
    }
}
