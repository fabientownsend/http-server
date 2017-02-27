package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Server {
    private BufferedReader input;
    private PrintWriter output;

    public Server(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        try {
            output.println(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
