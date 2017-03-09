package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class FormPageTest {
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");

    @Test
    public void returnsNoDataByDefault() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET.name());
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);
        assertThat(formPage.execute().build()).isEqualTo("HTTP/1.1 200 OK".getBytes());
    }

    @Test
    public void returnDataWhenDataIntoMemory() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET.name());
        memory.add("hello");
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);

        httpServerResponse = formPage.execute();
        assertThat(httpServerResponse.build()).contains("hello".getBytes());
    }

    @Ignore
    @Test
    public void returnTheSizeOfTheData() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET.name());
        memory.add("hello");
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);

        httpServerResponse = formPage.execute();
        assertThat(httpServerResponse.build()).contains("Content-Length: 5".getBytes());
    }

    @Test
    public void saveDataIntoMemoryWhenPut() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.PUT.name());
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);
        formPage.execute();

        assertThat(memory.remove()).contains("hello");
    }

    @Test
    public void saveDataIntoMemoryWhenPost() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.POST.name());
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);
        formPage.execute();

        assertThat(memory.remove()).contains("hello");
    }

    @Test
    public void removesDataWhenDeleteVerb() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.POST.name());
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);
        formPage.execute();

        clientHttpRequest.setVerb(HttpVerb.DELETE.name());
        formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);
        formPage.execute();

        assertThat(memory.size()).isEqualTo(0);
    }
}


