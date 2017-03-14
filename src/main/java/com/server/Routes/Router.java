package com.server.Routes;

import com.server.Cookie.Cookie;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;

public class Router {
    public BaseController route(HttpServerResponse httpServerResponse, ClientHttpRequest clientHttpRequest, LinkedList<String> memory, String directory) {
        String uri = clientHttpRequest.getUri();

        Hashtable<String, Boolean> folderContent = getDirectoryFile(directory);

        if (uri.equals("/")) {
            return new DefaultPage(httpServerResponse, directory);
        } else if (uri.startsWith("/parameters")) {
            return new ParametersPage(httpServerResponse, clientHttpRequest);
        } else if (uri.startsWith("/cookie")) {
            return new Cookie(httpServerResponse, clientHttpRequest, memory);
        } else if (uri.equals("/logs")) {
            return new Logs(httpServerResponse, clientHttpRequest);
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
            return new FileProviderPage(httpServerResponse, clientHttpRequest, directory);
        } else {
            return new NotFoundPage(httpServerResponse);
        }
    }

    private Hashtable getDirectoryFile(String directoryPath) {
        Hashtable<String, Boolean> router = new Hashtable<>();

        File directory = new File(directoryPath);
        String[] files = directory.list();

        for (String file : files) {
            router.put("/" + file, true);
        }
        return router;
    }
}
