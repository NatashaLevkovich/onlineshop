package web.command.impl;

import entities.Order;
import entities.Product;
import entities.User;
import services.OrderService;
import services.ProductService;
import services.ProductSizeService;
import services.impl.OrderServiceImpl;
import services.impl.ProductServiceImpl;
import services.impl.ProductSizeServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

public class ProductController implements Controller {
    private ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Product product = productService.get(Long.valueOf(req.getParameter("id")));
        req.setAttribute("productinfo", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
