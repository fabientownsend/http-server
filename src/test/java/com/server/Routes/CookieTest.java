package com.server.Routes;

import com.server.Cookie.Cookie;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class CookieTest {
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");

    @Ignore
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
            + "Set-Cookie: type:chocolate\r\n"
            + "Content-Length: 18\r\n\r\n"
            + "Eat\r\n"
            + "mmmm chocolate");
    }
}
