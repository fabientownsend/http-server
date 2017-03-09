package com.server;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpRequest.HttpRequestParser;
import com.server.HttpRequest.HttpRequestProvider;
import com.server.HttpResponse.HttpServerResponse;
import com.server.Routes.RequestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.logging.Level;

import static com.server.Main.LOGGER;

public class Server {
    private final OutputStream outputStream;
    private HttpRequestProvider httpRequestProvider;
    private HttpRequestParser httpRequestParser;
    private RequestController requestController;
    private HttpServerResponse httpServerResponse;

    public Server(BufferedReader socketInput, OutputStream outputStream, LinkedList<String> memory) {
        this.httpRequestProvider = new HttpRequestProvider(socketInput);
        this.httpRequestParser = new HttpRequestParser();
        this.requestController = new RequestController(memory);
        this.httpServerResponse = new HttpServerResponse("");
        this.outputStream = outputStream;
    }

    public void start() throws IOException {
        try {
            String httpRequest = httpRequestProvider.getRequest();
            LOGGER.log(Level.INFO, httpRequest);

            ClientHttpRequest clientHttpRequest = httpRequestParser.parse(httpRequest);
            httpServerResponse = new HttpServerResponse(clientHttpRequest.getHttpVersion());

            httpServerResponse = requestController.call(httpServerResponse, clientHttpRequest);
            System.out.println(httpServerResponse.build().toString());
            LOGGER.log(Level.INFO, httpServerResponse.build().toString());
        } catch (BadRequestException e) {
            httpServerResponse.setHttpResponseCode(400);
            httpServerResponse.setBody("The request could not be understood");
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            httpServerResponse.setHttpResponseCode(500);
            httpServerResponse.setBody("The server encountered an unexpected condition");
            LOGGER.log(Level.WARNING, e.toString());
        } finally {
            outputStream.write(httpServerResponse.build());
        }
    }
}
