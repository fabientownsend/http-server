package com.server;

import java.util.LinkedList;

public class FormPage implements UpstreamService {
    private final ClientHttpRequest clientHttpRequest;
    private LinkedList<String> memory;

    public FormPage(ClientHttpRequest clientHttpRequest, LinkedList<String> memory) {
        this.clientHttpRequest = clientHttpRequest;
        this.memory = memory;
    }

    public String generateContent() {
        String response =  "HTTP/1.1 200 OK";

        if (clientHttpRequest.getVerb() == HttpVerb.POST || clientHttpRequest.getVerb() == HttpVerb.PUT) {
            memory.add(0, clientHttpRequest.getBody());
        } else if (clientHttpRequest.getVerb() == HttpVerb.GET && memory.size() > 0) {
            response += "\nContent-Length: " + memory.get(0).length() + "\n";
            response += "\n" + memory.get(0);
        } else if (clientHttpRequest.getVerb() == HttpVerb.DELETE) {
            memory.remove(0);
        }

        return response;
    }
}
