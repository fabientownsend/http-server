package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;

import java.util.LinkedList;

public class FormPage implements BaseController {
    private LinkedList<String> memory;

    public FormPage(LinkedList<String> memory) {
        this.memory = memory;
    }

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
           HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);

        if (clientHttpRequest.getVerb().equals(HttpVerb.POST.name())
            || clientHttpRequest.getVerb().equals(HttpVerb.PUT.name())) {
            memory.add(0, clientHttpRequest.getBody());
        } else if (clientHttpRequest.getVerb().equals(HttpVerb.GET.name()) && memory.size() > 0) {
            httpResponse.content(memory.get(0));
        } else if (clientHttpRequest.getVerb().equals(HttpVerb.DELETE.name())) {
            memory.remove(0);
        }

        return httpResponse;
    }
}
