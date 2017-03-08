package com.server;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceFactory {
    public UpstreamService provide(
            HttpServerResponse httpServerResponse,
            ClientHttpRequest clientHttpRequest,
            LinkedList<String> memory) {
        String uri = clientHttpRequest.getUri();
        Pattern pattern = Pattern.compile("([^\\s]+(\\.(?i)(txt|jpeg|jpg|png|gif|bmp))$)");
        Matcher matcher = pattern.matcher(uri);

        if (uri.equals("/")) {
            return new DefaultPage(httpServerResponse);
        } else if (uri.equals("/form")) {
            return new FormPage(httpServerResponse, clientHttpRequest, memory);
        } else if (uri.equals("/redirect")) {
            return new RedirectPage(httpServerResponse);
        } else if (uri.equals("/method_options")) {
            return new MethodOptions(httpServerResponse, clientHttpRequest);
        } else if (uri.equals("/method_options2")) {
            return new MethodOptions2(httpServerResponse);
        } else if (uri.equals("/coffee")) {
            return new Coffee(httpServerResponse);
        } else if (matcher.matches() || uri.equals("/file1")) {
            return new ImagePage(httpServerResponse, clientHttpRequest);
        } else {
            return new NotFoundPage(httpServerResponse);
        }
    }
}
