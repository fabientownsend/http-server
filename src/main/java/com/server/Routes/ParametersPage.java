package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

import java.net.URLDecoder;

public class ParametersPage implements BaseController {
    private final ClientHttpRequest clientHttpRequest;
    private HttpServerResponse httpServerResponse;

    public ParametersPage(HttpServerResponse httpServerResponse, ClientHttpRequest clientHttpRequest) {
        this.httpServerResponse = httpServerResponse;
        this.clientHttpRequest = clientHttpRequest;
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(200);
        String[] parameters = splitParameters(clientHttpRequest.getUri());

        String formattedParameters = formatListParameters(parameters);
        if (!formattedParameters.isEmpty()) {
            httpServerResponse.setBody(formattedParameters);
        }

        return httpServerResponse;
    }

    private String formatListParameters(String[] parameters) {
        if (parameters[0].isEmpty()) {
            return "";
        }

        String formattedParameters = "";
        for (int i = 0; i < parameters.length; i++) {
            String parameter = extractParameter(parameters[i], i);

            if (!parameter.isEmpty()) {
                formattedParameters += parameter;
            }
        }

        return formattedParameters;
    }

    private String extractParameter(String parameter, int i) {
        String formattedParameters = "";
        String first;
        String second;
        first = parameter.split("=")[0];
        second = parameter.split("=")[1];
        try {
            formattedParameters += first + " = " + URLDecoder.decode(second, "UTF-8");
        } catch (Exception e) { }

        return formattedParameters;
    }

    private String[] splitParameters(String uri) {
        return extractParameters(uri).split("&");
    }

    private String extractParameters(String uri) {
        if (uri.contains("?")) {
            return uri.split("\\?")[1];
        } else {
            return "";
        }
    }
}
