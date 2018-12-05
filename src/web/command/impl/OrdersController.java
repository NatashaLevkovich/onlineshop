package web.command.impl;

import entities.Order;
import entities.Dto.ProductDto;
import entities.Status;
import entities.User;
import services.OrderService;
import services.impl.OrderServiceImpl;
import services.impl.ProductDtoService;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersController implements Controller {
    private OrderService orderService = OrderServiceImpl.getInstance();
    ProductDtoService productDtoService = ProductDtoService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String orderId = req.getParameter("orderId");
        if (orderId != null) {
            Order order = orderService.get(Long.valueOf(orderId));
            order.setStatus(Status.COMPLETED);
            orderService.update(order);
        }
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.getOrdersByUser(user.getId());
        Map<Long, List<ProductDto>> map = new HashMap<>();
        req.setAttribute("orders", orders);
        for (Order o : orders) {
           map.put(o.getId(), productDtoService.get(o.getId()));
        }
        req.setAttribute("productDto", map);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
