package com.server.Routes;

import java.util.LinkedList;

public class Memory {
    private LinkedList<String> cookie;

    public Memory() {
        this.cookie = new LinkedList<>();
    }

    public String content() {
        if (cookie.size() == 0) {
            return "";
        } else {
            return cookie.get(0);
        }
    }

    public void setContent(String value) {
        cookie.add(0, value);
    }

    public String remove() {
        return cookie.remove(0);
    }
}
