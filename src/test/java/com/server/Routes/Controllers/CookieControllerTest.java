package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import com.server.Routes.Memory;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CookieControllerTest {
    private ClientHttpRequest clientHttpRequest;
    private Memory cookie;
    private CookieController cookieController;

    @Before
    public void initialize() {
        this.cookie = new Memory();
        this.cookieController = new CookieController(cookie);
        this.clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET);
        clientHttpRequest.setHttpVersion("HTTP/1.1");
    }

    @Test
    public void returns200WithSetCookieEqualChocolate() {
        clientHttpRequest.setUri("/cookie?type=chocolate");

        String httpResponse = new String(cookieController.doGet(clientHttpRequest).response());

        assertThat(httpResponse).isEqualTo(
            "HTTP/1.1 200 OK\r\n"
            + "Set-Cookie: type:chocolate\r\n"
            + "Content-Length: 3\r\n"
            + "\r\n"
            + "Eat");
    }

    @Test
    public void returns200WithSetCookieEqualApple() {
        clientHttpRequest.setUri("/cookie?type=apple");

        String httpResponse = new String(cookieController.doGet(clientHttpRequest).response());

        assertThat(httpResponse).isEqualTo(
            "HTTP/1.1 200 OK\r\n"
            + "Set-Cookie: type:apple\r\n"
            + "Content-Length: 3\r\n"
            + "\r\n"
            + "Eat");
    }

    @Test
    public void cookieValueIsSavedInMemory() {
        clientHttpRequest.setUri("/cookie?type=apple");

        cookieController.doGet(clientHttpRequest);

        assertThat(cookie.content()).isEqualTo("type:apple");
    }
}
