package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Server {
    private BufferedReader input;
    private PrintWriter output;
    private RequestLineParser requestLineParser;
    private static final String ERROR_REQUEST = "error request";

    public Server(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
        this.requestLineParser = new RequestLineParser();
    }

    public void run() {
        try {
            String httpRequest = input.readLine();

            try {
                requestLineParser.parse(httpRequest);
                String response = requestLineParser.versionNumber() + " " + 200 + " OK";
                output.println(response);
            } catch (RequestLineParser.RequestLineFormatException e) {
                output.println(ERROR_REQUEST);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
