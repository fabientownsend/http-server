package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.server.http_request.HttpRequestParser;
import com.server.http_request.RequestLineParser.RequestLineFormatException;

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
        executeClientRequest();
    }

    private void executeClientRequest() {
        try {
            String httpRequest = input.readLine();

            try {
                httpRequestParser.parse(httpRequest);
                String response;

                if (httpRequestParser.getUri().equals("/")) {
                    response = httpRequestParser.versionNumber() + " " + 200 + " OK";
                } else if (httpRequestParser.getUri().equals("/form")) {
                    response = httpRequestParser.versionNumber() + " " + 200 + " OK";
                } else if (httpRequestParser.getHttpVerb().equals("OPTIONS") &&
                        httpRequestParser.getUri().equals("/method_options")) {
                    response = httpRequestParser.versionNumber() + " " + 200 + " OK\n" +
                            "Allow: GET,HEAD,POST,OPTIONS,PUT";
                } else if (httpRequestParser.getHttpVerb().equals("OPTIONS") &&
                        httpRequestParser.getUri().equals("/method_options2")) {
                    response = httpRequestParser.versionNumber() + " " + 200 + " OK\n" +
                            "Allow: GET,OPTIONS";
                } else if ((httpRequestParser.getHttpVerb().equals("GET") ||
                            httpRequestParser.getHttpVerb().equals("PUT") ||
                            httpRequestParser.getHttpVerb().equals("HEAD") ||
                            httpRequestParser.getHttpVerb().equals("POST")) &&
                        httpRequestParser.getUri().equals("/method_options")) {
                    response = httpRequestParser.versionNumber() + " " + 200 + " OK\n" +
                            "Allow: GET,OPTIONS";
                } else if (httpRequestParser.getUri().equals("/method_options2")) {
                    response = httpRequestParser.versionNumber() + " " + 200 + " OK";
                } else if (httpRequestParser.getUri().equals("/redirect")) {
                    response = httpRequestParser.versionNumber() + " " + 302 + " Object moved" +
                            "\nLocation: http://localhost:5000/";
                } else {
                    response = httpRequestParser.versionNumber() + " " + 404 + " Not Found";
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
