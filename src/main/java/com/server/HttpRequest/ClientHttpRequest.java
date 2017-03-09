package com.server.HttpRequest;

import com.server.HttpVerb;

import java.util.Hashtable;

public class ClientHttpRequest {
    private HttpVerb verb;
    private String uri;
    private String httpVersion;
    private Hashtable<String, String> sectionInformation;
    private String body;

    public HttpVerb getVerb() {
        return verb;
    }

    public void setVerb(HttpVerb verb) {
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
        return sectionInformation.get(information);
    }

    public void setSectionInformation(Hashtable<String,String> sectionInformation) {
        this.sectionInformation = sectionInformation;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
