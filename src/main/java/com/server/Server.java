package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.server.RequestLineParser.RequestLineFormatException;

public class Server {
    private BufferedReader input;
    private PrintWriter output;
    private HttpRequestParser httpRequestParser;
    private static final String ERROR_REQUEST = "error request";

    public Server(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
        this.httpRequestParser = new HttpRequestParser();
    }

    public void run() {
        try {
            String httpRequest = input.readLine();
            System.out.print(httpRequest);

            try {
                httpRequestParser.parse(httpRequest);
                String response;

                if (httpRequestParser.getUri().equals("/")) {
                    response = httpRequestParser.versionNumber() + " " + 200 + " OK";
                } else if (httpRequestParser.getUri().equals("/form")) {
                    response = httpRequestParser.versionNumber() + " " + 200 + " OK";
                } else {
                    response = httpRequestParser.versionNumber() + " " + 302 + " Object moved" +
                            "\nLocation: http://localhost:5000/";
                }
                output.println(response);
            } catch (RequestLineFormatException e) {
                output.println(ERROR_REQUEST);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
