package web.command.impl;

import com.google.gson.Gson;
import entities.Order;
import entities.Status;
import entities.User;
import services.ItemService;
import services.OrderService;
import services.UserService;
import services.impl.ItemServiceImpl;
import services.impl.OrderServiceImpl;
import services.impl.UserServiceImpl;
import web.Encoder;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController implements Controller {
    private UserService userService = UserServiceImpl.getInstance();
    private OrderService orderService = OrderServiceImpl.getInstance();
    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email == null || password == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        }

        User user = userService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(Encoder.encode(password))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            Order order = orderService.getOrdersByUserAndStatus(user.getId(), Status.NEW);
            session.setAttribute("items", itemService.getItemsByOrder(order.getId()).size());
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("error", "Неверный логин или пароль");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        }
    }
}
