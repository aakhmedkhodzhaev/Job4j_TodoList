package ru.job4j.todolist.util;

import ru.job4j.todolist.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TodoUtils {

    public static final String CONNECTION_NAME = "ATTRIBUTE_FOR_CONNECTION";

    public static final String USER_NAME = "ATTRIBUTE_USER_NAME";

    public static void saveLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }

    public static void saveUserCookie(HttpServletResponse resp, User user) {
        Cookie cookieUser = new Cookie(USER_NAME, user.getEmail());
        cookieUser.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookieUser);
    }

    public static String getUserNameInCookies(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteUserCookies(HttpServletResponse resp) {
        Cookie cookieName = new Cookie(USER_NAME, null);
        cookieName.setMaxAge(0);
        resp.addCookie(cookieName);
    }

}