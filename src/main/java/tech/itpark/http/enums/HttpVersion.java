package tech.itpark.http.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HttpVersion {
    HTTP_1_1("HTTP/1.1");

    private final String description;
}
