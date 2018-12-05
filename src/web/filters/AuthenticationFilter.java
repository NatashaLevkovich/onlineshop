package web.filters;

import web.RequestHandler;
import web.command.ControllerType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/shop"})
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        ControllerType type = RequestHandler.getPage(req);
        if (ControllerType.PRODUCT.equals(type)) {

            if ((session.getAttribute("user") == null)) {
                resp.sendRedirect("/shop?page=login");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
