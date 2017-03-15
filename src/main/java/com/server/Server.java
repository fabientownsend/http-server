package com.server;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpRequest.HttpRequestParser;
import com.server.HttpRequest.HttpRequestProvider;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.RequestController;
import com.server.Routes.Memory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;

import static com.server.Main.LOGGER;

public class Server {
    private final OutputStream outputStream;
    private HttpRequestProvider httpRequestProvider;
    private HttpRequestParser httpRequestParser;
    private RequestController requestController;
    private HttpResponse httpResponse;

    public Server(BufferedReader socketInput, OutputStream outputStream, Memory memory, String directory) {
        this.httpRequestProvider = new HttpRequestProvider(socketInput);
        this.httpRequestParser = new HttpRequestParser();
        this.requestController = new RequestController(memory, directory);
        this.httpResponse = new HttpResponse("");
        this.outputStream = outputStream;
    }

    public void start() throws IOException {
        try {
            String socketOutput = httpRequestProvider.getRequest();
            LOGGER.log(Level.INFO, "request: " + new String(socketOutput));
            System.out.println(socketOutput);

            ClientHttpRequest clientHttpRequest = httpRequestParser.parse(socketOutput);

            httpResponse = requestController.call(clientHttpRequest);
            LOGGER.log(Level.INFO, "response: " + new String(httpResponse.response()));
        } catch (BadRequestException e) {
            httpResponse.statusCode(HttpStatusCode.BAD_REQUEST);
            httpResponse.content("The request could not be understood");
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            httpResponse.statusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            httpResponse.content("The server encountered an unexpected condition");
            LOGGER.log(Level.WARNING, e.toString());
        } finally {
            outputStream.write(httpResponse.response());
        }
    }
}
