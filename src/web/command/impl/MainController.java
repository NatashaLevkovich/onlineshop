package web.command.impl;


import services.ProductService;
import services.impl.ProductServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class MainController implements Controller {
    private ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String logout = req.getParameter("logout");
        if ("off".equalsIgnoreCase(logout)){
            HttpSession session = req.getSession();
            session.removeAttribute("user");
            resp.sendRedirect("/shop?page=main");
            return;
        }
        req.setAttribute("products",  productService.getAll());
        req.setAttribute("saleproduct", productService.getProductsByDiscount(0.2));
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
