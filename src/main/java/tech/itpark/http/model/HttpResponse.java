package tech.itpark.http.model;

import tech.itpark.http.enums.HttpVersion;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse implements Response {

    private Map<String, Object> headers = new HashMap<>();
    private HttpVersion version;
    private int code;
    private String text;
    private byte[] body;

    @Override
    public HttpVersion getVersion() {
        return version;
    }

    @Override
    public void setVersion(HttpVersion version) {
        this.version = version;
    }

    @Override
    public int getStatusCode() {
        return code;
    }

    @Override
    public void setStatusCode(int code) {
        this.code = code;
    }

    @Override
    public String getStatusText() {
        return text;
    }

    @Override
    public void setStatusText(String text) {
        this.text = text;
    }

    @Override
    public void addHeader(String name, Object value) {
        this.headers.put(name, value);
    }

    @Override
    public Map<String, Object> getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    @Override
    public byte[] getBody() {
        return body;
    }

    @Override
    public void setBody(byte[] body) {
        this.body = body;
    }
}
