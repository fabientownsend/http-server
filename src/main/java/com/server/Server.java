package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

public class Server {
    private final OutputStream outputStream;
    private LinkedList<String> memory;
    private HttpRequestProvider httpRequestProvider;
    private HttpRequestParser httpRequestParser;
    private ServiceFactory serviceFactory;

    public Server(BufferedReader socketInput, OutputStream outputStream, LinkedList<String> memory) {
        this.httpRequestProvider = new HttpRequestProvider(socketInput);
        this.memory = memory;
        this.httpRequestParser = new HttpRequestParser();
        this.serviceFactory = new ServiceFactory();
        this.outputStream = outputStream;
    }

    public void start() {
        try {
            String httpRequest = httpRequestProvider.getRequest();
            ClientHttpRequest clientHttpRequest = httpRequestParser.parse(httpRequest);
            HttpServerResponse httpServerResponse =
                    new HttpServerResponse(clientHttpRequest.getHttpVersion());
            UpstreamService service =
                    serviceFactory.provide(httpServerResponse, clientHttpRequest, memory);

           httpServerResponse = service.generateContent();

            outputStream.write(httpServerResponse.build());
        } catch (IOException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
