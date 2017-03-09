package com.server;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpRequest.HttpRequestParser;
import com.server.HttpRequest.HttpRequestProvider;
import com.server.HttpResponse.HttpServerResponse;
import com.server.Routes.ServiceFactory;
import com.server.Routes.UpstreamService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.logging.Level;

import static com.server.Main.LOGGER;

public class Server {
    private final OutputStream outputStream;
    private LinkedList<String> memory;
    private HttpRequestProvider httpRequestProvider;
    private HttpRequestParser httpRequestParser;
    private ServiceFactory serviceFactory;


    public Server(BufferedReader socketInput, OutputStream outputStream, LinkedList<String> memory) throws IOException {
        this.httpRequestProvider = new HttpRequestProvider(socketInput);
        this.memory = memory;
        this.httpRequestParser = new HttpRequestParser();
        this.serviceFactory = new ServiceFactory();
        this.outputStream = outputStream;
    }

    public void start() {
        try {
            String httpRequest = httpRequestProvider.getRequest();
            LOGGER.log(Level.INFO, httpRequest);

            ClientHttpRequest clientHttpRequest = httpRequestParser.parse(httpRequest);
            HttpServerResponse httpServerResponse =
                    new HttpServerResponse(clientHttpRequest.getHttpVersion());
            UpstreamService service =
                    serviceFactory.provide(httpServerResponse, clientHttpRequest, memory);

            httpServerResponse = service.generateContent();
            LOGGER.log(Level.INFO, httpServerResponse.build().toString());

            outputStream.write(httpServerResponse.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
