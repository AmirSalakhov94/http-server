package tech.itpark.http.model;

import tech.itpark.http.enums.HttpMethod;
import tech.itpark.http.enums.HttpVersion;

import java.util.Map;

public interface Request {

    HttpMethod getHttpMethod();

    String getUri();

    String getPath();

    Map<String, Object> getUrlParams();

    HttpVersion getHttpVersion();

    Map<String, Object> getHeaders();

    byte[] getBody();
}
