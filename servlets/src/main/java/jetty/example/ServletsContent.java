package jetty.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

@SuppressWarnings({"Duplicates", "NotNullNullableValidation"})
public final class ServletsContent {

  public static void main(String[] args) throws Exception {
    final var server = new DefaultServer().build();

    final var context = new ServletContextHandler();
    context.setContextPath("/");
    context.addServlet(
        new ServletHolder(new ContentServlet(new ContentGenerator())),
        "/"
    );
    server.setHandler(context);

    server.start();
  }
}
