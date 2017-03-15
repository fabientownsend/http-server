package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;
import com.server.Routes.Memory;

public class FormController implements BaseController {
    private Memory memory;

    public FormController(Memory memory) {
        this.memory = memory;
    }

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);

        if (submitData(clientHttpRequest)) {
            saveData(clientHttpRequest);
        } else if (requestedData(clientHttpRequest)) {
            String data = getData();
            if (!data.isEmpty()) {
                httpResponse.content(getData());
            }
        } else if (deleteRequest(clientHttpRequest)) {
            deleteData();
        }

        return httpResponse;
    }

    private String deleteData() {
        return memory.remove();
    }

    private String getData() {
        return memory.content();
    }

    private void saveData(ClientHttpRequest clientHttpRequest) {
        memory.setContent(clientHttpRequest.getBody());
    }

    private boolean deleteRequest(ClientHttpRequest clientHttpRequest) {
        return clientHttpRequest.getVerb().equals(HttpVerb.DELETE.name());
    }

    private boolean requestedData(ClientHttpRequest clientHttpRequest) {
        return clientHttpRequest.getVerb().equals(HttpVerb.GET.name());
    }

    private boolean submitData(ClientHttpRequest clientHttpRequest) {
        return clientHttpRequest.getVerb().equals(HttpVerb.POST.name()) || clientHttpRequest.getVerb().equals(HttpVerb.PUT.name());
    }
}
