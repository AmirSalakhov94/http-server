package tech.itpark.http.handler;

import tech.itpark.http.enums.HttpMethod;
import tech.itpark.http.exception.client.ClientErrorException;
import tech.itpark.http.exception.client.NotFoundException;
import tech.itpark.http.exception.server.ServerErrorException;
import tech.itpark.http.model.HttpResponse;
import tech.itpark.http.model.Request;
import tech.itpark.http.model.Response;
import tech.itpark.http.server.HttpServer;

import java.util.HashMap;
import java.util.Map;

import static tech.itpark.http.HttpConstants.CONTENT_TYPE;
import static tech.itpark.http.HttpConstants.TEXT_PLAIN;

public class HandlerHttpServer {

    private final Map<String, HttpServer> registeredAllRequests = new HashMap<>();
    private final Map<String, HttpServer> registeredSpecificRequests = new HashMap<>();

    public void register(String path, HttpServer httpServer) {
        registeredAllRequests.put(path, httpServer);
    }

    public void registerGet(String path, HttpServer httpServer) {
        registeredSpecificRequests.put(HttpMethod.GET + path, httpServer);
    }

    public void registerPost(String path, HttpServer httpServer) {
        registeredSpecificRequests.put(HttpMethod.POST + path, httpServer);
    }


    public Response handle(Request request) throws ClientErrorException, ServerErrorException {
        Response response = new HttpResponse();
        response.setVersion(request.getHttpVersion());
        response.getHeaders().put(CONTENT_TYPE, TEXT_PLAIN);

        String path = request.getHttpMethod() + request.getPath();
        HttpServer httpServer = registeredSpecificRequests.get(path);
        if (httpServer != null) {
            httpServer.execute(request, response);
            return response;
        } else {
            HttpServer generalHttpServer = registeredAllRequests.get("/");
            if (generalHttpServer != null) {
                generalHttpServer.execute(request, response);
                return response;
            }
        }

        throw new NotFoundException("Path not found: " + path);
    }
}
