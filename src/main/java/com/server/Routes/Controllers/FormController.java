package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;

import java.util.LinkedList;

public class FormController implements BaseController {
    private LinkedList<String> memory;

    public FormController(LinkedList<String> memory) {
        this.memory = memory;
    }

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);

        if (submitData(clientHttpRequest)) {
            saveData(clientHttpRequest);
        } else if (requestedData(clientHttpRequest)) {
            httpResponse.content(getData());
        } else if (deleteRequest(clientHttpRequest)) {
            deleteData();
        }

        return httpResponse;
    }

    private String deleteData() {
        return memory.remove(0);
    }

    private String getData() {
        return memory.get(0);
    }

    private void saveData(ClientHttpRequest clientHttpRequest) {
        memory.add(0, clientHttpRequest.getBody());
    }

    private boolean deleteRequest(ClientHttpRequest clientHttpRequest) {
        return clientHttpRequest.getVerb().equals(HttpVerb.DELETE.name());
    }

    private boolean requestedData(ClientHttpRequest clientHttpRequest) {
        return clientHttpRequest.getVerb().equals(HttpVerb.GET.name()) && memory.size() > 0;
    }

    private boolean submitData(ClientHttpRequest clientHttpRequest) {
        return clientHttpRequest.getVerb().equals(HttpVerb.POST.name()) || clientHttpRequest.getVerb().equals(HttpVerb.PUT.name());
    }
}
