package jetty.example;

import org.eclipse.jetty.server.*;

@SuppressWarnings("NotNullNullableValidation")
public final class Connectors {

  public static void main(String[] args) throws Exception {
    final var server = new Server();

    final var serverConnector = new ServerConnector(server, new HttpConnectionFactory());
    serverConnector.setHost("localhost");
    serverConnector.setPort(3466);

    server.setConnectors(new Connector[]{serverConnector});
    server.start();
  }
}
