package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        LinkedList<String> memory = new LinkedList<>();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader input = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            Server server = new Server(input, output, memory);
            server.start();

            clientSocket.close();
        }
    }
}
