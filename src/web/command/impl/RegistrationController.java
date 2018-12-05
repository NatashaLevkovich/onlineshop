package web.command.impl;

import entities.User;
import services.UserService;
import services.impl.UserServiceImpl;
import web.Encoder;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RegistrationController implements Controller {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String pass2 = req.getParameter("pass2");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        if (email != null && pass != null && pass2 != null) {
            List<User> users = userService.getAll();
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    req.setAttribute("registration", "Пользовательс таки почтовым адресом уже зарегистрирован");
                    RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
                    dispatcher.forward(req, resp);
                    return;
                }
            }
            if (pass.equals(pass2)) {
                User user = new User(email, Encoder.encode(pass), firstName, lastName, address, phone);
                req.getSession().setAttribute("user", userService.save(user));
            } else {
                req.setAttribute("registration", "Пароли не совпадают");
                RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
                dispatcher.forward(req, resp);
                return;
            }

        }
        req.setAttribute("registration", "Регистрация завершена успешно");
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
