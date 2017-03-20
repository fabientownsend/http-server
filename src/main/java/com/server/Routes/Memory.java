package com.server.Routes;

public class Memory {
    private String memory = "";

    public String content() {
        return memory;
    }

    public void setContent(String value) {
        this.memory = value;
    }

    public void remove() {
        memory = "";
    }
}
