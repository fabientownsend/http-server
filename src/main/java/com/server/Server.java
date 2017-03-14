package com.server;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpRequest.HttpRequestParser;
import com.server.HttpRequest.HttpRequestProvider;
import com.server.HttpResponse.HttpResponse;
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
    private HttpResponse httpResponse;

    public Server(BufferedReader socketInput, OutputStream outputStream, LinkedList<String> memory, String directory) {
        this.httpRequestProvider = new HttpRequestProvider(socketInput);
        this.httpRequestParser = new HttpRequestParser();
        this.requestController = new RequestController(memory, directory);
        this.httpResponse = new HttpResponse("");
        this.outputStream = outputStream;
    }

    public void start() throws IOException {
        try {
            String httpRequest = httpRequestProvider.getRequest();
            LOGGER.log(Level.INFO, "request: " + new String(httpRequest));
            System.out.println(httpRequest);

            ClientHttpRequest clientHttpRequest = httpRequestParser.parse(httpRequest);

            httpResponse = requestController.call(clientHttpRequest);
            LOGGER.log(Level.INFO, "response: " + new String(httpResponse.response()));
        } catch (BadRequestException e) {
            httpResponse.statusCode(400);
            httpResponse.content("The request could not be understood");
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            httpResponse.statusCode(500);
            httpResponse.content("The server encountered an unexpected condition");
            LOGGER.log(Level.WARNING, e.toString());
        } finally {
            outputStream.write(httpResponse.response());
        }
    }
}
