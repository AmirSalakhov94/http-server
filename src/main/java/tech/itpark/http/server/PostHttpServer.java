package tech.itpark.http.server;

import tech.itpark.http.model.Request;
import tech.itpark.http.model.Response;

public interface PostHttpServer {

    void doPost(Request request, Response response);
}