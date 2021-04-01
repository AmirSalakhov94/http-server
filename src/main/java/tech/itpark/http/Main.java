package tech.itpark.http;

import tech.itpark.http.container.Container;
import tech.itpark.http.container.listener.ContainerListener;
import tech.itpark.http.container.listener.DefaultContainerListenerImpl;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ContainerListener containerListener = new DefaultContainerListenerImpl();
        containerListener.registerGet("/pathGet", (request, response) -> {
            System.out.println(request.getUrlParams());
            response.setStatusCode(200);
            response.addHeader("Content-Type", "text/plain");
        });
        containerListener.registerPost("/pathPost", (request, response) -> {
            System.out.println(request.getUrlParams());
            response.setStatusCode(200);
            response.addHeader("Content-Type", "text/plain");
            response.setBody(request.getBody());
        });
        Container container = new Container(Collections.singletonList(containerListener));
        container.listen(9999);
    }
}
