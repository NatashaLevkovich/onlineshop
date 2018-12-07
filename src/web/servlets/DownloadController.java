package web.servlets;

import services.OrderService;
import services.UserService;
import services.impl.OrderServiceImpl;
import services.impl.ProductDtoService;
import services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/download")
public class DownloadController extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();
    private OrderService orderService = OrderServiceImpl.getInstance();
    private ProductDtoService productDtoService = ProductDtoService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String fileName = req.getParameter("file");
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition","attachment; filename=" + fileName);

        File my_file = new File("D:\\orders\\" + fileName);
        OutputStream out = resp.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);

        byte[] buffer = new byte[4096];
        int length;

        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }

        in.close();
        out.flush();
    }

}
