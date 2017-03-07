package com.server;

import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class FormPageTest {
    @Test
    public void returnsNoDataByDefault() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET);
        FormPage formPage = new FormPage(clientHttpRequest, memory);
        assertThat(formPage.generateContent()).isEqualTo("HTTP/1.1 200 OK");
    }

    @Test
    public void returnDataWhenDataIntoMemory() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET);
        memory.add("hello");
        FormPage formPage = new FormPage(clientHttpRequest, memory);

        assertThat(formPage.generateContent()).contains("hello");
    }

    @Test
    public void returnTheSizeOfTheData() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.GET);
        memory.add("hello");
        FormPage formPage = new FormPage(clientHttpRequest, memory);

        assertThat(formPage.generateContent()).contains("Content-Length: 5");
    }

    @Test
    public void saveDataIntoMemoryWhenPut() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.PUT);
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(clientHttpRequest, memory);
        formPage.generateContent();

        assertThat(memory.remove()).contains("hello");
    }

    @Test
    public void saveDataIntoMemoryWhenPost() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.POST);
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(clientHttpRequest, memory);
        formPage.generateContent();

        assertThat(memory.remove()).contains("hello");
    }

    @Test
    public void removesDataWhenDeleteVerb() {
        LinkedList<String> memory = new LinkedList<>();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

        clientHttpRequest.setVerb(HttpVerb.POST);
        clientHttpRequest.setBody("hello");
        FormPage formPage = new FormPage(clientHttpRequest, memory);
        formPage.generateContent();

        clientHttpRequest.setVerb(HttpVerb.DELETE);
        formPage = new FormPage(clientHttpRequest, memory);
        formPage.generateContent();

        assertThat(memory.size()).isEqualTo(0);
    }
}
