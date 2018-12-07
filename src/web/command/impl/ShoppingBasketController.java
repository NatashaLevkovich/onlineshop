package web.command.impl;

import entities.*;
import entities.dto.ProductDto;
import services.*;
import services.impl.*;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShoppingBasketController implements Controller {

    private OrderService orderService = OrderServiceImpl.getInstance();
    private ProductDtoService productDtoService = ProductDtoService.getInstance();
    private ItemService itemService = ItemServiceImpl.getInstance();
    private ProductSizeService productSizeService = ProductSizeServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
//        if (session.getAttribute("user") == null) {
//            session.setAttribute("notUser", true);
//            resp.sendRedirect("/shop?page=shoppingbasket");
//            return;
//        }
        User user = (User) session.getAttribute("user");
        Order order = orderService.getOrdersByUserAndStatus(user.getId(), Status.NEW);
        String action = req.getParameter("action");

        if (action != null){
            Item item = itemService.get(Long.valueOf(req.getParameter("id")));
            itemService.delete(item);
            ProductSize productSize = productSizeService.getSizeByProductAndSize(item.getProductId(), item.getProductSize());
            productSize.setQuantity(productSize.getQuantity() + item.getQuantity());
            productSizeService.update(productSize);
            order = orderService.update(order);
            session.setAttribute("items", itemService.getItemsByOrder(order.getId()).size());
        }


        req.setAttribute("order", order);
        List<ProductDto> productDtoList = productDtoService.get(order.getId());
        if (productDtoList.size() > 0) {
            req.setAttribute("productDto", productDtoList);
        } else {
            orderService.delete(order);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
