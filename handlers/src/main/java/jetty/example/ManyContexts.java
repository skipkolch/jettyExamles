package jetty.example;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("NotNullNullableValidation")
public final class ManyContexts {

  public static void main(String[] args) throws Exception {
    var server = new DefaultServer().build();
    server.setHandler(contextCollection());
    server.start();
  }

  private static ContextHandlerCollection contextCollection() {
    final var context = new ContextHandler("/");
    context.setHandler(new HelloHandler("Root Hello"));

    final var contextFR = new ContextHandler("/fr");
    contextFR.setHandler(new HelloHandler("Bonjour"));

    final var contextIT = new ContextHandler("/it");
    contextIT.setHandler(new HelloHandler("Buongiorno"));

    final var contextRu = new ContextHandler("/ru");
    contextRu.setHandler(new HelloHandler("Привет"));

    return new ContextHandlerCollection(context, contextFR, contextIT, contextRu);
  }

  public static final class HelloHandler extends AbstractHandler {
    final String message;

    public HelloHandler(String message) {
      this.message = message;
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
      response.setContentType("text/html; charset=utf-8");
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().println("<h1>" + message + "</h1>");
      baseRequest.setHandled(true);
    }
  }

}
