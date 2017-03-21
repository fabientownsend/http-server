package com.server.Routes;

import com.server.Routes.Controllers.*;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

public class RouteMap {
    private final Hashtable<String, BaseController> routes;

    public RouteMap(Memory memory, String publicDirectoryPath) {
        this.routes = new Hashtable<>();
        routes.putAll(addControllers(memory, publicDirectoryPath));
        routes.putAll(addFiles(publicDirectoryPath));
    }

    public boolean contain(String path) {
        return routes.containsKey(path);
    }

    public BaseController provide(String queries) {
        return routes.get(queries);
    }

    private Map<String, BaseController> addControllers(Memory memory, String publicDirectoryPath) {
        Map<String, BaseController> controllers = new Hashtable<>();

        controllers.put("/", new DefaultController(publicDirectoryPath));
        controllers.put("/parameters", new ParameterDecodeController());
        controllers.put("/cookie", new CookieController(memory));
        controllers.put("/eat_cookie", new EatCookieController(memory));
        controllers.put("/logs", new LogsController());
        controllers.put("/tea", new TeaController());
        controllers.put("/form", new FormController(memory));
        controllers.put("/redirect", new RedirectionController());
        controllers.put("/method_options", new MethodOptionsController());
        controllers.put("/method_options2", new MethodOptions2Controller());
        controllers.put("/coffee", new CoffeeController());

        return controllers;
    }

    private Map<String, BaseController> addFiles(String publicDirectoryPath) {
        Map<String, BaseController> publicFiles = new Hashtable<>();
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
