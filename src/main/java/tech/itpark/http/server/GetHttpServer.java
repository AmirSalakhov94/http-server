package tech.itpark.http.server;

import tech.itpark.http.model.Request;
import tech.itpark.http.model.Response;

public interface GetHttpServer {

    void doGet(Request request, Response response);
}
