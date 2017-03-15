package com.server.HttpRequest;

import com.server.HttpHeaders.HttpHeaders;

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

    public int[] getRange() {
        int[] range = new int[2];
        range[0] = -100;
        range[1] = -100;
        if (sectionInformation.containsKey(HttpHeaders.RANGE)) {
            String infoRange = sectionInformation.get(HttpHeaders.RANGE);
            String infos = infoRange.split("=")[1];
            String[] infos2 = infos.split("-", 2);
            if (!infos2[0].isEmpty()) {
                range[0] = Integer.parseInt(infos2[0]);
            }
            if (!infos2[1].isEmpty()) {
                range[1] = Integer.parseInt(infos2[1]);
            }

            return range;
        }

        return range;
    }
}
