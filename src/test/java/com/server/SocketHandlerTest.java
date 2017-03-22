package com.server;

import com.server.Routes.Memory;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SocketHandlerTest {
    private String directoryPath = "";
    private Memory memory;

    @Before
    public void initialize() {
        this.memory = new Memory();
    }

    @Test
    public void throwAnExceptionWhen() throws Exception {
        BufferedReader inputError = null;
        OutputStream output = getStreamTest();
        SocketHandler socketHandler = new SocketHandler(inputError, output, memory, directoryPath);
        socketHandler.start();

        assertThat(output.toString()).contains("500 Internal Server Error");
    }

    @Test
    public void catchBadRequestExceptionWhenWrongLineRequest() throws Exception {
        BufferedReader socketInput = new BufferedReader(new StringReader("GET HTTP/1.1"));

        OutputStream output = getStreamTest();

        SocketHandler socketHandler = new SocketHandler(socketInput, output, memory, directoryPath);
        socketHandler.start();

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
