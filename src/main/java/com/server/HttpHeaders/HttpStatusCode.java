package com.server.HttpHeaders;

public enum HttpStatusCode {
    OK(200, "OK"),
    CREATED(201, "Created"),
    NO_CONTENT(204, "No Content"),
    PARTIAL_CONTENT(206, "Partial Content"),
    OBJECT_MOVED(302, "Object Moved"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    I_M_A_TEAPOT(418, "I'm a teapot"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String phrase;

    HttpStatusCode(int code, String phrase) {
        this.code = code;
        this.phrase = phrase;
    }

    public Object getPhrase() {
        return this.phrase;
    }

    public int getCode() {
        return code;
    }
}
