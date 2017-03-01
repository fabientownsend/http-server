package com.server.http_request;

public class RequestLineParser {
    private String httpVerb;
    private String uri;
    private String httpVersionNumber;

    public void parse(String httpRequest) throws RequestLineFormatException {
        String[] requestLines = httpRequest.split("\\s+");

        if (requestLines.length != 3) {
            throw new RequestLineFormatException();
        }

        this.httpVerb = requestLines[0];
        this.uri = requestLines[1];
        this.httpVersionNumber = requestLines[2];

        httpVerbChecker();
    }

    private void httpVerbChecker() throws RequestLineFormatException {
        for (HttpVerb verb : HttpVerb.values()) {
            if (verb.name().equals(httpVerb)) {
                return;
            }
        }
        throw new RequestLineFormatException();
    }

    public String getHttpVerb() {
        return httpVerb;
    }

    public String getUri() {
        return uri;
    }

    public String versionNumber() {
        return httpVersionNumber;
    }

    enum HttpVerb {
        DELETE, GET, HEAD, PUT, POST
    }

    public class RequestLineFormatException extends Exception {

    }
}
