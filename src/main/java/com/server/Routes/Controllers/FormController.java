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

        if (submitRequest(clientHttpRequest)) {
            memory.setContent(clientHttpRequest.getBody());
        } else if (getRequest(clientHttpRequest) && !memory.content().isEmpty()) {
            httpResponse.content(memory.content());
        } else if (deleteRequest(clientHttpRequest)) {
            memory.remove();
        }

        return httpResponse;
    }

    private boolean deleteRequest(ClientHttpRequest clientHttpRequest) {
        return clientHttpRequest.getVerb().equals(HttpVerb.DELETE.name());
    }

    private boolean getRequest(ClientHttpRequest clientHttpRequest) {
        return clientHttpRequest.getVerb().equals(HttpVerb.GET.name());
    }

    private boolean submitRequest(ClientHttpRequest clientHttpRequest) {
        return clientHttpRequest.getVerb().equals(HttpVerb.POST.name())
            || clientHttpRequest.getVerb().equals(HttpVerb.PUT.name());
    }
}
