package tech.itpark.http.connection.handler;

import lombok.RequiredArgsConstructor;
import tech.itpark.http.enums.HttpVersion;
import tech.itpark.http.exception.client.ClientErrorException;
import tech.itpark.http.exception.server.ServerErrorException;
import tech.itpark.http.handler.HandlerHttpServer;
import tech.itpark.http.model.Request;
import tech.itpark.http.model.Response;
import tech.itpark.http.util.RequestUtil;
import tech.itpark.http.util.ResponseUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

@RequiredArgsConstructor
public class ConnectionHandlerImpl implements ConnectionHandler {

    private final HandlerHttpServer handlerHttpServer;

    @Override
    public void onNewConnection(Socket socket) {
        try (
                socket;
                final var in = new BufferedInputStream(socket.getInputStream());
                final var out = new BufferedOutputStream(socket.getOutputStream())
        ) {
            String res = "";
            try {
                Request request = RequestUtil.fill(in);
                Response response = handlerHttpServer.handle(request);
                ResponseUtil.fill(response, out);
            } catch (ServerErrorException e) {
                res = HttpVersion.HTTP_1_1.getDescription() + " " + e.getCode() + " " + e.getCodeName() + "\r\n";
                e.printStackTrace();
            } catch (ClientErrorException e) {
                res = HttpVersion.HTTP_1_1.getDescription() + " " + e.getCode() + " " + e.getCodeName() + "\r\n";
                e.printStackTrace();
            } catch (Exception e) {
                res = HttpVersion.HTTP_1_1.getDescription() + " 500 Server Error \r\n";
                e.printStackTrace();
            } finally {
                out.write(res.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
