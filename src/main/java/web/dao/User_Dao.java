package web.dao;

import web.model.User;

import java.util.List;

public interface User_Dao {
    List<User> getAllUsers();

    User showUserById(int id);

    void save(User user);

    void update(int id, User updatedUser);

    void delete(int id);
}
