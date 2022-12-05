package jetty.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

@SuppressWarnings({"Duplicates", "NotNullNullableValidation"})
public final class HttpServlet {

  public static void main(String[] args) throws Exception {
    final var server = new DefaultServer().build();
    //curl -d "key=1&value=10" http://localhost:3466
    //curl http://localhost:3466?id=1

    final var context = new ServletContextHandler();
    context.setContextPath("/");
    context.addServlet(new ServletHolder("servlet-http", ServletHttp.class), "/");

    server.setHandler(context);
    server.start();
  }
}
