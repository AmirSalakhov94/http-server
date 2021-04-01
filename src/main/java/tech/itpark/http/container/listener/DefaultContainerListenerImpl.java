package tech.itpark.http.container.listener;

import tech.itpark.http.enums.HttpVersion;
import tech.itpark.http.exception.client.ClientErrorException;
import tech.itpark.http.exception.server.ServerErrorException;
import tech.itpark.http.model.HttpResponse;
import tech.itpark.http.model.Request;
import tech.itpark.http.model.Response;
import tech.itpark.http.server.GeneralHttpServer;
import tech.itpark.http.server.GetHttpServer;
import tech.itpark.http.server.PostHttpServer;
import tech.itpark.http.util.RequestUtil;
import tech.itpark.http.util.ResponseUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class DefaultContainerListenerImpl implements ContainerListener {

    private final Map<String, GetHttpServer> registeredGetRequestPath = new HashMap<>();
    private final Map<String, PostHttpServer> registeredPostRequestPath = new HashMap<>();
    private final Map<String, GeneralHttpServer> registeredAllRequestPath = new HashMap<>();

    @Override
    public void register(String path, GeneralHttpServer generalHttpServer) {
        registeredAllRequestPath.put(path, generalHttpServer);
    }

    @Override
    public void registerGet(String path, GetHttpServer httpServer) {
        registeredGetRequestPath.put(path, httpServer);
    }

    @Override
    public void registerPost(String path, PostHttpServer httpServer) {
        registeredPostRequestPath.put(path, httpServer);
    }

    @Override
    public void listen(Socket socket) {
        try (
                socket;
                final var in = new BufferedInputStream(socket.getInputStream());
                final var out = new BufferedOutputStream(socket.getOutputStream())
        ) {
            String res = "";
            try {
                Request request = RequestUtil.fill(in);
                Response response = new HttpResponse();
                response.setVersion(request.getHttpVersion());
                response.getHeaders().put("Content-Type", "text/html;charset=UTF-8");

                GetHttpServer getServer = registeredGetRequestPath.get(request.getPath());
                if (getServer != null) {
                    getServer.doGet(request, response);
                }

                PostHttpServer postServer = registeredPostRequestPath.get(request.getPath());
                if (postServer != null) {
                    postServer.doPost(request, response);
                }

                GeneralHttpServer generalHttpServer = registeredAllRequestPath.get("/");
                generalHttpServer.execute(request, response);

                ResponseUtil.fill(response, out);
            } catch (ServerErrorException e) {
                res = HttpVersion.HTTP_1_1.getDescription() + " " + e.getCode() + " " + e.getCodeName() + "\r\n";
                e.printStackTrace();
            } catch (ClientErrorException e) {
                res = HttpVersion.HTTP_1_1.getDescription() + " " + e.getCode() + " " + e.getCodeName() + "\r\n";
                e.printStackTrace();
            } catch (Exception e) {
                res = HttpVersion.HTTP_1_1.getDescription() + " 500 Server Error \r\n";
                e.printStackTrace();
            } finally {
                out.write(res.getBytes());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
