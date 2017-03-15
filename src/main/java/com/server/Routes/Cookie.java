package com.server.Routes;

import java.util.LinkedList;

public class Cookie {
    private LinkedList<String> cookie;

    public Cookie() {
        this.cookie = new LinkedList<>();
    }

    public String content() {
        return cookie.get(0);
    }

    public void setContent(String value) {
        this.cookie.add(0, value);
    }
}
