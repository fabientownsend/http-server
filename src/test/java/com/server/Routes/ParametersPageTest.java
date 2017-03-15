package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParametersPageTest {
    @Test
    public void returnListParametersInTheBody() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setUri("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff");

        ParametersPage parametersPage = new ParametersPage();
        String parameters = new String(parametersPage.execute(clientHttpRequest).response());
        assertThat(parameters).contains("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?");
        assertThat(parameters).contains("variable_2 = stuff");
    }

    @Test
    public void returnsOneParameters() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setUri("/parameters?variable_2=stuff");

        ParametersPage parametersPage = new ParametersPage();
        String parameters = new String(parametersPage.execute(clientHttpRequest).response());
        assertThat(parameters).contains("variable_2 = stuff");
    }

    @Test
    public void returnsNothingWhenNoParameters() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");
        clientHttpRequest.setUri("/parameters");

        ParametersPage parametersPage = new ParametersPage();
        String parameters = new String(parametersPage.execute(clientHttpRequest).response());
        assertThat(parameters).isEqualTo("HTTP/1.1 200 OK");
    }

    @Test
    public void unsupportedEncoding() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setUri("/parameters?variable_1=Operators%20%3Cdsfadsi#!$#@!%%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff");

        ParametersPage parametersPage = new ParametersPage();
        String parameters = new String(parametersPage.execute(clientHttpRequest).response());
        assertThat(parameters).doesNotContain("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?");
        assertThat(parameters).contains("variable_2 = stuff");
    }
}
