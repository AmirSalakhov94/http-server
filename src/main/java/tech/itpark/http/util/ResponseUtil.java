package tech.itpark.http.util;

import lombok.experimental.UtilityClass;
import tech.itpark.http.model.Response;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.stream.Collectors;

import static tech.itpark.http.HttpConstants.CONTENT_LENGTH;

@UtilityClass
public class ResponseUtil {

    public void fill(final Response response, BufferedOutputStream out) throws IOException {
        final var body = response.getBody();
        final var responseBody = getResponseBody(body, "\r\n".getBytes());
        if (responseBody != null) {
            response.getHeaders().put(CONTENT_LENGTH, responseBody.length);
        }

        final var httpBody =
                response.getVersion().getDescription() + " " + response.getStatusCode() + " " + response.getStatusText()
                        + "\r\n" +
                        response.getHeaders().keySet().stream()
                                .map(key -> key + ":" + response.getHeaders().get(key) + "\r\n")
                                .collect(Collectors.joining()) +
                        "\r\n";
        final var responseAll = (responseBody != null)
                ? getResponseBody(httpBody.getBytes(), responseBody)
                : httpBody.getBytes();
        out.write(responseAll);
    }

    private byte[] getResponseBody(byte[] body, byte[] body1) {
        if (body != null) {
            byte[] responseBody = new byte[body.length + body1.length];
            System.arraycopy(body, 0, responseBody, 0, body.length);
            System.arraycopy(body1, 0, responseBody, body.length, body1.length);
            return responseBody;
        }

        return body;
    }
}
