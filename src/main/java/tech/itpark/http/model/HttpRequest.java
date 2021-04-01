package tech.itpark.http.model;

import lombok.Builder;
import tech.itpark.http.enums.HttpMethod;
import tech.itpark.http.enums.HttpVersion;

import java.util.Map;

@Builder
public class HttpRequest implements Request {

    private HttpMethod httpMethod;
    private String uri;
    private String path;
    private Map<String, Object> params;
    private HttpVersion httpVersion;
    private Map<String, Object> headers;
    private byte[] body;

    @Override
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Map<String, Object> getUrlParams() {
        return params;
    }

    @Override
    public HttpVersion getHttpVersion() {
        return httpVersion;
    }

    @Override
    public Map<String, Object> getHeaders() {
        return headers;
    }

    @Override
    public byte[] getBody() {
        return body;
    }
}
