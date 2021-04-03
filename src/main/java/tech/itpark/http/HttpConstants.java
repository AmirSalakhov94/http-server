package tech.itpark.http;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpConstants {

    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String TEXT_PLAIN = "text/plain;charset=UTF-8";
    public static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    public static final byte[] HTTP_START_LINE_AND_HEADER_SEPARATOR = new byte[]{'\r', '\n', '\r', '\n'};
    public static final byte[] HTTP_LINE_SEPARATOR = new byte[]{'\r', '\n'};
    public static final int BUFFER_SIZE = 4096;
}
