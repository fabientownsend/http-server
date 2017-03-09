package com.server;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpRequest.HttpRequestParser;
import com.server.HttpRequest.HttpRequestProvider;
import com.server.HttpResponse.HttpServerResponse;
import com.server.Routes.RequestController;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.logging.Level;

import static com.server.Main.LOGGER;

public class Server {
    private final OutputStream outputStream;
    private HttpRequestProvider httpRequestProvider;
    private HttpRequestParser httpRequestParser;
    private RequestController requestController;


    public Server(BufferedReader socketInput, OutputStream outputStream, LinkedList<String> memory) {
        this.httpRequestProvider = new HttpRequestProvider(socketInput);
        this.httpRequestParser = new HttpRequestParser();
        this.requestController = new RequestController(memory);
        this.outputStream = outputStream;
    }

    public void start() {
        try {
            String httpRequest = httpRequestProvider.getRequest();
            LOGGER.log(Level.INFO, httpRequest);

            ClientHttpRequest clientHttpRequest = httpRequestParser.parse(httpRequest);
            HttpServerResponse httpServerResponse = new HttpServerResponse(clientHttpRequest.getHttpVersion());

            httpServerResponse = requestController.call(httpServerResponse, clientHttpRequest);
            LOGGER.log(Level.INFO, httpServerResponse.build().toString());

            outputStream.write(httpServerResponse.build());
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.toString());
        }
    }
}
