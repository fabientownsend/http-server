package com.server.http_request;

import com.server.http_request.RequestLineParser.RequestLineFormatException;

import java.util.Hashtable;

public class HttpRequestParser {
    private String requestLine;
    private String body;
    private RequestLineParser requestLineParser;

    public void parse(String httpRequest) throws RequestLineFormatException {
        this.requestLineParser = new RequestLineParser();
        String[] requests = httpRequest.split("\\n");

        this.requestLine = requests[0];
        requestLineParser.parse(requestLine);
        this.body = requestMessageBody(requests);
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

    private String requestMessageBody(String[] requests) {
        Boolean afterBlankLine = false;
        StringBuilder body = new StringBuilder();

        for (String line : requests) {

            if (afterBlankLine) {
                body.append(line);
            }

            if (isEndRequestMessageHeader(line)) {
                afterBlankLine = true;
            }
        }

        return body.toString();
    }

    public Hashtable<String, String> getRequestHeader(String httpRequest) {
        String body = getRequestMessageHeader(httpRequest);
        String[] array_body = body.split("\\n");
        Hashtable<String, String> optionalRequestHeader = new Hashtable<>();

        for (int i = 1; i < array_body.length; i++) {
            String[] keyValueInformation = array_body[i].split(":", 2);
            optionalRequestHeader.put(keyValueInformation[0], keyValueInformation[1].trim());
        }

        return optionalRequestHeader;
    }

    private String getRequestMessageHeader(String httpRequest) {
        StringBuilder header = new StringBuilder();
        String[] httpRequestLines = httpRequest.split("\\n");

        for (String line : httpRequestLines) {
            if (isEndRequestMessageHeader(line)) {
                break;
            }
            header.append(line + "\n");
        }

        return header.toString();
    }

    private boolean isEndRequestMessageHeader(String line) {
        return line.equals("") || line.equals("\n");
    }
}
