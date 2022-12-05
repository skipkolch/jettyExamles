package jetty.example;

import org.eclipse.jetty.security.JDBCLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

@SuppressWarnings({"Duplicates", "NotNullNullableValidation"})
public final class LoginJDBC {

  public static void main(String[] args) throws Exception {
    final var server = new DefaultServer().build();

    final var context = new ServletContextHandler();
    context.setContextPath("/");
    context.setBaseResource(Resource.newResource(LoginService.class.getResource("/static")));
    context.addServlet(new ServletHolder(DefaultServlet.class), "/");

    final var loginService = new JDBCLoginService("login", LoginJDBC.class.getResource("/jdbc_config").toExternalForm());
    context.setHandler(new SecurityHandlerBuilder().build(loginService));

    server.setHandler(context);
    server.start();
  }
}
