package tech.itpark.http;

import tech.itpark.http.container.Container;
import tech.itpark.http.container.listener.ContainerListener;
import tech.itpark.http.container.listener.DefaultContainerListener;
import tech.itpark.http.handler.HandlerHttpServer;

import java.util.Collections;

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
        ContainerListener containerListener = new DefaultContainerListener(handlerHttpServer);
        Container container = new Container(Collections.singletonList(containerListener));
        container.listen(9999);
    }
}
