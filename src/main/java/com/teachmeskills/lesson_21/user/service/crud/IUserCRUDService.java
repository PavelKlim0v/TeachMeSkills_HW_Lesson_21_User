package com.teachmeskills.lesson_21.user.service.crud;

public interface IUserCRUDService {

    String INSERT_USER = "INSERT INTO user (name, email) VALUES (?, ?)";
    String GET_DATA_USER = "SELECT * FROM user WHERE id = ?";
    String GET_DATA_ALL_USER = "SELECT * FROM user";
    String UPDATE_DATA_USER = "UPDATE user SET name = ?, email = ? WHERE id = ?";
    String DELETE_USER = "DELETE FROM user WHERE id = ?";
    String DELETE_ALL_USERS = "DELETE FROM user";
    String RESET_AUTOINCREMENT = "ALTER TABLE user AUTO_INCREMENT = 1;";

    int FIRST_PREPARED_PARAMETER_STATEMENT = 1;
    int SECOND_PREPARED_PARAMETER_STATEMENT = 2;
    int THIRD_PREPARED_PARAMETER_STATEMENT = 3;

}
