package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class CookieTest {
    @Ignore
    @Test
    public void returns200Response() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/cookie?type=chocolate");

        clientHttpRequest.setVerb(HttpVerb.GET.name());
        Cookie cookie = new Cookie(memory);
        String response = new String(cookie.execute(clientHttpRequest).response());
        assertThat(response).isEqualTo(
            "HTTP/1.1 200 OK\r\n"
            + "Set-Cookie: type:chocolate\r\n"
            + "Content-Length: 18\r\n\r\n"
            + "Eat\r\n"
            + "mmmm chocolate");
    }
}
