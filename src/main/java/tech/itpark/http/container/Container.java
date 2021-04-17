package tech.itpark.http.container;

import lombok.RequiredArgsConstructor;
import tech.itpark.http.connection.handler.ConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;

@RequiredArgsConstructor
public class Container {

    private final ConnectionHandler connectionHandler;

    public void listen(int port) {
        try (final var serverSocket = new ServerSocket(port)) {
            while (true) {
                try {
                    final var socket = serverSocket.accept();
                    connectionHandler.onNewConnection(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
