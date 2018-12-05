package web.servlets;

import web.RequestHandler;
import web.command.ControllerType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/shop")
public class ServletController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerType controllerType = RequestHandler.getPage(req);
        controllerType.getController().execute(req, resp);
    }
}
