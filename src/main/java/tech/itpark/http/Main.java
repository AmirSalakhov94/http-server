package tech.itpark.http;

import tech.itpark.http.connection.handler.ConnectionHandler;
import tech.itpark.http.connection.handler.ConnectionHandlerImpl;
import tech.itpark.http.container.Container;
import tech.itpark.http.handler.HandlerHttpServer;

import static tech.itpark.http.HttpConstants.CONTENT_TYPE;
import static tech.itpark.http.HttpConstants.TEXT_PLAIN;

public class Main {
    public static void main(String[] args) {
        HandlerHttpServer handlerHttpServer = new HandlerHttpServer();
        handlerHttpServer.registerGet("/pathGet", (request, response) -> {
            System.out.println(request.getUrlParams());
            response.addHeader(CONTENT_TYPE, TEXT_PLAIN);
        });
        handlerHttpServer.registerPost("/pathPost", (request, response) -> {
            System.out.println(request.getUrlParams());
            response.addHeader(CONTENT_TYPE, TEXT_PLAIN);
            response.setBody(request.getBody());
        });
        ConnectionHandler connectionHandlerListener = new ConnectionHandlerImpl(handlerHttpServer);
        Container container = new Container(connectionHandlerListener);
        container.listen(9999);
    }
}
