package tech.itpark.http.model;

import tech.itpark.http.enums.HttpVersion;

import java.util.Map;

public interface Response {

    HttpVersion getVersion();

    void setVersion(HttpVersion version);

    int getStatusCode();

    void setStatusCode(int code);

    String getStatusText();

    void setStatusText(String text);

    void addHeader(String name, Object value);

    Map<String, Object> getHeaders();

    void setHeaders(Map<String, Object> headers);

    byte[] getBody();

    void setBody(byte[] body);
}
