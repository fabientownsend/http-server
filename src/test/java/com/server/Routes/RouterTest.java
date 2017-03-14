package com.server.Routes;

import com.server.Cookie.Cookie;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class RouterTest {
    private LinkedList <String> memory = new LinkedList<>();
    private Router requestController = new Router();
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");
    private String directoryPath = "/Users/fabientownsend/Documents/Java/cob_spec/public/";

    @Test
    public void returnsDefaultPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/");

        assertThat(requestController.route(clientHttpRequest, memory, directoryPath)).isInstanceOf(DefaultPage.class);
    }

    @Test
    public void returnsFormPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/form");

        assertThat(requestController.route(clientHttpRequest, memory, directoryPath)).isInstanceOf(FormPage.class);
    }

    @Test
    public void returnsRedirectPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/redirect");

        assertThat(requestController.route(clientHttpRequest, memory, directoryPath)).isInstanceOf(RedirectPage.class);
    }

    @Test
    public void returnsMethodOptionsPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options");

        assertThat(requestController.route(clientHttpRequest, memory, directoryPath)).isInstanceOf(MethodOptions.class);
    }

    @Test
    public void returnsMethodOptionsTwoPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options2");

        assertThat(requestController.route(clientHttpRequest, memory, directoryPath)).isInstanceOf(MethodOptions2.class);
    }

    @Test
    public void returnsNotFoundPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/it_doesnt_exist");

        assertThat(requestController.route(clientHttpRequest, memory, directoryPath)).isInstanceOf(NotFoundPage.class);
    }

    @Test
    public void returnsParametersPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff");

        assertThat(requestController.route(clientHttpRequest, memory, directoryPath)).isInstanceOf(ParametersPage.class);
    }

    @Test
    public void returnsLogsPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/logs");

        assertThat(requestController.route(clientHttpRequest, memory, directoryPath)).isInstanceOf(Logs.class);
    }

    @Test
    public void returnsCookiePage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/cookie?type=chocolate");

        assertThat(requestController.route(clientHttpRequest, memory, directoryPath)).isInstanceOf(Cookie.class);
    }

    @Test
    public void returnsEatCookiePage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/eat_cookie");

        assertThat(requestController.route(clientHttpRequest, memory, directoryPath)).isInstanceOf(EatCookie.class);
    }
}
