package com.teachmeskills.lesson_21.user.entity;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private int id;
    private String name;
    private String email;

    public User() { }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getParameterId(HttpServletRequest req) throws SQLException {
        String str = req.getParameter("id");
        if (str == null || str.equals("0")) {
            return id = 0;
        }
        return id = Integer.parseInt(req.getParameter("id"));
    }

    public String getParameterName(HttpServletRequest req) throws SQLException {
        return name = req.getParameter("name");
    }

    public String getParameterEmail(HttpServletRequest req) throws SQLException {
        return email = req.getParameter("email");
    }

    public int getIdRS(ResultSet resultSet) throws SQLException {
        return id = resultSet.getInt("id");
    }

    public String getNameRS(ResultSet resultSet) throws SQLException {
        return name = resultSet.getString("name");
    }

    public String getEmailRS(ResultSet resultSet) throws SQLException {
        return email = resultSet.getString("email");
    }

    @Override
    public String toString() {
        return "User {" +
                "id" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
