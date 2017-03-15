package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;
import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class FormPageTest {
    private HttpResponse httpResponse = new HttpResponse("HTTP/1.1");

    @Test
    public void returnsNoDataByDefault() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");
        FormPage formPage = new FormPage(memory);
        assertThat(formPage.execute(clientHttpRequest).response()).isEqualTo("HTTP/1.1 200 OK".getBytes());
    }

    @Test
    public void returnDataWhenDataIntoMemory() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET.name());
        memory.add("hello");
        FormPage formPage = new FormPage(memory);

        httpResponse = formPage.execute(clientHttpRequest);
        assertThat(httpResponse.response()).contains("hello".getBytes());
    }

    @Test
    public void returnTheSizeOfTheData() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET.name());
        memory.add("hello");
        FormPage formPage = new FormPage(memory);

        httpResponse = formPage.execute(clientHttpRequest);
        assertThat(httpResponse.response()).contains("Content-Length: 5".getBytes());
    }
    @Test
    public void saveDataIntoMemoryWhenPut() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.PUT.name());
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(memory);
        formPage.execute(clientHttpRequest);

        assertThat(memory.remove()).contains("hello");
    }

    @Test
    public void saveDataIntoMemoryWhenPost() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.POST.name());
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(memory);
        formPage.execute(clientHttpRequest);

        assertThat(memory.remove()).contains("hello");
    }

    @Test
    public void removesDataWhenDeleteVerb() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.POST.name());
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(memory);
        formPage.execute(clientHttpRequest);

        clientHttpRequest.setVerb(HttpVerb.DELETE.name());
        formPage = new FormPage(memory);
        formPage.execute(clientHttpRequest);

        assertThat(memory.size()).isEqualTo(0);
    }
}


