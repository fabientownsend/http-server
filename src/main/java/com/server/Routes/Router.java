package com.server.Routes;

import com.server.Routes.Controllers.BaseController;
import com.server.Routes.Controllers.NotFoundController;

public class Router {
    private final RouteMap routeMap;

    public Router(Memory memory, String publicDirectoryPath) {
        this.routeMap = new RouteMap(memory, publicDirectoryPath);
    }

    public BaseController route(String path) {
        if (routeMap.contain(withoutQueries(path))) {
            return routeMap.provide(withoutQueries(path));
        } else {
            return new NotFoundController();
        }
    }

    private String withoutQueries(String path) {
        return path.split("\\?")[0];
    }
}
