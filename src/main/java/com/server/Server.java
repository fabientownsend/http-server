package com.server;

import com.server.http_request.HttpRequestParser;
import com.server.http_request.HttpRequestProvider;
import com.server.http_request.RequestLineParser.RequestLineFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Server {
    private HttpRequestProvider httpRequestProvider;
    private LinkedList<String> memory;
    private PrintWriter output;
    private HttpRequestParser httpRequestParser;
    private HttpResponseBuilder httpResponseBuilder;
    private static final String ERROR_REQUEST = "error request";

    public Server(BufferedReader input, PrintWriter output) {
        this.output = output;
        this.httpRequestParser = new HttpRequestParser();
        this.httpResponseBuilder = new HttpResponseBuilder();
        this.httpRequestProvider = new HttpRequestProvider(input);
    }

    public Server(BufferedReader input, PrintWriter output, LinkedList<String> memory) {
        this(input, output);
        this.memory = memory;
    }

    public void run() {
        try {
            String header = httpRequestProvider.extractHeader();

            try {
                httpRequestParser.parse(header);
            } catch (RequestLineFormatException e) {
                e.printStackTrace();
            }

            if (httpRequestParser.hasBody(header) && httpRequestParser.getUri().equals("/form")) {
                int bodySize = httpRequestParser.contentLength(header);
                String body = httpRequestProvider.extractBody(bodySize);
                memory.add(0, body);
            }

            executeClientRequest(httpRequestParser.getRequestLine(header));
        } catch (IOException e) { }
    }

    private void executeClientRequest(String httpRequest) {
        try {
            httpRequestParser.parse(httpRequest);
            ClientHttpRequest httpClientRequest = new ClientHttpRequest(
                    httpRequestParser.getHttpVerb(),
                    httpRequestParser.getUri(),
                    httpRequestParser.versionNumber(),
                    memory
            );

            if (!httpRequestParser.getBody().equals("")) {
                memory.add(0, httpRequestParser.getBody());
            }

            String response = httpResponseBuilder.build(httpClientRequest);

            output.println(response);
        } catch (RequestLineFormatException e) {
            output.println(ERROR_REQUEST);
        }
    }
}
