package com.server.Routes;

import com.server.Cookie.Cookie;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;
import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class cookieTest {
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");

    @Test
    public void returns200Response() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/cookie?type=chocolate");

        clientHttpRequest.setVerb(HttpVerb.GET.name());
        Cookie cookie = new Cookie(httpServerResponse, clientHttpRequest, memory);
        String response = new String(cookie.execute().build());
        assertThat(response).isEqualTo(
            "HTTP/1.1 200 OK\r\n"
            + "Content-Length: 3\r\n"
            + "\r\nEat");
    }

    @Test
    public void saveTheValueInMemory() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/cookie?type=chocolate");

        clientHttpRequest.setVerb(HttpVerb.GET.name());
        assertThat(memory.remove()).isEqualTo("chocolate");
    }
}
