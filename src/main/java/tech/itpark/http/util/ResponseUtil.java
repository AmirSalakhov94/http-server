package tech.itpark.http.util;

import lombok.experimental.UtilityClass;
import tech.itpark.http.model.Response;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.stream.Collectors;

@UtilityClass
public class ResponseUtil {

    public void fill(final Response response, BufferedOutputStream out) throws IOException {
        response.setStatusCode(200);
        response.setStatusText("Ok");
        byte[] body = response.getBody();
        byte[] responseBody = getResponseBody(body, "\r\n".getBytes());
        if (responseBody != null) {
            response.getHeaders().put("Content-Length", responseBody.length);
        }

        String s = response.getVersion().getDescription() + " " + response.getStatusCode() + " " + response.getStatusText() + "\r\n" +
                response.getHeaders().keySet().stream()
                        .map(key -> key + ":" + response.getHeaders().get(key) + "\r\n")
                        .collect(Collectors.joining()) +
                "\r\n";
        byte[] responseAll = s.getBytes();
        if (responseBody != null) {
            responseAll = getResponseBody(s.getBytes(), responseBody);
        }
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
