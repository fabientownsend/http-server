package com.server;

import com.server.RequestLineParser.RequestLineFormatException;

public class HttpRequestParser {
    private String requestLine;
    private String body;
    private RequestLineParser requestLineParser;

    public void parse(String httpRequest) throws RequestLineFormatException {
        this.requestLineParser = new RequestLineParser();
        String[] requests = httpRequest.split("\\n");

        this.requestLine = requests[0];
        requestLineParser.parse(requestLine);
        this.body = parseBody(requests);
    }

    public String getHttpVerb() {
        return requestLineParser.getHttpVerb();
    }

    public String getUri() {
        return requestLineParser.getUri();
    }

    public String versionNumber() {
        return requestLineParser.versionNumber();
    }

    public String getBody() {
        return body;
    }

    private String parseBody(String[] requests) {
        Boolean afterBlankLine = false;
        StringBuilder body = new StringBuilder();

        for (String line : requests) {

            if (afterBlankLine) {
                body.append(line);
            }

            if (line.equals("")) {
                afterBlankLine = true;
            }
        }

        return body.toString();
    }
}
