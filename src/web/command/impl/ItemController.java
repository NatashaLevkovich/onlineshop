package web.command.impl;

import entities.*;
import services.ItemService;
import services.OrderService;
import services.ProductService;
import services.ProductSizeService;
import services.impl.ItemServiceImpl;
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
import java.util.List;
import java.util.Map;

public class ItemController implements Controller {

    private ProductService productService = ProductServiceImpl.getInstance();
    private OrderService orderService = OrderServiceImpl.getInstance();
    private ProductSizeService productSizeService = ProductSizeServiceImpl.getInstance();
    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Product product = productService.get(Long.valueOf(req.getParameter("id")));
        HttpSession session = req.getSession();
        int size = Integer.valueOf(req.getParameter("size"));
        int quantity = Integer.valueOf(req.getParameter("quantity"));

        if (session.getAttribute("user") == null) {
            session.setAttribute("notUser", true);
            resp.sendRedirect("/shop?page=product&id=" + product.getId());
            return;
        } else {
            User user = (User) session.getAttribute("user");
            Order order = orderService.getOrdersByUserAndStatus(user.getId(), Status.NEW);
            Map<Integer, Integer> sizeAndQuantity = productSizeService.getSizeAndQuantityByProduct(product.getId());
            int maxQuantity = 0;
            for (Map.Entry<Integer, Integer> map : sizeAndQuantity.entrySet()) {
                if (map.getKey() == size) {
                    maxQuantity = map.getValue();
                    if (map.getValue() < quantity) {
                        session.setAttribute("error", "Недостаточно товара, в наличии " + map.getValue());
                        resp.sendRedirect("/shop?page=product&id=" + product.getId());
                        return;
                    }
                }
            }
            if (order.getId() == 0) {
                order = orderService.save(user.getId(), product.getId(), size, quantity);
                session.setAttribute("items", 1);
                session.setAttribute("productinfo", product);
                resp.sendRedirect("/shop?page=product&id=" + product.getId());
                return;
            }  else {
                List<Item> items = order.getItems();
                for (Item item : items) {
                    if (item.getProductId() == product.getId() && item.getProductSize() == size) {
                        item.setQuantity(item.getQuantity() + quantity);
                        if (quantity < maxQuantity) {
                            itemService.update(item, order, quantity);
                            session.setAttribute("items", itemService.getItemsByOrder(order.getId()).size());
                            req.setAttribute("productinfo", product);
                            resp.sendRedirect("/shop?page=product&id=" + product.getId());
                            return;
                        } else {
                            req.setAttribute("error", "Недостаточно товара, в наличии " + maxQuantity);
                            resp.sendRedirect("/shop?page=product&id=" + product.getId());
                            return;
                        }
                    } else {
                        itemService.save(order, product.getId(), size, quantity);
                        session.setAttribute("items", itemService.getItemsByOrder(order.getId()).size());
                        session.setAttribute("productinfo", product);
                        resp.sendRedirect("/shop?page=product&id=" + product.getId());
                        return;
                    }
                }
            }

        }
    }
}
