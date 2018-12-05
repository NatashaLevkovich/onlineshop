package web.command;

import web.command.impl.*;

public enum ControllerType {
    MAIN("main.jsp", "Main",  new MainController()),
    CATALOG("catalog.jsp", "Catalog",  new CatalogController()),
    PRODUCT("product.jsp", "Product",  new ProductController()),
    ITEM("product.jsp", "Item",  new ItemController()),
    REGISTRATION("registration.jsp", "Registration",  new RegistrationController()),
    ORDERS("orders.jsp", "orders", new OrdersController()),
    LOGIN("login.jsp", "Login",  new LoginController()),
    SHOPPING_BASKET("shoppingbasket.jsp", "Shoppingbasket", new ShoppingBasketController()),
    ADMIN("admin.jsp", "Admin", new AdminController());


    private String pagePath;
    private String pageName;
    private Controller controller;

    ControllerType(String pagePath, String pageName, Controller controller) {
        this.pagePath = pagePath;
        this.pageName = pageName;

        this.controller = controller;
    }

    public String getPagePath() {
        return pagePath;
    }

    public String getPageName() {
        return pageName;
    }

    public Controller getController() {
        return controller;
    }

    public static ControllerType getByPageName(String page) {
        for (ControllerType type : ControllerType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
        return MAIN;
    }
}
