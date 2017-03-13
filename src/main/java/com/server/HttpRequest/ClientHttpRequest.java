package com.server.HttpRequest;

import java.util.Hashtable;
import java.util.Map;

public class ClientHttpRequest {
    private String verb;
    private String uri;
    private String httpVersion;
    private Map<String, String> sectionInformation;
    private String body;

    public ClientHttpRequest() {
        sectionInformation = new Hashtable<>();
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getInformation(String information) {
        if (sectionInformation.containsKey(information)) {
            return sectionInformation.get(information);
        } else {
            return "";
        }
    }

    public void setSectionInformation(Map<String,String> sectionInformation) {
        this.sectionInformation = sectionInformation;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
