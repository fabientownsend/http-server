package com.server;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpRequest.HttpRequestParser;
import com.server.HttpRequest.HttpRequestProvider;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.Memory;
import com.server.Routes.RouteAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

public class SocketHandler {
    private final OutputStream outputStream;
    private HttpRequestProvider httpRequestProvider;
    private HttpRequestParser httpRequestParser;
    private RouteAction routeAction;
    private HttpResponse httpResponse;

    public SocketHandler(BufferedReader socketInput, OutputStream outputStream, Memory memory, String directory) {
        this.httpRequestProvider = new HttpRequestProvider(socketInput);
        this.httpRequestParser = new HttpRequestParser();
        this.routeAction = new RouteAction(memory, directory);
        this.httpResponse = new HttpResponse("");
        this.outputStream = outputStream;
    }

    public void start() throws IOException {
        try {
            String socketOutput = httpRequestProvider.getRequest();
            ServerLogger.logInfo("request: " + new String(socketOutput));
            System.out.println(socketOutput);

            ClientHttpRequest clientHttpRequest = httpRequestParser.parse(socketOutput);

            httpResponse = routeAction.executeResult(clientHttpRequest);
        } catch (BadRequestException e) {
            httpResponse.statusCode(HttpStatusCode.BAD_REQUEST);
            httpResponse.content("The request could not be understood");
            ServerLogger.logInfo(e.getMessage());
        } catch (Exception e) {
            httpResponse.statusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            httpResponse.content("The server encountered an unexpected condition");
            ServerLogger.logInfo(e.getMessage());
        } finally {
            outputStream.write(httpResponse.response());
        }
    }
}
