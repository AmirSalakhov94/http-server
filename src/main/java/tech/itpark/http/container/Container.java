package tech.itpark.http.container;

import tech.itpark.http.container.listener.ContainerListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class Container {

    private final List<ContainerListener> containerListeners;

    public Container(List<ContainerListener> containerListeners) {
        this.containerListeners = containerListeners;
    }

    public void listen(int port) {
        try (final var serverSocket = new ServerSocket(port)) {
            while (true) {
                try {
                    final var socket = serverSocket.accept();
                    containerListeners.forEach(cl -> {
                        cl.listen(socket);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
