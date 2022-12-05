package jetty.example;

import io.prometheus.client.jetty.JettyStatisticsCollector;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.eclipse.jetty.util.log.Log;

import java.lang.management.ManagementFactory;

@SuppressWarnings("NotNullNullableValidation")
public final class HandlersStatistics {

  public static void main(String[] args) throws Exception {
    final Server server = new DefaultServer().build(3466);

    MBeanContainer mbeanContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
    server.addBean(mbeanContainer);
    server.addBean(Log.getLog());

    final ContextHandler contextHandler = new ContextHandler();
    contextHandler.setContextPath("/");
    StatisticsHandler stats = new StatisticsHandler();
    stats.setHandler(new DefaultHandler());
    stats.setServer(server);
    contextHandler.setHandler(stats);
    new JettyStatisticsCollector(stats).register();
    server.setHandler(contextHandler);

    server.start();
  }
}
