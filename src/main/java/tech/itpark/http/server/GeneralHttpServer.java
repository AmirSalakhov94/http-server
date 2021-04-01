package tech.itpark.http.server;

import tech.itpark.http.model.Request;
import tech.itpark.http.model.Response;

public interface GeneralHttpServer {

    void execute(Request request, Response response);
}
