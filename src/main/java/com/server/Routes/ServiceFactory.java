package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;

public class ServiceFactory {
    private final LinkedList<String> memory;

    public ServiceFactory(LinkedList<String> memory) {
        this.memory = memory;
    }

    public UpstreamService provide(HttpServerResponse httpServerResponse, ClientHttpRequest clientHttpRequest) {
        String uri = clientHttpRequest.getUri();

        Hashtable<String, Boolean> folderContent = getDirectoryFile();

        if (uri.equals("/")) {
            return new DefaultPage(httpServerResponse);
        } else if (uri.equals("/tea")) {
            return new Tea(httpServerResponse);
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
        } else if (folderContent.containsKey(uri)) {
            return new ImagePage(httpServerResponse, clientHttpRequest);
        } else {
            return new NotFoundPage(httpServerResponse);
        }
    }

    private Hashtable getDirectoryFile() {
        String directoryPath = "/Users/fabientownsend/Documents/Java/cob_spec/public/";
        Hashtable<String, Boolean> router = new Hashtable<>();

        File directory = new File(directoryPath);
        String[] files = directory.list();

        for (String file : files) {
            router.put("/" + file, true);
        }
        return router;
    }
}
