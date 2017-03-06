package com.server;

import java.util.Hashtable;

public class HttpResponseBuilder {
    private Hashtable<String, Boolean> links ;
    public HttpResponseBuilder() {
        this.links = new Hashtable<>();
        links.put("/", true);
        links.put("/form", true);
        links.put("/method_options", true);
        links.put("/method_options2", true);
    }

    public String build(ClientHttpRequest httpRequest) {
        String httpResponse  = httpRequest.getHttpVersionNumber();

        if (httpRequest.getHttpVerb().equals("POST")) {
            System.out.println("POST " + httpRequest.getUri());
        }

        if (httpRequest.getUri().equals("/redirect")) {
            httpResponse += " 302 Object moved\n";
            httpResponse += "Location: http://localhost:5000/";
        } else if (links.containsKey(httpRequest.getUri())) {
            httpResponse += " 200 OK";
            if (httpRequest.getHttpVerb().equals("OPTIONS") &&
                httpRequest.getUri().equals("/method_options")) {
                httpResponse += "\nAllow: GET,HEAD,POST,OPTIONS,PUT";
            } else if (httpRequest.getUri().equals("/method_options2") ||
                       httpRequest.getUri().equals("/method_options")) {
                httpResponse += "\nAllow: GET,OPTIONS";
            } else if (httpRequest.getUri().equals("/form") && httpRequest.getHttpVerb().equals("GET")) {
                String data = httpRequest.getLastData();
                if (!data.equals("")) {
                    System.out.println("request with data");
                    httpResponse += "\nContent-Length: " + data.length() + "\n";
                    httpResponse += "\n" + data;
                } else {
                    System.out.println("request but data empty");
                }

                for (String lal : httpRequest.getData()) {
                    System.out.println(lal + ", ");
                }
            }
        } else {
            httpResponse += " 404 Not Found";
        }

        return httpResponse;
    }
}
