package web;

import web.command.ControllerType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static web.command.ControllerType.MAIN;

public class RequestHandler {
    public static ControllerType getPage(HttpServletRequest req) {
        String page = req.getParameter("page");
        if (page == null || "".equals(page)) {
            page = "main";
        }

        ControllerType type = ControllerType.getByPageName(page);

        HttpSession session = req.getSession();
        String pageName = (String) session.getAttribute("pageName");

        if (pageName != null) {
            session.setAttribute("prevPage", pageName);
            session.setAttribute("pageName", type.getPageName());
            session.setAttribute("pagePath", type.getPagePath());
        } else {
            session.setAttribute("prevPage", type.getPageName());
            session.setAttribute("pageName", MAIN.getPageName());
            session.setAttribute("pagePath", MAIN.getPagePath());
        }

        return type;
    }
}
