package jetty.example;

import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("NotNullNullableValidation")
public final class ServletHttp extends HttpServlet {
  final Map<String, String> data = new ConcurrentHashMap<>();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/plain");
    final var id = request.getParameter("id");
    if (id == null) {
      response.setStatus(HttpStatus.BAD_REQUEST_400);
      return;
    }
    final var cacheControl = request.getHeader("Cache-control");
    try (final PrintWriter out = response.getWriter()) {
      out.println("Data id = " + data.get(id) + ", cache-control = " + cacheControl);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
    final var key = req.getParameter("key");
    final var value = req.getParameter("value");

    if ((key == null) || (value == null)) {
      response.setStatus(HttpStatus.BAD_REQUEST_400);
      return;
    }

    data.put(key, value);
    response.setStatus(HttpServletResponse.SC_OK);
  }
}
