package tech.itpark.http.container.listener;

import tech.itpark.http.server.GeneralHttpServer;
import tech.itpark.http.server.GetHttpServer;
import tech.itpark.http.server.PostHttpServer;

import java.net.Socket;

public interface ContainerListener {

    void register(String path, GeneralHttpServer generalHttpServer);

    void registerGet(String path, GetHttpServer httpServer);

    void registerPost(String path, PostHttpServer httpServer);

    void listen(final Socket socket);
}
