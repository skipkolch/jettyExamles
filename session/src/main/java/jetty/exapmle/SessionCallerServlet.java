package jetty.exapmle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("NotNullNullableValidation")
public final class SessionCallerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //force session creation
        req.getSession(true);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
