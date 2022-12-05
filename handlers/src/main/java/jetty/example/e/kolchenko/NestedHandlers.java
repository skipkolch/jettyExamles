package jetty.example.e.kolchenko;

import jetty.example.DefaultServer;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.HandlerWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class NestedHandlers {

  public static void main(String[] args) throws Exception {
    final var server = new DefaultServer().build();

    final var wrapperHandler = new WrapperHandler();
    final var baseHandler = new BaseHandler();

    wrapperHandler.setHandler(baseHandler);


    server.setHandler(wrapperHandler);
    server.start();
  }

  public static final class BaseHandler extends AbstractHandler {
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
      System.out.println("Call -- BaseHandler");

      response.setContentType("text/html;charset=utf-8");
      response.setStatus(HttpServletResponse.SC_OK);

      final var writer = response.getWriter();
      writer.println("<h1> BaseHandler </h1>");
      writer.println(request.getAttribute("param1"));
      writer.println(request.getAttribute("param2"));

      baseRequest.setHandled(true);
    }
  }

  public static final class WrapperHandler extends HandlerWrapper {
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
      System.out.println("Call -- WrapperHandler");
      request.setAttribute("param1", "value1");
      request.setAttribute("param2", "value2");
      super.handle(target, baseRequest, request, response);
      System.out.println("Call end -- WrapperHandler");
    }
  }

}
