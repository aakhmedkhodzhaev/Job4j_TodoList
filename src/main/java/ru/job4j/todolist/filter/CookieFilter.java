package ru.job4j.todolist.filter;

import ru.job4j.todolist.model.User;
import ru.job4j.todolist.persistence.TodoRegistration;
import ru.job4j.todolist.util.TodoUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CookieFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sreq;
        HttpSession session = req.getSession();

        User userInSession = TodoUtils.getLoginedUser(session);

        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            filterChain.doFilter(sreq, sresp);
            return;
        }

        String cheked = (String) session.getAttribute("COOKIE_CHEKED");
        if (cheked == null) {
            String userEmail = TodoUtils.getUserNameInCookies(req);
            try {
                User user = TodoRegistration.getInstance().findByEmail(userEmail);
                TodoUtils.saveLoginedUser(session, user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }
        filterChain.doFilter(sreq, sresp);
    }

    @Override
    public void destroy() {
    }
}