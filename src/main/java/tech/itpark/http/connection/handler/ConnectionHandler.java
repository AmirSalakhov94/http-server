package tech.itpark.http.connection.handler;

import java.net.Socket;

public interface ConnectionHandler {

    void onNewConnection(final Socket socket);
}
