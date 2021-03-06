package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.Routes.Controllers.*;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteProviderTest {
    private Memory memory = new Memory();
    private Path currentPath = Paths.get("/src/test/java/com/server/public");
    private String directoryPath =  currentPath.toAbsolutePath().toString();
    private RouteProvider requestController = new RouteProvider(memory, directoryPath);

    @Test
    public void returnsDefaultPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/");

        assertThat(requestController.getRequiredRoute(clientHttpRequest.getUri())).isInstanceOf(DefaultController.class);
    }

    @Test
    public void returnsFormPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/form");

        assertThat(requestController.getRequiredRoute(clientHttpRequest.getUri())).isInstanceOf(FormController.class);
    }

    @Test
    public void returnsRedirectPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/redirect");

        assertThat(requestController.getRequiredRoute(clientHttpRequest.getUri())).isInstanceOf(RedirectionController.class);
    }

    @Test
    public void returnsMethodOptionsPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options");

        assertThat(requestController.getRequiredRoute(clientHttpRequest.getUri())).isInstanceOf(MethodOptionsController.class);
    }

    @Test
    public void returnsMethodOptionsTwoPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options2");

        assertThat(requestController.getRequiredRoute(clientHttpRequest.getUri())).isInstanceOf(MethodOptions2Controller.class);
    }

    @Test
    public void returnsNotFoundPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/it_doesnt_exist");

        assertThat(requestController.getRequiredRoute(clientHttpRequest.getUri())).isInstanceOf(NotFoundController.class);
    }

    @Test
    public void returnsParametersPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff");

        assertThat(requestController.getRequiredRoute(clientHttpRequest.getUri())).isInstanceOf(ParameterDecodeController.class);
    }

    @Test
    public void returnsLogsPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/logs");

        assertThat(requestController.getRequiredRoute(clientHttpRequest.getUri())).isInstanceOf(LogsController.class);
    }

    @Test
    public void returnsCookiePage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/cookie?type=chocolate");

        assertThat(requestController.getRequiredRoute(clientHttpRequest.getUri())).isInstanceOf(CookieController.class);
    }

    @Test
    public void returnsEatCookiePage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/eat_cookie");

        assertThat(requestController.getRequiredRoute(clientHttpRequest.getUri())).isInstanceOf(EatCookieController.class);
    }

    @Test
    public void returnsTeaController() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/tea");

        assertThat(requestController.getRequiredRoute(clientHttpRequest.getUri())).isInstanceOf(TeaController.class);
    }
}
