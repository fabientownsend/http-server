package com.server;

import java.util.LinkedList;

public class ServiceFactory {
    public UpstreamService provide(ClientHttpRequest clientHttpRequest, LinkedList<String> memory) {
        String uri = clientHttpRequest.getUri();

        if (uri.equals("/")) {
            return new DefaultPage(clientHttpRequest);
        } else if (uri.equals("/form")) {
            return new FormPage(clientHttpRequest, memory);
        } else if (uri.equals("/redirect")) {
            return new RedirectPage(clientHttpRequest);
        } else if (uri.equals("/method_options")) {
            return new MethodOptions(clientHttpRequest);
        } else if (uri.equals("/method_options2")) {
            return new MethodOptions2(clientHttpRequest);
        } else {
            return new NotFoundPage(clientHttpRequest);
        }
    }
}
