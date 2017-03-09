package com.server;

import org.junit.Test;

import java.io.*;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class ServerTest {
    @Test
    public void catchBadRequestException() throws Exception {
        BufferedReader socketInput = new BufferedReader(new StringReader("GET HTTP/1.1"));

        OutputStream output = getStreamTest();

        Server server = new Server(socketInput, output, new LinkedList<>());
        server.start();

        assertThat(output.toString()).contains("400 Bad Request");
    }

    private OutputStream getStreamTest() {
        return new OutputStream() {
            public byte[] b;

            @Override
            public void write(int b) throws IOException { }

            @Override
            public String toString() {
                try {
                    return new String(b, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            public void write(byte[] b) throws IOException {
                this.b = b;
            }
        };
    }
}
