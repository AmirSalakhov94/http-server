package tech.itpark.http.util;

import lombok.experimental.UtilityClass;
import tech.itpark.http.enums.HttpMethod;
import tech.itpark.http.exception.client.BadHeaderException;
import tech.itpark.http.exception.client.MalFormedRequestException;
import tech.itpark.http.exception.client.WrongHttpStartLineException;
import tech.itpark.http.exception.client.WrongUrlRequestException;
import tech.itpark.http.exception.server.HttpMethodNotSupportedException;
import tech.itpark.http.exception.server.HttpVersionNotSupportedException;
import tech.itpark.http.model.HttpRequest;
import tech.itpark.http.model.Request;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static tech.itpark.http.HttpConstants.*;
import static tech.itpark.http.enums.HttpVersion.HTTP_1_1;

@UtilityClass
public class RequestUtil {

    public Request fill(final BufferedInputStream in) throws IOException {
        final var buffer = new byte[BUFFER_SIZE]; // request line - headers < 4Кб
        in.mark(BUFFER_SIZE);

        final var read = in.read(buffer);
        final var requestLineEndIndex = BytesUtil.indexOf(buffer, HTTP_LINE_SEPARATOR, 0);
        if (requestLineEndIndex == -1) {
            throw new MalFormedRequestException();
        }

        String[] startLineParts = getStartLineParts(buffer, requestLineEndIndex);
        final var method = startLineParts[0];
        if (!HttpMethod.contains(method)) {
            throw new HttpMethodNotSupportedException("Not supported http method: " + method);
        }

        final var uri = startLineParts[1];
        if (!uri.startsWith("/")) {
            throw new WrongUrlRequestException("Wrong uri: " + uri);
        }

        final var version = startLineParts[2];
        if (!HTTP_1_1.getDescription().equals(version)) {
            throw new HttpVersionNotSupportedException("Not supported http version: " + version);
        }

        final var splitUri = uri.split("\\?");
        Map<String, Object> params = new HashMap<>();
        if (splitUri.length == 2) {
            params = getUrlParams(splitUri[1]);
        }
        final var path = splitUri[0];
        final var headers = getHeaders(buffer, requestLineEndIndex);
        final var httpMethod = HttpMethod.valueOf(method);

        in.reset();

        if (HttpMethod.POST == httpMethod) {
            final var contentLength = Integer.parseInt(headers.get(CONTENT_LENGTH).toString());
            final var headersEndIndex
                    = BytesUtil.indexOf(buffer, HTTP_START_LINE_AND_HEADER_SEPARATOR, requestLineEndIndex);

            byte[] headersContent = in.readNBytes(headersEndIndex);
            byte[] bodyContent = in.readNBytes(contentLength);
            return HttpRequest.builder()
                    .uri(uri)
                    .headers(headers)
                    .params(params)
                    .path(path)
                    .httpVersion(HTTP_1_1)
                    .body(bodyContent)
                    .httpMethod(httpMethod)
                    .build();
        }

        return HttpRequest.builder()
                .uri(uri)
                .headers(headers)
                .params(params)
                .path(path)
                .httpVersion(HTTP_1_1)
                .httpMethod(httpMethod)
                .build();
    }

    private Map<String, Object> getUrlParams(final String strParam) {
        final var paramsMap = new HashMap<String, Object>();
        if (strParam == null || strParam.isEmpty())
            return paramsMap;

        final var params = strParam.split("&");
        for (String p : params) {
            final var splitParams = p.split("=");
            paramsMap.put(splitParams[0], splitParams[1]);
        }

        return paramsMap;
    }

    private String[] getStartLineParts(final byte[] buffer, final int requestLineEndIndex) {
        final var startLine = new String(buffer, 0, requestLineEndIndex);
        final var startLineParts = startLine.trim().split(" ");
        if (startLineParts.length != 3) {
            throw new WrongHttpStartLineException("Wrong http start line " + startLine);
        }

        return startLineParts;
    }

    private Map<String, Object> getHeaders(final byte[] buf, final int start) {
        final var headers = new HashMap<String, Object>();
        int currentIndex = start;
        while (true) {
            final var headerEndIndex = BytesUtil.indexOf(buf, HTTP_LINE_SEPARATOR, currentIndex);
            if (headerEndIndex == -1) {
                throw new MalFormedRequestException();
            }

            final var headerLine = new String(buf, currentIndex, headerEndIndex - currentIndex).trim();
            if (headerLine.isEmpty())
                break;

            final var headerParts = headerLine.split(":", 2);
            if (headerParts.length != 2) {
                throw new BadHeaderException("Bad header: " + headerLine);
            }

            headers.put(headerParts[0].trim(), headerParts[1].trim());
            currentIndex = headerEndIndex;
        }

        return headers;
    }
}
