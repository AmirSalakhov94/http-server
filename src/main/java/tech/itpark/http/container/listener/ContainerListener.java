package tech.itpark.http.container.listener;

import java.net.Socket;

public interface ContainerListener {

    void listen(final Socket socket);
}
