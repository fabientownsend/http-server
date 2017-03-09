package com.server.HttpRequest;

import com.server.HttpVerb;

import java.util.Hashtable;
import java.util.LinkedList;

public class HttpRequestParser {
    private String[] httpRequest;

    public ClientHttpRequest parse(String httpRequest) throws Exception {
        this.httpRequest = httpRequest.split("\\n");
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(parseVerb());
        clientHttpRequest.setUri(parseUri());
        clientHttpRequest.setHttpVersion(parseHttpVersion());
        clientHttpRequest.setSectionInformation(parseSectionInformation());
        clientHttpRequest.setBody(parseBody());

        return clientHttpRequest;
    }

    private HttpVerb parseVerb() throws Exception {
        String requestLine = getRequestLine();
        String[] infoLine = requestLine.split("\\s+");
        String strVerb = infoLine[0];

        for (HttpVerb verb: HttpVerb.values()) {
            if (verb.name().equals(strVerb)) {
                return verb;
            }
        }

        return HttpVerb.GET;
    }

    private String parseUri() {
        String requestLine = getRequestLine();
        String[] infoLine = requestLine.split("\\s+");
        return infoLine[1];
    }

    private String parseHttpVersion() {
        String requestLine = getRequestLine();
        String[] infoLine = requestLine.split("\\s+");
        return infoLine[2];
    }

    public String getRequestLine() {
        return httpRequest[0];
    }

    private Hashtable<String, String> parseSectionInformation() {
        Hashtable<String, String> sectionInformation = new Hashtable<>();

        for (String information : getHeaderSection()) {
            String[] splitInformation = information.split(":", 2);
            sectionInformation.put(splitInformation[0], splitInformation[1].trim());
        }

        return sectionInformation;
    }

    private LinkedList<String> getHeaderSection() {
        LinkedList<String> headerSection = new LinkedList<>();

        for (int i = 1; i < httpRequest.length; i++) {
            if (httpRequestHeadFinished(httpRequest[i])) {
                return headerSection;
            } else {
                headerSection.add(httpRequest[i]);
            }
        }

        return headerSection;
    }

    private boolean httpRequestHeadFinished(String line) {
        return line.equals("");
    }

    private String parseBody() {
        boolean bodyStarted = false;
        String body = "";

        for (String line : httpRequest) {
            if (bodyStarted) {
                body += line;
            }

            if (httpRequestHeadFinished(line)) {
                bodyStarted = true;
            }
        }

        return body;
    }
}
