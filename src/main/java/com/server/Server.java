package com.server;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Server {
    private final BufferedReader input;
    private final PrintWriter output;
    private final String memory;

    public Server(BufferedReader socketInput, PrintWriter socketOutput, String memory) {
        this.input = socketInput;
        this.output = socketOutput;
        this.memory = memory;
    }

    public void start() {
        output.println("HTTP/1.1 200 OK");
    }
}
