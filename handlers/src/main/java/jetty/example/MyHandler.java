package jetty.example;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("NotNullNullableValidation")
public final class MyHandler extends AbstractHandler {
  @Override
  public void handle(String target,
                     Request baseRequest,
                     HttpServletRequest request,
                     HttpServletResponse response) throws IOException {
    baseRequest.setHandled(true);
    final String method = baseRequest.getMethod();
    if(method == "GET") {

    }
    System.out.println("Call MyHandler with method " + method);
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().println("<h1>Hello MyHandler</h1>");
  }
}
