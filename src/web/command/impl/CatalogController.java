package web.command.impl;

import services.ProductService;
import services.impl.ProductServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CatalogController implements Controller {
    private ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String category = req.getParameter("cat");
        if (category != null) {
            req.setAttribute("products", productService.getProductsBySubcategory(category.toUpperCase()));
        } else {
        req.setAttribute("products",  productService.getAll());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
