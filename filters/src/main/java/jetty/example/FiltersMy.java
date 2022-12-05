package jetty.example;

import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

@SuppressWarnings({"Duplicates", "NotNullNullableValidation"})
public final class FiltersMy {

  public static void main(String[] args) throws Exception {
    final var server = new DefaultServer().build();

    final var context = new ServletContextHandler();
    context.setBaseResource(Resource.newResource(FiltersMy.class.getResource("/static")));
    context.setContextPath("/");
    context.addServlet(new ServletHolder(DefaultServlet.class), "/");
    context.addFilter(MyFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

    server.setHandler(context);
    server.start();
  }
}
