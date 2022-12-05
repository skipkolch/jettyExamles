package jetty.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.net.URL;
public final class ServletsDefault {

  public static void main(String[] args) throws Exception {
    final var server = new DefaultServer().build();

    final var context = new ServletContextHandler();
    context.setBaseResource(Resource.newResource(ServletsDefault.class.getResource("/static")));
    context.setContextPath("/dir");
    context.addServlet(
        new ServletHolder(DefaultServlet.class),
        "/"
    );
    server.setHandler(context);

    server.start();
  }
}
