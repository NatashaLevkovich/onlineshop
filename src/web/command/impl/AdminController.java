package web.command.impl;

import entities.*;
import entities.dto.ProductDto;
import services.*;
import services.impl.*;
import web.Encoder;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AdminController implements Controller {
    private ProductService productService = ProductServiceImpl.getInstance();
    private OrderService orderService = OrderServiceImpl.getInstance();
    private ProductDtoService productDtoService = ProductDtoService.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if ("set".equals(req.getParameter("action"))) {
            Product product = new Product(req.getParameter("name"), Double.valueOf(req.getParameter("price")), Double.valueOf(req.getParameter("discount")),
                    ProductCategory.valueOf(req.getParameter("category")), ProductSubcategory.valueOf(req.getParameter("subcategory")), req.getParameter("image"),
                    req.getParameter("manufacturer"), req.getParameter("material"));
            String[] sizes = req.getParameter("size").split(" ");
            String[] count = req.getParameter("quantity").split(" ");
            Map<Integer, Integer> size = new HashMap<>();

            for (int i = 0; i < sizes.length; i++) {
                size.put(Integer.valueOf(sizes[i]), Integer.valueOf(count[i]));

            }
            product.setSizeAndQuantity(size);

            productService.save(product);
        }

        if ("redactor".equals(req.getParameter("action"))) {
            long id = Long.valueOf(req.getParameter("id"));
            Product product = productService.get(id);
            String price = req.getParameter("price");
            if (price.length() > 0) {
                product.setPrice(Double.valueOf(price));
            }
            String discount = req.getParameter("discount");
            if (discount.length() > 0) {
                product.setDiscount(Double.valueOf(discount));
            }
            String category = req.getParameter("category");
            if (category.length() > 0) {
                product.setCategory(ProductCategory.valueOf(category));
            }
            String subcategory = req.getParameter("subcategory");
            if (subcategory.length() > 0) {
                product.setSubcategory(ProductSubcategory.valueOf(subcategory));
            }
            String manufacturer = req.getParameter("manufacturer");
            if (manufacturer.length() > 0) {
                product.setManufacturer(manufacturer);
            }
            String material = req.getParameter("material");
            if (material.length() > 0) {
                product.setMaterial(material);
            }
            String size = req.getParameter("size");
            if (size.length() > 0) {
                String[] sizes = req.getParameter("size").split(" ");
                String[] count = req.getParameter("quantity").split(" ");
                Map<Integer, Integer> sizeQuantity = new HashMap<>();
                for (int i = 0; i < sizes.length; i++) {
                    if (i < count.length) {
                        sizeQuantity.put(Integer.valueOf(sizes[i]), Integer.valueOf(count[i]));
                    }
                }
                product.setSizeAndQuantity(sizeQuantity);
            }

            productService.update(product);
        }

        if ("setuser".equals(req.getParameter("action"))) {
            String email = req.getParameter("email");
            String pass = Encoder.encode(req.getParameter("password"));
            String firstName = req.getParameter("firstname");
            String lastName = req.getParameter("lastname");
            String address = req.getParameter("address");
            String phone = req.getParameter("phone");
            String discount = req.getParameter("discount");
            String status = req.getParameter("status");
            for (User user : userService.getAll()) {
                if (user.getEmail().equals(email)) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
                    dispatcher.forward(req, resp);
                    return;
                }
            }

            User user = new User(email, pass, firstName, lastName, address, phone);
            if (discount.length() > 0) {
                user.setDiscount(Double.valueOf(discount));
            } else {
                user.setDiscount(0);
            }
            user.setStatus(status);
            userService.save(user);

        }

        if ("redactoruser".equals(req.getParameter("action"))) {
            long id = Long.valueOf(req.getParameter("id"));
            User user = userService.get(id);
            String firstName = req.getParameter("firstname");
            if (firstName.length() > 0) {
                user.setFirstName(firstName);
            }
            String lastName = req.getParameter("lastname");
            if (lastName.length() > 0) {
                user.setLastName(lastName);
            }
            String address = req.getParameter("address");
            if (address.length() > 0) {
                user.setAddress(address);
            }
            String phone = req.getParameter("phone");
            if (phone.length() > 0) {
                user.setPhoneNumber(phone);
            }

            userService.update(user);

            String discount = req.getParameter("discount");
            if (discount.length() > 0) {
                user.setDiscount(Double.valueOf(discount));
                userService.setDiscount(user);
            }
            String status = req.getParameter("status");
            if (status.length() > 0) {
                user.setStatus(status);
                userService.setStatus(user);
            }
        }

        if ("redactororder".equals(req.getParameter("action"))){
            long id = Long.valueOf(req.getParameter("id"));
            Status status = Status.valueOf(req.getParameter("status").toUpperCase());
            Order order = orderService.get(id);
            order.setStatus(status);
            orderService.update(order);
        }

        List<Product> products = productService.getAll();
        List<User> users = userService.getAll();

        List<Order> orders = orderService.getAll();
        for (Order o : orders) {
            File file = new File("D:\\orders\\order" + o.getId() + ".doc");
            List<File> files = new ArrayList<>();
            for (File f : new File("D:\\orders").listFiles()){
                files.add(f);
            }
            if (!files.contains(file) || o.getStatus() == Status.NEW) {
                FileWriter fw = new FileWriter(file);
                fw.write("Заказ №" + o.getId() + "\n");
                fw.write(o.getOrderDateToString() + "\n");
                fw.write("Общая стоимость: " + o.getTotalPrice()+" р. \n");
                for (User u : users){
                    if (u.getId() == o.getUserId()){
                        fw.write("ID клиента: " + u.getId()+"\n" );
                        fw.write(u.getFirstName() +" " + u.getLastName()+"\n");
                        fw.write(u.getAddress()+"\n");
                        fw.write(u.getPhoneNumber() + "\n");
                    }
                }
                for (ProductDto dto : productDtoService.get(o.getId())) {
                    fw.write(dto.toString());
                }
                fw.flush();
                fw.close();
            }
        }

        req.setAttribute("products", products);
        req.setAttribute("users", users);
        req.setAttribute("orders", orders);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
