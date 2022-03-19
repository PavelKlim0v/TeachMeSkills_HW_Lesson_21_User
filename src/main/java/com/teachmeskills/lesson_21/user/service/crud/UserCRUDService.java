package com.teachmeskills.lesson_21.user.service.crud;

import com.teachmeskills.lesson_21.user.service.connector.MySQLConnector;
import com.teachmeskills.lesson_21.user.entity.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserCRUDService implements IUserCRUDService {

    private static final MySQLConnector connector = new MySQLConnector();

    // добавление нового пользователя
    public void saveUser(User user) {
        try(PreparedStatement preparedStatement = connector.getConnection().prepareStatement(INSERT_USER)) {
            preparedStatement.setString(FIRST_PREPARED_PARAMETER_STATEMENT, user.getName());
            preparedStatement.setString(SECOND_PREPARED_PARAMETER_STATEMENT, user.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // вывести в консоль данные пользователя по id (все данные по пользователю)
    public User getUserById(int id) {
        try(PreparedStatement preparedStatement = connector.getConnection().prepareStatement(GET_DATA_USER)) {
            preparedStatement.setInt(FIRST_PREPARED_PARAMETER_STATEMENT, id);
            preparedStatement.execute();

            User user = new User();
            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    user = new User(user.getIdRS(resultSet), user.getNameRS(resultSet), user.getEmailRS(resultSet));
                }
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // вывести в консоль список всех пользователей (все данные пользователей)
    public List<User> getDataAllUsers() {
        try(Statement statement = connector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_DATA_ALL_USER)) {
            List<User> listUsers = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                listUsers.add(new User(user.getIdRS(resultSet), user.getNameRS(resultSet), user.getEmailRS(resultSet)));
            }
            return listUsers;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // изменить данные пользователя по id в бд
    public void updateUser(User user) {
        try(PreparedStatement preparedStatement = connector.getConnection().prepareStatement(UPDATE_DATA_USER)) {
            preparedStatement.setString(FIRST_PREPARED_PARAMETER_STATEMENT, user.getName());
            preparedStatement.setString(SECOND_PREPARED_PARAMETER_STATEMENT, user.getEmail());
            preparedStatement.setInt(THIRD_PREPARED_PARAMETER_STATEMENT, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // удалить пользователя по id в бд
    public void deleteUserById(int id) {
        try(PreparedStatement preparedStatement = connector.getConnection().prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(FIRST_PREPARED_PARAMETER_STATEMENT, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // удалить всех пользователей в бд
    public void deleteAllUsers() {
        try(PreparedStatement preparedStatement = connector.getConnection().prepareStatement(DELETE_ALL_USERS)) {
            preparedStatement.executeUpdate();
            resetAutoincrement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // обновить счетчик id в бд (используется только в методе deleteAllUsers())
    private void resetAutoincrement() {
        try(PreparedStatement preparedStatement = connector.getConnection().prepareStatement(RESET_AUTOINCREMENT)) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
