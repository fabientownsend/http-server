package com.server.Routes;

import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class Router {
    private Map<String, Object> routes;

    public Router(LinkedList<String> memory, String publicDirectoryPath) {
        this.routes = new Hashtable<>();
        routes.putAll(addControllers(memory, publicDirectoryPath));
        routes.putAll(addFiles(publicDirectoryPath));
    }

    public BaseController route(String path) {
        if (routes.containsKey(withoutQueries(path))) {
            return (BaseController) routes.get(withoutQueries(path));
        } else {
            return new NotFoundPage();
        }
    }

    private String withoutQueries(String path) {
        return path.split("\\?")[0];
    }

    private Map<String, Object> addControllers(LinkedList<String> memory, String publicDirectoryPath) {
        Map<String, Object> controllers = new Hashtable<>();

        controllers.put("/", new DefaultController(publicDirectoryPath));
        controllers.put("/parameters", new ParametersPage());
        controllers.put("/cookie", new CookieController(memory));
        controllers.put("/eat_cookie", new EatCookieController(memory));
        controllers.put("/logs", new Logs());
        controllers.put("/tea", new Tea());
        controllers.put("/form", new FormPage(memory));
        controllers.put("/redirect", new RedirectPage());
        controllers.put("/method_options", new MethodOptions());
        controllers.put("/method_options2", new MethodOptions2());
        controllers.put("/coffee", new CoffeeController());

        return controllers;
    }

    private Map<String, Object> addFiles(String publicDirectoryPath) {
        Map<String, Object> publicFiles = new Hashtable<>();
        File directory = new File(publicDirectoryPath);
        String[] files = directory.list();

        if (files != null) {
            for (String file : files) {
                publicFiles.put("/" + file, new PublicFilesController(publicDirectoryPath));
            }
        }

        return publicFiles;
    }
}
