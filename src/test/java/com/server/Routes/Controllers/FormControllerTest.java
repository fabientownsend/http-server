package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;
import com.server.Routes.Memory;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FormControllerTest {
    private HttpResponse httpResponse = new HttpResponse("HTTP/1.1");
    private Memory memory;

    @Before
    public void initialize() {
        this.memory = new Memory();
    }

    @Test
    public void returnsNoDataByDefault() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");
        FormController formController = new FormController(memory);
        String httpResponse = new String(formController.execute(clientHttpRequest).response());
        assertThat(httpResponse).isEqualTo("HTTP/1.1 200 OK");
    }

    @Test
    public void returnDataWhenDataIntoMemory() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET.name());
        memory.setContent("hello");
        FormController formController = new FormController(memory);

        httpResponse = formController.execute(clientHttpRequest);
        assertThat(httpResponse.response()).contains("hello".getBytes());
    }

    @Test
    public void returnTheSizeOfTheData() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET.name());
        memory.setContent("hello");
        FormController formController = new FormController(memory);

        httpResponse = formController.execute(clientHttpRequest);
        assertThat(httpResponse.response()).contains("Content-Length: 5".getBytes());
    }
    @Test
    public void saveDataIntoMemoryWhenPut() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.PUT.name());
        clientHttpRequest.setBody("hello");
        FormController formController = new FormController(memory);
        formController.execute(clientHttpRequest);

        assertThat(memory.remove()).contains("hello");
    }

    @Test
    public void saveDataIntoMemoryWhenPost() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.POST.name());
        clientHttpRequest.setBody("hello");
        FormController formController = new FormController(memory);
        formController.execute(clientHttpRequest);

        assertThat(memory.remove()).contains("hello");
    }
}


