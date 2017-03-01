package com.server;

import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ServerTest {
    @Test
    public void returnsErrorMessageWhenBadRequest() {
        BufferedReader input = new BufferedReader(new StringReader("BAD request"));
        StringWriter out = new StringWriter();
        PrintWriter output = new PrintWriter(out, true);
        Server server = new Server(input, output);
        server.run();

        assertThat(out.toString()).isEqualTo("error request\n");
    }

    @Test
    public void returnsTheRequestWhenCorrect() {
        BufferedReader input = new BufferedReader(new StringReader("GET / HTTP/1.1"));
        StringWriter out = new StringWriter();
        PrintWriter output = new PrintWriter(out, true);
        Server server = new Server(input, output);
        server.run();

        assertThat(out.toString()).isEqualTo("HTTP/1.1 200 OK\n");
    }

    @Test
    public void redirectPathWhenUrlCallIsRedirection() {
        BufferedReader input = new BufferedReader(new StringReader("GET /redirect HTTP/1.1"));
        StringWriter out = new StringWriter();
        PrintWriter output = new PrintWriter(out, true);
        Server server = new Server(input, output);
        server.run();

        assertThat(out.toString()).isEqualTo(
            "HTTP/1.1 302 Object moved\n" +
            "Location: http://localhost:5000/\n"
        );
    }

    @Test
    public void tryOption() {
        BufferedReader input = new BufferedReader(new StringReader("OPTIONS /method_options HTTP/1.1"));
        StringWriter out = new StringWriter();
        PrintWriter output = new PrintWriter(out, true);
        Server server = new Server(input, output);
        server.run();

        assertThat(out.toString()).isEqualTo(
            "HTTP/1.1 200 OK\n" +
            "Allow: GET,HEAD,POST,OPTIONS,PUT\n"
        );
    }

    @Test
    public void methodOptionsWithGet() {
        BufferedReader input = new BufferedReader(new StringReader("GET /method_options HTTP/1.1"));
        StringWriter out = new StringWriter();
        PrintWriter output = new PrintWriter(out, true);
        Server server = new Server(input, output);
        server.run();

        assertThat(out.toString()).isEqualTo(
                "HTTP/1.1 200 OK\n" +
                "Allow: GET,OPTIONS\n"
        );
    }

    @Test
    public void methodOptionsWithPut() {
        BufferedReader input = new BufferedReader(new StringReader("PUT /method_options HTTP/1.1"));
        StringWriter out = new StringWriter();
        PrintWriter output = new PrintWriter(out, true);
        Server server = new Server(input, output);
        server.run();

        assertThat(out.toString()).isEqualTo(
                "HTTP/1.1 200 OK\n" +
                "Allow: GET,OPTIONS\n"
        );
    }

    @Test
    public void methodOptionsWithHead() {
        BufferedReader input = new BufferedReader(new StringReader("HEAD /method_options HTTP/1.1"));
        StringWriter out = new StringWriter();
        PrintWriter output = new PrintWriter(out, true);
        Server server = new Server(input, output);
        server.run();

        assertThat(out.toString()).isEqualTo(
                "HTTP/1.1 200 OK\n" +
                "Allow: GET,OPTIONS\n"
        );
    }

    @Test
    public void methodOptionsWithPost() {
        BufferedReader input = new BufferedReader(new StringReader("POST /method_options HTTP/1.1"));
        StringWriter out = new StringWriter();
        PrintWriter output = new PrintWriter(out, true);
        Server server = new Server(input, output);
        server.run();

        assertThat(out.toString()).isEqualTo(
                "HTTP/1.1 200 OK\n" +
                "Allow: GET,OPTIONS\n"
        );
    }
}
