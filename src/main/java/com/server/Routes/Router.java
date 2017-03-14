package com.server.Routes;

import com.server.Cookie.Cookie;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;

public class Router {
    public BaseController route(ClientHttpRequest clientHttpRequest, LinkedList<String> memory, String directory) {
        String uri = clientHttpRequest.getUri();
        HttpServerResponse httpServerResponse = new HttpServerResponse(clientHttpRequest.getHttpVersion());

        Hashtable<String, Boolean> folderContent = getDirectoryFile(directory);

        if (uri.equals("/")) {
            return new DefaultPage(clientHttpRequest, directory);
        } else if (uri.startsWith("/parameters")) {
            return new ParametersPage(clientHttpRequest);
        } else if (uri.startsWith("/cookie")) {
            return new Cookie(clientHttpRequest, memory);
        } else if (uri.equals("/eat_cookie")) {
            return new EatCookie(clientHttpRequest, memory);
        } else if (uri.equals("/logs")) {
            return new Logs(clientHttpRequest);
        } else if (uri.equals("/tea")) {
            return new Tea(httpServerResponse);
        } else if (uri.equals("/form")) {
            return new FormPage(clientHttpRequest, memory);
        } else if (uri.equals("/redirect")) {
            return new RedirectPage(clientHttpRequest);
        } else if (uri.equals("/method_options")) {
            return new MethodOptions(clientHttpRequest);
        } else if (uri.equals("/method_options2")) {
            return new MethodOptions2(clientHttpRequest);
        } else if (uri.equals("/coffee")) {
            return new Coffee(clientHttpRequest);
        } else if (folderContent.containsKey(uri)) {
            return new FileProviderPage(clientHttpRequest, directory);
        } else {
            return new NotFoundPage(clientHttpRequest);
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
