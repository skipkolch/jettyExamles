package jetty.example;

import org.eclipse.jetty.server.*;

@SuppressWarnings({"NotNullNullableValidation", "FieldCanBeLocal"})
public final class DefaultServer {
  private final Server server = new Server();
  private static final int port = 3466;

  public Server build() {
    return build(port);
  }

  public Server build(int port) {
    final var serverConnector = new ServerConnector(server, new HttpConnectionFactory());
    serverConnector.setHost("localhost");
    serverConnector.setPort(port);
    server.setConnectors(new Connector[]{serverConnector});
    return server;
  }
}
