package web.service;

import org.springframework.stereotype.Service;
import web.model.User;

import java.util.List;
@Service
public interface User_Service {
    List<User> index();

    User show(int id);

    void save(User user);

    void update(int id, User updatedUser);

    void delete(int id);
}