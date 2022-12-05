package jetty.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

@SuppressWarnings("NotNullNullableValidation")
public final class HandlersMy {

  public static void main(String[] args) throws Exception {
    final var server = new DefaultServer().build();

    final var context = new ContextHandler();
    context.setContextPath("/test");
    context.setHandler(new MyHandler());
    server.setHandler(context);
    server.start();
  }
}
