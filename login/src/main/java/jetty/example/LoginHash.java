package jetty.example;

import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

@SuppressWarnings("NotNullNullableValidation")
public final class LoginHash {

  public static void main(String[] args) throws Exception {
    final var server = new DefaultServer().build();

    final var context = new ServletContextHandler();
    context.setContextPath("/");
    context.setBaseResource(Resource.newResource(LoginService.class.getResource("/static")));
    context.addServlet(new ServletHolder(DefaultServlet.class), "/");

    final var loginService = new HashLoginService("login", LoginHash.class.getResource("/hash_config").toExternalForm());
    context.setHandler(new SecurityHandlerBuilder().build(loginService));

    server.setHandler(context);
    server.start();
  }
}
