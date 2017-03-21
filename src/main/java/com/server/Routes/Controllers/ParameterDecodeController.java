package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

import java.net.URLDecoder;

public class ParameterDecodeController implements BaseController {
    public HttpResponse doGet(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);

        String queries = getQueries(clientHttpRequest.getUri());
        String[] parameters = splitParameters(queries);

        String formattedParameters = formatListParameters(parameters);
        if (!formattedParameters.isEmpty()) {
            httpResponse.content(formattedParameters);
        }

        return httpResponse;
    }

    private String getQueries(String uri) {
        return extractParameters(uri);
    }

    private String[] splitParameters(String queries) {
        return queries.split("&");
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

    private String extractParameters(String uri) {
        if (uri.contains("?")) {
            return uri.split("\\?")[1];
        } else {
            return "";
        }
    }
}
