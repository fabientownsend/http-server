package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Server {
    private PrintWriter output;
    private LinkedList<String> memory;
    private HttpRequestProvider httpRequestProvider;
    private HttpRequestParser httpRequestParser;
    private ServiceFactory serviceFactory;

    public Server(BufferedReader socketInput, PrintWriter socketOutput, LinkedList<String> memory) {
        this.httpRequestProvider = new HttpRequestProvider(socketInput);
        this.output = socketOutput;
        this.memory = memory;
        this.httpRequestParser = new HttpRequestParser();
        this.serviceFactory = new ServiceFactory();
    }

    public void start() {
        try {
            String httpRequest = httpRequestProvider.getRequest();
            ClientHttpRequest clientHttpRequest = httpRequestParser.parse(httpRequest);
            UpstreamService service = serviceFactory.provide(clientHttpRequest, memory);
            String response = service.generateContent();

            output.println(response);
        } catch (IOException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
