package com.teachmeskills.lesson_21.user.servlet;

import com.teachmeskills.lesson_21.user.entity.User;
import com.teachmeskills.lesson_21.user.service.crud.UserCRUDService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user")
public class UserCrudServlet extends HttpServlet {

    private static final UserCRUDService userCrudService = new UserCRUDService();
    private User user = new User();

    @Override   // add user
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            user = new User(user.getParameterName(req), user.getParameterEmail(req));
            userCrudService.saveUser(user);
            resp.getWriter().println("Changes have been made.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override   // get user
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (user.getParameterId(req) > 0) {
                user = userCrudService.getUserById(user.getParameterId(req));
                resp.getWriter().println(user);

            } else if (user.getParameterId(req) == 0){
                List<User> listUsers = userCrudService.getDataAllUsers();
                for (User user : listUsers) {
                    resp.getWriter().println(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override   // update user
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            user = new User(user.getParameterId(req), user.getParameterName(req), user.getParameterEmail(req));
            userCrudService.updateUser(user);
            resp.getWriter().println("Changes have been made.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override   // delete user
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (user.getParameterId(req) > 0) {
                userCrudService.deleteUserById(user.getParameterId(req));
                resp.getWriter().println("Changes have been made.");

            } else if (user.getParameterId(req) == 0){
                userCrudService.deleteAllUsers();
                resp.getWriter().println("All users have been deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

