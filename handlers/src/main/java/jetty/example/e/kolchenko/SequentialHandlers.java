package jetty.example.e.kolchenko;

import jetty.example.DefaultServer;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SequentialHandlers {

  public static void main(String[] args) throws Exception {
    final var server = new DefaultServer().build();

    var handlerList = new HandlerList(
        new HandlerWithParams(),
        new HandlerWithoutParams(),
        new EmptyHandler()
    );

    var handlerCollection = new HandlerCollection(
        handlerList,
        new ConsoleLogHandler()
    );

    server.setHandler(handlerCollection);
    server.start();
  }


  // set handler true if params isn't empty
  public static final class HandlerWithParams extends AbstractHandler {
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
      System.out.println("Call -- HandlerWithParams");
      final var params = request.getParameterMap();
      if (params.size() == 0) {
        return;
      }

      response.setContentType("text/html;charset=utf-8");
      response.setStatus(HttpServletResponse.SC_OK);

      final var writer = response.getWriter();
      writer.println("<h1> HandlerWithParams </h1>");
      writer.println(params);
    }
  }

  public static final class HandlerWithoutParams extends AbstractHandler {
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
      System.out.println("Call -- HandlerWithoutParams");

      response.setContentType("text/html;charset=utf-8");
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().println("<h1> HandlerWithoutParams </h1>");
    }
  }

  public static final class EmptyHandler extends AbstractHandler {
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) {
      System.out.println("Call -- EmptyHandler");
    }
  }

  public static final class ConsoleLogHandler extends AbstractHandler {
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) {
      System.out.println("Call -- ConsoleLogHandler");
      System.out.println(request.getParameterMap());
    }
  }
}
