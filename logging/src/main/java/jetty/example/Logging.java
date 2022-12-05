package jetty.example;


import org.eclipse.jetty.server.AsyncRequestLogWriter;
import org.eclipse.jetty.server.CustomRequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.eclipse.jetty.server.CustomRequestLog.DEFAULT_DATE_FORMAT;
import static org.eclipse.jetty.server.CustomRequestLog.NCSA_FORMAT;

@SuppressWarnings({"FieldCanBeLocal", "NotNullNullableValidation", "FieldMayBeFinal"})
public final class Logging {
  private static String timezone = "Europe/Moscow";

  public static void main(String[] args) throws Exception {
    final var server = new DefaultServer().build();

    final var context = new ServletContextHandler();
    context.setContextPath("/");
    context.addServlet(new ServletHolder(DefaultServlet.class), "/");
    server.setHandler(context);

    final var logsPath = Paths.get(Logging.class.getResource("/static").toURI());
    final var jettyPath = logsPath.resolve("jetty");
    if (!Files.exists(jettyPath)) {
      Files.createDirectories(jettyPath);
    }

    final var writer = new AsyncRequestLogWriter(jettyPath.toString() + '/' + "app.log");
    context.setBaseResource(Resource.newResource(jettyPath));

    writer.setAppend(true);
    writer.setRetainDays(7);
    writer.setTimeZone(timezone);
    server.setRequestLog(new CustomRequestLog(writer, NCSA_FORMAT.replace("%t", "%{" + DEFAULT_DATE_FORMAT + '|' + timezone + "}t")));

    server.start();
  }
}
