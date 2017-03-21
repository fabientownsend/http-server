package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;

import static com.server.HttpVerb.*;

public interface BaseController {
    default HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpVerb verb = clientHttpRequest.getVerb();

        if (verb == GET) {
            return doGet(clientHttpRequest);
        } else if (verb == POST) {
            return doPost(clientHttpRequest);
        } else if (verb == PUT) {
            return doPut(clientHttpRequest);
        } else if (verb == DELETE) {
            return doDelete(clientHttpRequest);
        } else if (verb == OPTIONS) {
            return doOptions(clientHttpRequest);
        } else if (verb == PATCH) {
            return doPatch(clientHttpRequest);
        } else if (verb == HEAD) {
            return doHead(clientHttpRequest);
        } else {
            return methodNotAllowed();
        }
    }

    default HttpResponse doGet(ClientHttpRequest clientHttpRequest) {
        return methodNotAllowed();
    }

    default HttpResponse doPost(ClientHttpRequest clientHttpRequest) {
        return methodNotAllowed();
    }

    default HttpResponse doPut(ClientHttpRequest clientHttpRequest) {
        return methodNotAllowed();
    }

    default HttpResponse doDelete(ClientHttpRequest clientHttpRequest) {
        return methodNotAllowed();
    }

    default HttpResponse  doOptions(ClientHttpRequest clientHttpRequest) {
        return methodNotAllowed();
    }

    default HttpResponse doPatch(ClientHttpRequest clientHttpRequest) {
        return methodNotAllowed();
    }

    default HttpResponse doHead(ClientHttpRequest clientHttpRequest) {
        return methodNotAllowed();
    }

    default HttpResponse methodNotAllowed() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.METHOD_NOT_ALLOWED);
        return httpResponse;
    }
}
