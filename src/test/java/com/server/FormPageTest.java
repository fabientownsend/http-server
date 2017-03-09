package com.server;

import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class FormPageTest {
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");

    @Test
    public void returnsNoDataByDefault() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET);
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);
        assertThat(formPage.generateContent().build()).isEqualTo("HTTP/1.1 200 OK".getBytes());
    }

    @Test
    public void returnDataWhenDataIntoMemory() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET);
        memory.add("hello");
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);

        httpServerResponse = formPage.generateContent();
        assertThat(httpServerResponse.build()).contains("hello".getBytes());
    }

    @Ignore
    @Test
    public void returnTheSizeOfTheData() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET);
        memory.add("hello");
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);

        httpServerResponse = formPage.generateContent();
        assertThat(httpServerResponse.build()).contains("Content-Length: 5".getBytes());
    }

    @Test
    public void saveDataIntoMemoryWhenPut() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.PUT);
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);
        formPage.generateContent();

        assertThat(memory.remove()).contains("hello");
    }

    @Test
    public void saveDataIntoMemoryWhenPost() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.POST);
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);
        formPage.generateContent();

        assertThat(memory.remove()).contains("hello");
    }

    @Test
    public void removesDataWhenDeleteVerb() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.POST);
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);
        formPage.generateContent();

        clientHttpRequest.setVerb(HttpVerb.DELETE);
        formPage = new FormPage(httpServerResponse, clientHttpRequest, memory);
        formPage.generateContent();

        assertThat(memory.size()).isEqualTo(0);
    }
}


