package jetty.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;

import java.net.URL;

@SuppressWarnings("NotNullNullableValidation")
public final class HandlersResource {

  public static void main(String[] args) throws Exception {
    final var server = new DefaultServer().build();

    final var resourceHandler = new ResourceHandler();
    resourceHandler.setBaseResource(Resource.newResource(HandlersResource.class.getResource("/static/example")));
    resourceHandler.setDirectoriesListed(false);

    final var contextHandler = new ContextHandler();
    contextHandler.setContextPath("/resource");
    contextHandler.setHandler(resourceHandler);

    server.setHandler(contextHandler);
    server.start();
  }
}
