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
    private HttpResponseBuilder httpResponseBuilder;
    private static final String ERROR_REQUEST = "error request";

    public Server(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
        this.httpRequestParser = new HttpRequestParser();
        this.httpResponseBuilder = new HttpResponseBuilder();
    }

    public void run() {
        try {
            String httpRequest = input.readLine();
            executeClientRequest(httpRequest);
        } catch (IOException e) { }
    }

    private void executeClientRequest(String httpRequest) {
        try {
            httpRequestParser.parse(httpRequest);
            ClientHttpRequest httpClientRequest = new ClientHttpRequest(
                    httpRequestParser.getHttpVerb(),
                    httpRequestParser.getUri(),
                    httpRequestParser.versionNumber()
            );

            String response = httpResponseBuilder.build(httpClientRequest);

            output.println(response);
        } catch (RequestLineFormatException e) {
            output.println(ERROR_REQUEST);
        }

    }
}
